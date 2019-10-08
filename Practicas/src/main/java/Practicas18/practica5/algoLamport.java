package practica5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class algoLamport implements Runnable{

	public int id_hilo; 
	public static final int hasta = 100;
	public static final int hilos = 2;
	public static volatile int cuenta = 0;


	private static volatile boolean[] elegir = new boolean[hilos];
																				 
	private static volatile int[] ticket = new int[hilos]; 
	
	public algoLamport(int id) {
		id_hilo = id;
	}

	public void run() {
        int escala = 2;

		for (int i = 0; i < hasta; i++) {

			cerrar(id_hilo);
				cuenta = cuenta + 1;
                System.out.println("El hilo " + id_hilo + " cuenta: " + cuenta);
                
				try {
					Thread.sleep((int) (Math.random() * escala));
				} catch (InterruptedException e) {}
			abrir(id_hilo);
		}

	}

    public void cerrar(int id) {
		elegir[id] = true;

		ticket[id] = max() + 1;
		elegir[id] = false;

		for (int j = 0; j < hilos; j++) {

			if (j != id) {
                while (elegir[j]) {Thread.yield(); }
                while (ticket[j] != 0 && (ticket[id] > ticket[j] || (ticket[id] == ticket[j] && id > j))) {
                    Thread.yield();
                 }
            }		 
		} 
	}

	private void abrir(int id) {
		ticket[id] = 0;
	}

	private int max() {
		int m = ticket[0];

		for (int i = 1; i < ticket.length; i++) {
			if (ticket[i] > m)
				m = ticket[i];
		}
		return m;
	}

	public static void main(String[] args) {

		for (int i = 0; i < hilos; i++) {
			elegir[i] = false;
			ticket[i] = 0;
		}

		ExecutorService exe = Executors.newFixedThreadPool(hilos);
		for(int i = 0;i < hilos; i++){
			exe.execute(new algoLamport(i));
		}
		exe.shutdown();
		System.out.println("fin");
	} 
}