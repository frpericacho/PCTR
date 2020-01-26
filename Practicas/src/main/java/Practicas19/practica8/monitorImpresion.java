public class monitorImpresion {
    boolean[] impresoras = null;

    public monitorImpresion(int num) {
        impresoras = new boolean[num];
        for (int i = 0; i < impresoras.length; ++i)
            impresoras[i] = true;
    }

    private int libre() {
        for (int i = 0; i < impresoras.length; ++i) {
            if (impresoras[i])
                return i;
        }
        return -1;
    }

    public synchronized int inicio() {
        int id;
        while ((id = libre()) == -1) {
            try {
                wait();
            } catch (Exception ex) {
            }
        }
        System.out.println("Estoy imprimiendo en la impresora " + id + " " + Thread.currentThread());
        impresoras[id] = false;
        notifyAll();
        return id;
    }

    public synchronized void fin(int i) {
        impresoras[i] = true;
        System.out.println("Fin de impresion en la impresora " + i);
        notifyAll();
    }

}