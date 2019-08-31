package ejercicios_para_practicar;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class torre{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition c_a = lock.newCondition();
    public static Condition c_d = lock.newCondition();
    public static boolean[] pistas_d = new boolean[3];
    public static boolean pista_a;
    public static int cant;

    public int entra_PD(int id){
        lock.lock();
        try{
            while(cant == 0){
                try {
                    c_d.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cant--;
            int i;
            for (i = 0; i < 3; ++i) {
                if (!pistas_d[i]) {
                    pistas_d[i] = true;
                    break;
                }
            }

            System.out.println("Avion " + id + " entra en pista de despegue " + i);
            return i;
        }finally{
            lock.unlock();
        }
    }

    public void sale_PD(int pista,int id){
        lock.lock();
        try{
            System.out.println("el avion " + id + " ha salido de la pista de despegue " + pista);
            cant++;
            pistas_d[pista] = true;
            c_d.signalAll();
        }finally{
            lock.unlock();
        }
    }

    public void entra_PA(int id){
        lock.lock();
        try{
            while(!pista_a){
                try {
                    c_a.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pista_a = false;
            System.out.println("el avion " + id + " ha entrado en la pista de aterrizaje");
        }finally{
            lock.unlock();
        }
    }

    public void sale_PA(int id){
        lock.lock();
        try{
            pista_a = true;
            System.out.println("el avion " + id + " ha salido de la pista de aterrizaje");
        }finally{
            lock.unlock();
        }
    }

}