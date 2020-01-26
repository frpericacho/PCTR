
import java.rmi.*;
import java.rmi.registry.*;

public class cLibros {
    public static void main(String[] args) throws Exception {

        iLibros RefObRemoto = (iLibros) Naming.lookup("//localhost/Servidor");

        RefObRemoto.inserta("nom1", "autor1", 1);
        RefObRemoto.inserta("nom2", "autor2", 2);
        RefObRemoto.inserta("nom3", "autor3", 3);
        RefObRemoto.inserta("nom4", "autor1", 4);
        RefObRemoto.inserta("nom5", "autor4", 5);
        RefObRemoto.borra("nom4");
    }
}