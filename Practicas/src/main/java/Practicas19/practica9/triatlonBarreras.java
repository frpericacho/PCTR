import java.util.Random;
import java.util.concurrent.*;

class triatlonBarreras implements Runnable
{
    public static long init,finit;
    private static int nParticipantes;
    private static double []tiempos;
    private final CyclicBarrier natacion_ciclismo;
    private final CyclicBarrier ciclismo_correr;
    private int id;
    private final Random R;
    
    public triatlonBarreras(int id, int nParticipantes , CyclicBarrier natacion_ciclismo, 
            CyclicBarrier ciclismo_correr)
    {
        this.R = new Random();
        this.tiempos = new double[nParticipantes];
        this.id = id;
        this.natacion_ciclismo = natacion_ciclismo;
        this.ciclismo_correr = ciclismo_correr;
        for(int i=0; i<nParticipantes; ++i)
        {
            tiempos[i] = 0;
        }
    }
    
    public void run()
    {
        init = System.currentTimeMillis();
        int time1 = R.nextInt(100)+100;
        try
        {
            Thread.sleep(time1);
        }catch(InterruptedException ex){}
        
        tiempos[id-1] += time1;
        finit = System.currentTimeMillis();
        System.out.println("termina natacion " + id + " con: " + (finit-init));
        try {
            natacion_ciclismo.await();
        }catch(InterruptedException | BrokenBarrierException ex) {}
        
        init = System.currentTimeMillis();
        int time2 = R.nextInt(100)+100;
        
        try
        {
            Thread.sleep(time2);
        }catch(InterruptedException ex){}
        
        tiempos[id-1] += time2;
        finit = System.currentTimeMillis();

        System.out.println("termina ciclismo " + id + " con : " + (finit-init));
        try
        {
            ciclismo_correr.await();
        }catch(InterruptedException | BrokenBarrierException ex){}
        init = System.currentTimeMillis();
        int time3 = R.nextInt(100)+100;
        try
        {
            Thread.sleep(time3);
        }catch(InterruptedException ex){}
        
        tiempos[id-1] += time3;
        finit = System.currentTimeMillis();
        System.out.println("termina carrera " + id + " con : " + (finit-init));
    }
    
    public static int ganador()
    {
        double min = tiempos[0];
        int id_min = 0;
        for(int i=0; i<tiempos.length; ++i)
        {
            if(tiempos[i] < min)
            {
                id_min = i;
                min = tiempos[i];
            }
        }
        
        return id_min;
    }

    public static void main(String[] args) 
    {
        nParticipantes = 100;
        tiempos = new double[nParticipantes];
        CyclicBarrier natacion_ciclismo = new CyclicBarrier(nParticipantes);
        CyclicBarrier ciclismo_correr = new CyclicBarrier(nParticipantes);

        ExecutorService exe = Executors.newFixedThreadPool(nParticipantes);
        for(int i = 0; i < nParticipantes; i++){
            exe.execute(new triatlonBarreras(i+1, nParticipantes, natacion_ciclismo, ciclismo_correr));
        }
        exe.shutdown();
        while(!exe.isTerminated());
        
        System.out.println("El ganador del triatlon es el participante numero " + ganador());

    }
}