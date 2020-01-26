
public class Conductores {
    private static Conductor[] conductores = new Conductor[100];
    private static boolean[] ocupado = new boolean[100];

    public void iniciarOcupados() {
        for (int i = 0; i < 100; i++)
            ocupado[i] = false;
    }

    synchronized void introducirConductor(Conductor cond) {
        int i = 0;
        while (ocupado[i])
            i++;
        if (i < 100) {
            conductores[i] = cond;
            ocupado[i] = true;
        } else {
            System.out.println("No hay mas espacio en la base de datos");
        }
    }

    synchronized void borrarConductor(int id) {
        int i = 0;
        while (i < 100) {
            if(ocupado[i]){
                if (conductores[i].get_idConductor() == id) {
                    conductores[i] = null;
                    ocupado[i] = false;
                    System.out.println("Borrado");
                }
            }
            i++;
        }
    }

    synchronized String buscarConductor(int id) {
        int i = 0;
        String nombre = null;
        while (i < 100) {
            if (ocupado[i]) {
                if (conductores[i].get_idConductor() == id) {
                    nombre = conductores[i].get_nombreConductor();
                }
            }
            i++;
        }
        return nombre;
    }
}