package Practicas19.practica2;


class Usa_hebra{
    public static void main(String[] args) throws Exception{
        hebra[] h = new hebra[2];
       
        h[0] = new hebra(true);
        h[0].start();
       
        h[1] = new hebra(false);
        h[1].start();
        
        h[0].join();
        h[1].join();
    }
}