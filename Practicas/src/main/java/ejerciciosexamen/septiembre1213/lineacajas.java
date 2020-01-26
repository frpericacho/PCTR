/*
package ejerciciosexamen.septiembre1213;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class lineacajas{
    private static ReentrantLock cerrojo = new ReentrantLock();
    private static int[] nclientes = new int[10];
    private static Condition[] cajas = new Condition[20];
    private static int ultimacaja=0;
    private static int i=0;

    public static void rellena(){
        for(int i = 0; i < 20; i++){
            cajas[i] = cerrojo.newCondition();
        }
        for(int i = 0; i < 10; i++){
            nclientes[i]=0;
        }
    }

    public static void metodo() throws InterruptedException {
        for(int i = ultimacaja; i!=(ultimacaja-1+20)%20;i=(i+1+20)%20){
            cerrojo.lock();
            while(nclientes[i]==10){
                cajas[i].await();
            }
            nclientes[i]++;
            cerrojo.unlock();
            atender(i);
            break;
        }
    }

    public static void atender(int i) throws InterruptedException {
        Thread.sleep(1000);
        cerrojo.lock();
        nclientes[i]--;
        cerrojo.unlock();
    }
}
*/