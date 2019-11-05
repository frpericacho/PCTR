package Practicas19.practica4;

class algDekker {
    /* Iteraciones que dara cada hilo */
    static final int iteraciones = 2000000;
    /* Recurso compartido */
    static volatile int enteroCompartido = 0;
    /* Representa el deseo del hilo P de entrar en la seccion critica */
    static volatile boolean wantp = false;
    /* Representa el deseo del hilo Q de entrar en la seccion critica */  
    static volatile boolean wantq = false;
    /* Representa el deseo del hilo R de entrar en la seccion critica */
    static volatile boolean wantr = false;
    /* Representa de quien es el turno */
    static volatile int turn = 1;

    class P extends Thread {
        public void run() {
            for (int i=0; i<iteraciones; ++i) {
                /* Seccion no critica */
                wantp = true;
                while (wantq || wantr) {
                    if (turn != 1) {
                        wantp = false;
                        while (turn != 1)
                            Thread.yield();
                        wantp = true;
                    }
                }
                
                /* Seccion critica */
                ++enteroCompartido;
                Thread.yield();
                System.out.println("el numero de hilos en la seccion critica: "+enteroCompartido);
                --enteroCompartido;
                /* Fin Seccion critica */
                
                turn = 2;
                wantp = false;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for (int i=0; i<iteraciones; ++i) {
                /* Seccion no critica */
                wantq = true;
                while (wantp || wantr) {
                    if (turn != 2) {
                        wantq = false;
                        while (turn != 2)
                            Thread.yield();
                        wantq = true;
                    }
                }
                
                /* Seccion critica */
                ++enteroCompartido;
                Thread.yield();
                System.out.println("el numero de hilos en la seccion critica: "+enteroCompartido);
                --enteroCompartido;
                /* Fin Seccion critica */
                
                turn = 3;
                wantq = false;
            }
        }
    }

    class R extends Thread {
        public void run() {
            for (int i=0; i<iteraciones; ++i) {
                /* Seccion no critica */
                wantr = true;
                while (wantp || wantq) {
                    if (turn != 3) {
                        wantr = false;
                        while (turn != 3)
                            Thread.yield();
                        wantr = true;
                    }
                }
                
                /* Seccion critica */
                ++enteroCompartido;
                Thread.yield();
                System.out.println("el numero de hilos en la seccion critica: "+enteroCompartido);
                --enteroCompartido;
                /* Fin Seccion critica */
                
                turn = 1;
                wantr = false;
            }
        }
    }

    algDekker() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        p.start();
        q.start();
        r.start();
        
        try {
            p.join();
            q.join();
            r.join();
            System.out.println("El valor del recurso compartido es " + enteroCompartido);
            System.out.println("Deberia ser 0.");
        }
        catch (InterruptedException e) {}
    }

    public static void main(String[] args) {
        new algDekker();
    }
}