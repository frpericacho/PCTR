
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsamonitorCadena extends Thread {

    private int id;
    private static monitorCadena mon1 = new monitorCadena(100);
    private static monitorCadena mon2 = new monitorCadena(50);

    public UsamonitorCadena(int id) {
        this.id = id;
    }

    public int[][] matriz() {
        Random r = new Random();
        int[][] matriz = new int[(int)10e2][(int)10e2];

        for (int i = 0; i < (int)10e2; i++)
            for (int j = 0; j < (int)10e2; j++)
                matriz[i][j] = r.nextInt(10)+1;

        return matriz;
    }

    public int[][] trans(int mat[][]) {
        int[][] t = new int[(int)10e2][(int)10e2];
        for (int i = 0; i < (int)10e2; i++)
            for (int j = 0; j < (int)10e2; j++)
                t[i][j] = mat[j][i];

        return t;
    }

    public void run() {
        switch (id) {
        case 0:
            for (int i = 0; i < 100; i++) {
                try {
                    mon1.inserta(matriz());
                } catch (Exception e) {
                }
                System.out.println("Inserta en buffer1");
            }
            break;

        case 1:
            for (int i = 0; i < 100; i++) {
                try {
                    mon2.inserta(trans(mon1.extrae()));
                } catch (Exception e) {
                }
                System.out.println("Extrae de buffer 1 e inserta en buffer2");
            }
            break;

        case 2:
            for (int i = 0; i < 100; i++) {
                try {
                    mon2.extrae();
                } catch (Exception e) {
                }
                System.out.println("Extrae de buffer2");
            }
            break;
        }
    }

    public static void main(String[] args) throws Exception {
        UsamonitorCadena hilos[] = new UsamonitorCadena[3];

        ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            exe.execute(new UsamonitorCadena(i));
        }
        exe.shutdown();
    }

}