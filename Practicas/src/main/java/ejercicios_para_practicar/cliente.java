package ejercicios_para_practicar;

public class cliente implements Runnable{
    public int id;
    public static lineaCajas objeto = new lineaCajas();

    public cliente(int id){
        this.id = id;
    }

    @Override
    public void run() {
        lineaCajas.recibe();
        System.out.println("El cliente "+this.id+" ha sido atentido.");
    }
}