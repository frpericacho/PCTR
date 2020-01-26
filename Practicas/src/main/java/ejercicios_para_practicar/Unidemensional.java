package ejercicios_para_practicar;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Unidimensional
 */
public class Unidemensional implements Runnable {
    private static Integer[] cells;
    private static Integer[] nextGeneration;
    private static CyclicBarrier barrier;
    private static int generations;
    private int begin, end;

    public Unidemensional(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public static void setCells(int size, int cores, int generations, int end) {
        nextGeneration = new Integer[size];
        Unidemensional.generations = generations;
        barrier = new CyclicBarrier(cores, () -> {
            System.out.println(Arrays.toString(cells));
            cells = nextGeneration.clone();
        });
        cells = new Random().ints(size, 1, end).boxed().toArray(Integer[]::new);
    }

    public static Integer[] getNextGeneration() {
        return nextGeneration;
    }

    public static Integer[] getCells() {
        return cells;
    }

    @Override
    public void run() {
        for (int g = 0; g < generations; ++g) {
            for (int i = begin; i < end; ++i) {
                nextGeneration[i] = transicion(i);
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer transicion(int index) {
        return (cells[Math.floorMod(index - 1, cells.length)] + cells[index]
                + cells[Math.floorMod(index + 1, cells.length)]) % 3;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        int cores = Runtime.getRuntime().availableProcessors();
        Unidemensional.setCells(size, cores, 8, 100);
        int tasks = size;
        int chunkSize = (tasks + cores - 1) / cores;
        ExecutorService eService = Executors.newFixedThreadPool(cores);
        for (int task = 0, begin, end; task < cores; ++task) {
            begin = task * chunkSize;
            end = Math.min(begin + chunkSize, tasks);
            System.out.format("%d %d\n", begin, end);
            eService.execute(new Unidemensional(begin, end));
    }
    eService.shutdown();
    eService.awaitTermination(1, TimeUnit.HOURS);
  }
}