package seminarios;
//en el monitor tendremos todos los cubiertos mediante un vector de booleanos

class filosofos implements Runnable{
    static boolean[] libre = new boolean[10];

    public static void main(String[] args) {
        
    }

    public void run(){

    }

    public synchronized void soltarCubiertos(int i) throws Exception{
        libre[i]=libre[(i+1)%5]=true;
        notifyAll();
    }

    public synchronized void cogerCubiertos(int i) throws Exception {
        while(libre[i]==false || libre[(i+1)%5]==false){
            wait();
        }
        libre[i]=libre[(i+1)%5]=false;
    }

    public void inicializar(){
        for(int i = 0; i < 5; i++){
            libre[i] = true;
        }
    }
}