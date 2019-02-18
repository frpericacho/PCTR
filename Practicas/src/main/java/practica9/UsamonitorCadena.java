package practica9;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class UsamonitorCadena implements Runnable{
    private int accion;
    private double[][] m = new double[10][10];
    private Random r = new Random();
    static monitorCadena_x b1 = new monitorCadena_x(100);
    static monitorCadena_x b2 = new monitorCadena_x(50);
    public UsamonitorCadena(int i){
        accion = i;
    }

    public void run(){
        double[][] n = new double[10][10];

        for(int i = 0;i < 150; i++){
            double res = 1;
            switch(accion){
                case 0: for(int j = 0;j < 10; j++){
                            for(int c = 0;c < 10; c++){
                                m[j][c] = r.nextDouble();
                            }
                        }
                try {
                    b1.poner(m);
                } catch (Exception e) {}
                        break;

                case 1:
                try {
                    m = b1.sacar();
                } catch (Exception e) {}
                for(int j = 0;j < 10; j++){
                    for(int c = 0;c < 10; c++){
                        n[c][j] = m[j][c];
                    }
                }
                try {
                    b2.poner(n);
                } catch (Exception e) {}
                break;

                case 2:try {
                    m = b2.sacar();
                } catch (Exception e) {}
                for(int j = 0; j < 10; j++){
                    res = res * m[i][i];
                }
                System.out.println(res);
                break;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ExecutorService exe = Executors.newFixedThreadPool(3);
        for(int i = 0;i < 3; i++){
            exe.execute(new UsamonitorCadena(i));
        }
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.DAYS);
    }
}