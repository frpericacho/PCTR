package ejerciciosexamen.monitor;

public class Avion extends Thread {

    public static Torre T = new Torre();
    public int id;

    public Avion(int id){
        this.id = id;
    }

    public void run(){
        int pista = T.entraPdespege(id);
		try{ 
            sleep(1000);
        } catch (InterruptedException ie) {
        }
		T.salePdespege(id, pista);
		try{ 
            sleep(1000);
        } catch (InterruptedException ie) {
        }
		T.entraPaterrizaje(id);
		T.salePaterrizaje(id);
    }
}