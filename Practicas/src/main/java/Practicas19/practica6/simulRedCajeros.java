
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class simulRedCajeros {
    private static cuentaCorrienteSegura cuenta = new cuentaCorrienteSegura(1, 100);

    public simulRedCajeros() {
    }

    public static void main(String[] args) {

        Runnable task = () -> {
            cuenta.Deposito(10);
            cuenta.Reintegro(10);
        };

        ExecutorService exe = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            exe.execute(task);
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;

        System.out.println(cuenta.Saldo());
    }
}