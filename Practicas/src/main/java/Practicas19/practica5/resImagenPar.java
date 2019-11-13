package Practicas19.practica5;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

class resImagenPar implements Runnable {
	int fila;
	static int n = 20000;
	static int matriz[][] = new int[n][n];
	static int resaltada[][] = new int[n][n];

	resImagenPar(int fila) {
		this.fila = fila;
	}

	public static void iniciarImagen() {
		Random aleatorio = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = aleatorio.nextInt(254) + 1;
			}
		}
	}

	public void run() {
		for (int j = 0; j < matriz[0].length; ++j) {
			if (fila > 0 && fila < matriz.length - 1 && j > 0 && j < matriz[0].length - 1)
				resaltada[fila][j] = (4 * matriz[fila][j] - matriz[fila + 1][j] - matriz[fila][j + 1]
						- matriz[fila - 1][j] - matriz[fila][j - 1]) / 8;
			else
				resaltada[fila][j] = matriz[fila][j];
		}
	}

	public static void main(String[] args) throws IOException {
		iniciarImagen();
		ExecutorService exe = Executors.newFixedThreadPool(4);
		long ini = System.currentTimeMillis();
		for (int i = 0; i < matriz.length; ++i) {
			exe.execute(new resImagenPar(i));
		}
		exe.shutdown();
		while (!exe.isTerminated()) {
		}
		long fini = System.currentTimeMillis();
		System.out.println("Tiempo: " + (fini - ini) + " milisegundos");
	}
}