package Practicas19.practica4;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class algEisenbergMcGuire implements Runnable{
    private static enum estados {esperar,activo,libre};
    static estados est[] = {estados.libre, estados.libre};
    private static int turn = 0;
    private static int inCS = 0;
    private int i = 0;

    public algEisenbergMcGuire(int i){
        this.i = i;
    }
    
    public void run(){
        int index;
        do{
            est[i] = estados.esperar;

            index = turn;
            while(index != i){
                if(est[index] != estados.libre){
                    index = turn;
                }else{
                    index = (index + 1) % 2;
                }
            }

            est[i] = estados.activo;
            index = 0;
            while((index < 2) && ((index == i) || (est[turn] != estados.activo))){
                index++;
            }
        }while(!((index >= 2) && ((turn == i) || (est[turn] == estados.libre))));
        turn = i;
        /// critical
        inCS++;
        System.out.println(Thread.currentThread().getName() + " " + inCS);
        inCS--;
        index = (turn + 1) % 2;
        while(est[index]==estados.libre){
            index = (index + 1) % 2;
        }
        turn = index;
        est[i] = estados.libre;
    }
    
    public static void main(String[] args){
        ExecutorService exe = Executors.newFixedThreadPool(2);
        exe.execute(new algEisenbergMcGuire(0));
        exe.execute(new algEisenbergMcGuire(1));
        exe.shutdown();
    }
}