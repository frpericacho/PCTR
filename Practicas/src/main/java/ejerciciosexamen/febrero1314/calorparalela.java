package ejerciciosexamen.febrero1314;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class calorparalela implements Runnable{
    public static int[] escala = new int[201];
    public static int[][] mesc = new int[16][16];
    public static int[][] mlec = new int[16][16];
    public static int ini,fin;
    public static Random r = new Random();

    public void run() {
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < 16; j++){
                mesc[i][j] = mlec[i][j] + escala[r.nextInt(201)]*(mlec[i+1][j] + mlec[i-1][j] - 2*mlec[i][j]) + escala[r.nextInt(201)]*(mlec[i][j+1] + mlec[i][j-1] - 2*mlec[i][j]);
            }
        }
    }

    public calorparalela(int inicio,int fini){
        ini = inicio;
        fin = fini;
    }

    public static void main(String[] args) {
        int cont = -100;
        int inicio = 0,fini = 0;
        for(int i = 0;i < 201; i++){
            escala[i] = cont;
            cont++;
        }
        inicializa();
        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0;i < Runtime.getRuntime().availableProcessors(); i++){
            inicio = i*(16/4);
            fini = (i+1)*(16/4);
            exe.execute(new calorparalela(inicio,fini));
        }
        exe.shutdown();
        while(!exe.isTerminated());
    }

    public static void inicializa(){
        for(int i = 4;i < 6; i++){
            for(int j = 4;j < 6; j++){
                mlec[i][j] = 50;
            }
        }
        for(int i = 0; i < 16; i++){
            for(int j = 0;j < 16; j++){
                if(i == 0 || i == 9 || j == 0 || j == 9)
                    mlec[i][j] = 100;
                else
                    mlec[i][j] = 0;
            }
        }
        for (int x=0; x < mlec.length; x++) {
            System.out.print("|");
            for (int y=0; y < mlec[x].length; y++) {
              System.out.print (mlec[x][y]);
              if (y!=mlec[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
          }
    }
}