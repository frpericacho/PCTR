package practica2;
import java.util.Scanner;

class paciente{
    String nombre, dni, direccion, compania;
    int telefono; 

    public paciente(){
        Scanner S = new Scanner(System.in);
        System.out.println("intoduzca el nombre del paciente");
        nombre = S.nextLine();
        System.out.println("introduzca el dni del paciente");
        dni = S.nextLine();
        System.out.println("introduzca la direccion del paciente");
        direccion = S.nextLine();
        System.out.println("introduzca la compañia del paciente");
        compania = S.nextLine();
        System.out.println("introduzca el telefono del paciente");
        telefono = S.nextInt();
        S.close();
    }
}