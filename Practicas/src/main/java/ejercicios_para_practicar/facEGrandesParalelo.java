package ejercicios_para_practicar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.math.BigInteger;

public class facEGrandesParalelo implements Runnable {
    public ReentrantLock lock = new ReentrantLock();
    public static BigInteger resultado = new BigInteger("1");
    public BigInteger parte = new BigInteger("1");
    public int inf, sup;
    public static int cores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        int nTareas = Runtime.getRuntime().availableProcessors();

        int tVentana = 500 / nTareas;
        int resto = 500 % nTareas;
        int inf = 0;
        int sup = tVentana;

        ExecutorService exe = Executors.newFixedThreadPool(nTareas);
        for (int i = 0; i < nTareas; ++i) {
            if (i != nTareas - 1)
                exe.execute(new facEGrandesParalelo(inf, sup));
            else
                exe.execute(new facEGrandesParalelo(inf, sup + resto));
            inf = sup;
            sup += tVentana;
        }
        exe.shutdown();
        while (!exe.isTerminated()) {
        }

        System.out.println("Factorial de 500" + ":\n" + resultado);

    }

    public void run() {
        for (int i = inf; i < sup; ++i) {
            BigInteger aux = new BigInteger(Integer.toString(i + 1));
            parte = parte.multiply(aux);
        }

        lock.lock();
        try {
            resultado = resultado.multiply(parte);
        } finally {
            lock.unlock();
        }
    }

    public facEGrandesParalelo(int inf, int sup) {
        this.inf = inf;
        this.sup = sup;
    }
}

class usoBigInteger {
    public static void main(String[] args) {
        // Se muestra el resultado del factorial de 500 algo
        // imposible de hacer con los tipos primitivos de java
        System.out.println(factorial(new BigInteger("500")));
        System.out.println(BigSuma());
    }

    // Funcion recursiva para el calculo del factorial
    public static BigInteger factorial(BigInteger n) {
        // Si el parametro n es 1, se retorna 1 ya que el
        // factorial de 1 es 1 por definicion
        if (n.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }

    public static BigInteger BigSuma() {
        // Ejemplo de cómo sumar dos enteros grandes
        BigInteger a = new BigInteger("1000000");
        BigInteger b = new BigInteger("10000000");
        return (a.add(b));
    }
}