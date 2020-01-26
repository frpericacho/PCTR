package ejercicios_para_practicar;

public class avion extends Thread {
    public static torre t = new torre();
    public int id;

    public avion(int id) {
        this.id = id;
    }

    public void run() {
        int pista = t.entra_PD(id);
        t.sale_PD(id, pista);
        try {
            sleep(1000);
        } catch (InterruptedException ie) {
        }
        t.entra_PA(id);
        t.entra_PD(id);
    }

}