package ejerciciosexamen.junio1213;

import java.util.Random;

class automataCelularCilindrico extends Thread{
    private int ini,fin;
    private static int[] v = new int[1000];
    private static int[] v2 = new int[1000];
    static Random r = new Random();

    public automataCelularCilindrico(int ini,int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public void run(){
        for(int i = ini; i < fin ; i++){
            v2[i] = (v[(i-1+1000)%1000] + v[i] + v[(i+1+1000)%1000]) % 3;
        }
        for(int i = ini; i < fin; i++){
            v[i] = v2[i];
        }
    }
    public static void main(String[] args) throws Exception{
        int in = 0,fi = 250,n = 3;
        for(int i = 0; i < 1000; i++){
            v[i] = r.nextInt(n-1);
        }
        Thread hilos[] = new Thread[4];

        for(int i = 0; i < n; i++){
            in = 0;fi = 250; 
            for(int j = 0; j < 4; j++){
                hilos[j] = new Thread(new automataCelularCilindrico(in, fi));
                in = fi;
                fi += 250;
            }
            for(int j = 0; j < 4; j++){
                hilos[j].start();
            }
            for(int j = 0; j < 4; j++){
                hilos[j].join();
            }
        }
    }
}