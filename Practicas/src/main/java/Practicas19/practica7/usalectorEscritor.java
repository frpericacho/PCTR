import java.util.*;
import java.util.concurrent.*;

public class usalectorEscritor implements Runnable {

	private int tipohilo;
	static int n = 0;
	static lectorEscritor m = new lectorEscritor();

	public usalectorEscritor(int tipohilo) {
		this.tipohilo = tipohilo;
	}

	public void run() {
		try {
			switch (tipohilo) {
			case 0:
				for (int i = 0; i < 10; i++) {
					m.leer_ini();
					System.out.println("Lector leyendo la variable n:" + n);
					m.leer_fin();
				}
				break;
			case 1:
				m.escribir_ini();
				n++;
				System.out.println("Escritor escribiendo n:" + n);
				m.escribir_fin();
				break;
			}
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) throws Exception {
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			exe.execute(new usalectorEscritor(i % 2));
		}
		exe.shutdown();
		exe.awaitTermination(1, TimeUnit.DAYS);

	}
}