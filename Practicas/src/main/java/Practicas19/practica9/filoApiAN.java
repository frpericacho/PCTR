
import java.util.concurrent.*;

public class filoApiAN {
    static Semaphore[] forks = new Semaphore[5];

    public filoApiAN() {
        for (int i = 0; i < 5; i++)
            forks[i] = new Semaphore(1);
    }

    public synchronized void takeforks(int idFil) {
        System.out.println("el filosofo " + idFil + " coje los cubiertos");
        try {
            forks[idFil].acquire();
            forks[(idFil + 1) % 5].acquire();
        } catch (InterruptedException e) {
        }
        releaseforks(idFil);
    }

    public void releaseforks(int idFil) {
        try {
            System.out.println("el filosofo " + idFil + " suelta los cubiertos");
            forks[idFil].release();
            forks[(idFil + 1) % 5].release();
        } catch (Exception e) {
        }
    }

}