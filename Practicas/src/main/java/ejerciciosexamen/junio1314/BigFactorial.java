package ejerciciosexamen.junio1314;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class BigFactorial implements Runnable {
    public static int res = 0;
    public static int ini = 0, fin = 0;
    public static BigInteger fac = new BigInteger("1000");
    public static int cores = Runtime.getRuntime().availableProcessors();

    public BigFactorial(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public static int calcula() {
        int val = fac.intValue();

        if (val / cores != 0) {
            int aux = val / cores;
            aux = val - aux;
            return val;
        }
        else
            return 0;
    }

    public static void main(String[] args) {
        int partes = fac.intValue()/cores;
        int resto = calcula();

        ExecutorService exe = Executors.newFixedThreadPool(cores);
        for(int i = 0; i < cores; i++){
			if(i==0){
                BigFactorial factorial = new BigFactorial(0, partes+resto);
                exe.execute(factorial);
            }
            else{
                BigFactorial factorial = new BigFactorial((partes+resto+partes)*i,(partes+resto+partes)*(i+1));
                exe.execute(factorial);
            }
        }

        exe.shutdown();

        System.out.println(res);
    }

    @Override
    public void run() {
        int cont = 0;
        ReentrantLock ren = new ReentrantLock();

        for(int i = ini; i < fin; i++){
            cont  *= i; 
        }
        
        ren.lock();
            try {
                res += cont;
            } catch (Exception e) {}
        ren.unlock();
    }
}

/*
 * import java.math.BigInteger; public class usoBigInteger { public static void
 * main(String[] args) { //Se muestra el resultado del factorial de 500 algo
 * //imposible de hacer con los tipos primitivos de java
 * System.out.println(factorial(new BigInteger("500")));
 * System.out.println(BigSuma()); }
 * 
 * //Funcion recursiva para el calculo del factorial public static BigInteger
 * factorial(BigInteger n) { //Si el parametro n es 1, se retorna 1 ya que el
 * //factorial de 1 es 1 por definicion if(n.equals(BigInteger.ONE)) { return
 * BigInteger.ONE; } return n.multiply(factorial(n.subtract(BigInteger.ONE))); }
 * public static BigInteger BigSuma(){ //Ejemplo de cómo sumar dos enteros
 * grandes BigInteger a = new BigInteger("1000000"); BigInteger b = new
 * BigInteger("10000000"); return (a.add(b)); } }
 */