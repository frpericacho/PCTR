package ejercicios_para_practicar;

import java.util.concurrent.*;
public class ejer extends Thread {
  static int x = 0;
  Semaphore cerrojo = new Semaphore(1);
  int t;
  public ejer(int t){this.t=t;}
  public void run(){
    switch(t){
      case 0:{try{cerrojo.acquire();}
      catch(InterruptedException e){} x++;cerrojo.release();}
      case 1:{try{cerrojo.acquire();}
      catch(InterruptedException e){} x--;cerrojo.release();}
    }
  }
  
  public static void main(String[] args) throws Exception{
    ejer[] lista = new ejer[10000];
    for(int i=0;i<lista.length;i++){
      lista[i] = new ejer(1);
      lista[i].start();
    }
    for(int i=0;i<lista.length;i++){
      lista[i].join();
    }
    System.out.print(x);
  }
}