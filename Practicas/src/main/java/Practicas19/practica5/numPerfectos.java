package Practicas19.practica5;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class numPerfectos implements Callable<Integer>{
    public int ini,fin;
    public static ArrayList<Future<Integer>> res = new ArrayList<Future<Integer>>();
    public static int total=0;

    public numPerfectos(int inicio, int fini) {
        this.ini = inicio;
        this.fin = fini;
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int tareas = Runtime.getRuntime().availableProcessors();
        ExecutorService exe = Executors.newFixedThreadPool(tareas);
        int partes = (n+tareas-1)/tareas;

        for(int i = 0; i < tareas; i++){
            int inicio = partes * i;
            int fini = Math.min(partes*(i+1),n);
            res.add(exe.submit(new numPerfectos(inicio,fini)));
        }
        exe.shutdown();
        while(!exe.isTerminated());

        int resultado = 0;

        for(int i = 0; i < tareas; i++) {
            try {
                resultado += res.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
			}
        }
        System.out.println("Hay: " + resultado + " numeros perfectos");
    }

    @Override
    public Integer call() throws Exception {
        int cont = 0;
        for(int i = ini; i <= fin; i++){
            int aux = 0;
            for(int j = 1; j < i; j++){
                if(i%j == 0)
                    aux += j;
            }
            if(i == aux && i != 0){
                cont++;
            }
        }
        return cont;
    }
}