package seminarios;

public class hilosYield extends Thread{
    static hilosYield[] hilos;
    boolean haFinalizado = false;
    public void run(){
        if(this.getName().equals("Thread-5")){
            boolean todosFinalizados;
            do{
                todosFinalizados = true;
                for(int i = 0; i < hilos.length; ++i){
                    if(hilos[i] != null && hilos[i].haFinalizado && !hilos[i].getName().equals("Thread-5")){
                        todosFinalizados = false;
                    }
                }
                if(!todosFinalizados)Thread.yield();
            }while(!todosFinalizados);
        }
        System.out.println("Hilo "+getName()+" finaliza run");
        haFinalizado = true;
    }
    public static void main(String[] args) throws Exception{
        hilos = new hilosYield[10];
        for(int i = 0;i < 10; ++i){
            hilos[i] = new hilosYield();
            hilos[i].start();
        }
        for(int i = 0;i < 10; ++i){
            hilos[i].join();
        }
    }
}