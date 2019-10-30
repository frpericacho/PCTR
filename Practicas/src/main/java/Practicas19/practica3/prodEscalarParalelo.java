package Practicas19.practica3;
import java.util.Random;

class prodEscalarParalelo extends Thread {
    public static int cant = (int) 10e6;
    public static int inicio, fin, idHebra, val = 2;
    public static int produtoParcial[] = new int[2];
    public static int vec[] = new int[cant];
    public static Random r = new Random();

    public prodEscalarParalelo(int idHebra, int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.idHebra = idHebra;
    }

    public static void main(String[] args) {
        rellena();
        prodEscalarParalelo hilo1 = new prodEscalarParalelo(0, 0, cant / 2);
        prodEscalarParalelo hilo2 = new prodEscalarParalelo(1, cant / 2, cant);
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(produtoParcial[1]);
        int total = 0;
        for(int i = 0; i < 2; i++){
            System.out.println(produtoParcial[i]);
            total += produtoParcial[i];
        }
        
        System.out.println(total);
    }

    public static void rellena(){
        for(int i = 0; i < cant; i++){
            vec[i] = r.nextInt();
        }
    }

    public void run() {
        System.out.println(idHebra);
        for (int i = inicio; i < fin; i++) {
            produtoParcial[idHebra] += vec[i] * val;
        }
    }
}