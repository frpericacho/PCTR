
import java.util.ArrayList;

public class monitorCadena {

    private ArrayList<int[][]> buffer;
    private int n;
    private int cant;

    public monitorCadena(int n) {
        buffer = new ArrayList<int[][]>(n);
        this.n = n;
        cant = 0;
    }

    public synchronized int[][] extrae() throws Exception {
        while (buffer.isEmpty()) {
            notifyAll();
            wait();
        }
        int[][] m = buffer.get(cant - 1);
        buffer.remove(cant - 1);
        --cant;
        notifyAll();
        return m;
    }

    public synchronized void inserta(int[][] m) throws Exception {
        while (buffer.size() == n) {
            notifyAll();
            wait();
        }
        buffer.add(m);
        ++cant;
        notifyAll();
    }
}