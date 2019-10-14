package Practicas19.practica2;


public class hebra extends Thread{
    public static int n = 0;
    public boolean tipo;

    public hebra(boolean tipo){
        this.tipo = tipo;
    }

    public void run(){
        if(tipo){
            for(int i = 0;i < 10000; ++i){
                ++n;
            }
        }else{
            for(int i = 0;i < 10000; ++i){
                --n;
            }
        }
        System.out.println(n);
    }

}

//El ejercicio 5 se llama: expresiones_lambda.java

//Version 1 de lamda
/*
new thread(()->{
    //codigo
}).start();
*/

//Version 2 de lamda
/*
thread t = new thread(()->{
    //codigo
});
t.start();
*/

//Version 3 de lamda
/*
Runnable runable = ()->{
    //codigo
};
Thread t = new Thread(runable);
t.start();
*/