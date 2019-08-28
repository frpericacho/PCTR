package ejercicios_para_practicar;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaLineaCajas {
    public static void main(String[] args) {
        int nCores = Runtime.getRuntime().availableProcessors();
        lineaCajas.inicializar();
        ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(nCores, nCores, 6000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 250; ++i)
            ejecutor.execute(new cliente(i));
        ejecutor.shutdown();
        while (!ejecutor.isShutdown())
            ;
    }

}