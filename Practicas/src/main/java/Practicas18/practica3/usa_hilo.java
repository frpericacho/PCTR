package Practicas18.practica3;

class usa_hilo{
    public static void main(String[] args) throws Exception{
        hilo[] h = new hilo[2];
        h[0]=new hilo(true);
        h[0].start();
        h[1]=new hilo(false);
        h[1].start();
        
        h[0].join();
        h[1].join();
    }
}