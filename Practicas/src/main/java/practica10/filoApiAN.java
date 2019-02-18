package practica10;

import java.util.concurrent.Semaphore;

public class filoApiAN{
    static Semaphore[] cubierto = new Semaphore[5];

    public filoApiAN(){
        for(int i = 0;i < 5; i++){
            cubierto[i] = new Semaphore(1);
        }
    }

    public synchronized void empieza(int id) throws Exception{
        System.out.println("filosofo "+id+" espera");
        cubierto[id].acquire();
        cubierto[(id+1)%5].acquire();
        acaba(id);
    }

    public synchronized void acaba(int id) throws Exception{
        System.out.println("filosofo "+id+" come");
        cubierto[id].release();
        cubierto[(id+1)%5].release();
    }
}