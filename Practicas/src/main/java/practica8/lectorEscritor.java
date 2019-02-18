package practica8;

class lectorEscritor {
    static Integer n = 0;
    static boolean escribiendo = false;

    public synchronized void fin_lecture() {
        n--;
        System.out.println("he terminado de leer");
        if (n == 0) {
            notifyAll();
        }
    }

    public synchronized void inicia_lecture() throws Exception {
        while (escribiendo) {
            wait();
        }
        System.out.println("he empezado a leer");
        n++;
        notifyAll();
    }

    public synchronized void inicia_escritura() throws Exception {
        while (n != 0 || escribiendo) {
            wait();
        }
        System.out.println("he empezado a escribir");
        escribiendo = true;
    }

    public synchronized void fin_escritura() {
        escribiendo = false;
        System.out.println("he terminado de escribir");
        notifyAll();
        
    }
}