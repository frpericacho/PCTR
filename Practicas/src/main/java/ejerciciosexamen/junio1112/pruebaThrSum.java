package ejerciciosexamen.junio1112;

class pruebaThrSum extends Thread{
    private int ini;
    private int fin;
    public static Object cerrojo = new Object();

    public pruebaThrSum(int ini,int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public void run(){
        int cont = 0;

        for(int i = ini; i < fin; i++){
            cont += ThrSum.vector[i];
        }

        synchronized(cerrojo){
            ThrSum.cont += cont;
            System.out.println(Thread.currentThread().getName());
        }
    }
}