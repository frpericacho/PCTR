package ejerciciosexamen.monitor;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class lineaCajas{
    ReentrantLock lock = new ReentrantLock();
    Condition c = lock.newCondition();
    int cajas[] = new int[20];
    boolean haycaja = true;

    public lineaCajas(){
        for(int i = 0; i < 20; i++)
            cajas[i] = 0;
    }

    public int irCaja(int id){
        lock.lock();
        int caja = 0;
        try{
            try {
                while(!haycaja){
                    c.await();
                }
                for(int i = 0; i < 20; i++){
                    if(cajas[i] < 10){
                        caja = i;
                        cajas[i]++;
                        break;
                    }
                }
                int total_client = 0;
                for(int i = 0; i < 20; i++){
                    total_client += cajas[i];
                }
                if(total_client == 10*20)
                    haycaja = false;
                System.out.println("El cliente " + id + " ha entrado en la caja " + caja + " en el puesto " + cajas[caja]);
            } catch (Exception e) {
            }
        }finally{
            lock.unlock();
            return caja;
        }
    }

    public void salirCaja(int id, int caja){
        lock.lock();
        try{
            System.out.println("el cliente " + id + " ha salido de la caja " + caja);
            cajas[caja]--;
            c.signalAll();
        }finally{
            lock.unlock();
        }
    }
}