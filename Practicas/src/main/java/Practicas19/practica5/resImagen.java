package Practicas19.practica5;

import java.util.*;

public class resImagen {
	static int n = 2000;
	static int imagen[][] = new int[n][n];

	public static void operar() {
		int ianterior, janterior;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ianterior = i - 1;
				janterior = j - 1;
				if (janterior < 0)
					janterior = n - 1;
				if (ianterior < 0)
					ianterior = n - 1;
				imagen[i][j] = (4 * imagen[i][j] - imagen[(i + 1) % n][j] - imagen[i][(j + 1) % n]
						- imagen[ianterior][j] - imagen[i][janterior]) / 8;
			}
		}
	}

	public static void iniciarImagen() {
		Random aleatorio = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				imagen[i][j] = aleatorio.nextInt(254) + 1;
			}
		}
	}

	public static void imprimir() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(" " + imagen[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		iniciarImagen();
		Date d = new Date();
		long inicCronom = System.currentTimeMillis();
		d.setTime(inicCronom);
		operar();
		long finCronom = System.currentTimeMillis();
		d.setTime(finCronom);
		// imprimir();
		System.out.println("Tiempo: " + (finCronom - inicCronom) + " milisegundos");
	}

}
