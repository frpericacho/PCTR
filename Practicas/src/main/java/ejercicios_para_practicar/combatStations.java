package ejercicios_para_practicar;

import static java.lang.Thread.sleep;

import java.util.Random;

public class combatStations implements Runnable {

    int id;
    static CIWS fragata;

    public combatStations(int id) {
        this.id = id;
    }

    public void run() {
        while(true) {
            CIWS ciws = fragata.pedirCIWS(id);
            try{
                sleep(new Random().nextInt(10000));
            }catch(Exception e){
                e.printStackTrace();
            }
            fragata.dejarCIWS(id, ciws);
            try{
                sleep(new Random().nextInt(10000));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

   
}