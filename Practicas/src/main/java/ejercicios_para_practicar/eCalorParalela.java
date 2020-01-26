package ejercicios_para_practicar;
import java.lang.Runnable;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class eCalorParalela implements Runnable{
    public int ini,fin;
    public static ReentrantLock lock = new ReentrantLock();
    public static int[][] mlec = new int[10][10];
    public static int[][] mesc = new int[10][10];
    public static int c1,c2;
    public static Scanner s = new Scanner(System.in);
    public static int cores = Runtime.getRuntime().availableProcessors();

    public eCalorParalela(int ini, int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public static void main(String[] args) {
        int opc;
        int parte = (10+cores-1)/cores;
        

        System.out.println("introduzca el primer coeficiente");
        c1 = s.nextInt();
        System.out.println("introduzca el segundo coeficiente");
        c2 = s.nextInt();

        do{
            System.out.println("escoja una opcion");
            System.out.println("1.-Procesar normal");
            System.out.println("2.-Procesar propio");
            System.out.println("3.-Salir");
            opc = s.nextInt();

            switch(opc){
                case 1: rellena();
                        imprime(mlec);
                        ExecutorService exe = Executors.newFixedThreadPool(cores);
                        for(int i = 0; i < cores; i++){
                            int inicio = parte * i;
                            int fini = Math.min(inicio + parte, 10);
                            exe.execute(new eCalorParalela(inicio,fini));
                        }
                        exe.shutdown();
                        imprime(mesc);
                break;
                case 2:
                break;
            }
        }while(opc!=3);
    }

    public void run(){
        lock.lock();
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < 10; j++){
                mesc[i][j] = mlec[Math.floorMod(i, mlec.length)][Math.floorMod(j, mlec.length)] + c1 * 
                    (mlec[Math.floorMod(i + 1, mlec.length)][Math.floorMod(j, mlec.length)] + 
                    mlec[Math.floorMod(i - 1, mlec.length)][Math.floorMod(j, mlec.length)] - 
                    2*mlec[Math.floorMod(i, mlec.length)][Math.floorMod(j, mlec.length)]) + c2 * 
                    (mlec[Math.floorMod(i, mlec.length)][Math.floorMod(j+1, mlec.length)] + 
                    mlec[Math.floorMod(i, mlec.length)][Math.floorMod(j-1, mlec.length)] - 
                    2*mlec[Math.floorMod(i, mlec.length)][Math.floorMod(j, mlec.length)]);
            } 
        }
        lock.unlock();
    }

    public static void rellena(){
        for(int i = 0;i < 10; i++){
            for(int j = 0; j < 10; j++){
                    mlec[i][j] = 0;
            }
        }

        for(int i = 3; i < 7; i++){
            for(int j = 3; j < 7; j++){
                if(i == 3 || i == 6 || j == 3 || j == 6)
                    mlec[i][j] = 100;
                else
                    mlec[i][j] = 50;
            }
        }
    }

    public static void imprime(int mat[][]){
        System.out.println("---------------------------------");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
}