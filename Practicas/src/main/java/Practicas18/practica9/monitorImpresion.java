package practica9;

public class monitorImpresion{
    private static boolean estado[] = new boolean[3];
    
    public monitorImpresion(){
        for(int i = 0; i < 3; i++){
            estado[i] = true;
        }
    }

    synchronized void usar(int n){
        try {
            while(estado[n] == false)
                wait();
            estado[n] = false;
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    synchronized void liberar(int n){
        try {
            estado[n] = true;
            Thread.sleep(1000);
            notifyAll();
        } catch (Exception e) {}

    }
}