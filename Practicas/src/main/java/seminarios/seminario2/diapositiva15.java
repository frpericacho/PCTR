package seminarios.seminario2;

class diapositiva15 extends Thread{
    int veces;
    boolean par;
    public static void main(String[] args) throws Exception {
        diapositiva15 hilo1 = new diapositiva15(10,true);
        diapositiva15 hilo2 = new diapositiva15(10, false);
        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();
    }
    public void run(){
        for(int i = 0; i < 3* veces; ++i){
            if(par){
                if(i%2==0)
                    System.out.println(i);
            }else{
                if(i%2==1)
                    System.out.println(i);
            }
        }
    }
    public diapositiva15(int veces,boolean par){
        this.veces = veces;
        this.par = par;
    }
}