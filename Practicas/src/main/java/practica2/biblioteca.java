package practica2;
//import java.util.ArrayList;
import java.util.Scanner;

class biblioteca{
    public static void main(String[] args) {
        int a = 0,b = 1,aux = 0, tel = 0;
        int index = 0;
        String B_dni="",s_aux="";
        paciente[] pacientes = new paciente[100];
        Scanner S = new Scanner(System.in);
        do{
            do{
                System.out.println("introduzca una opcion:");
                System.out.println("1.- Insertar paciente");
                System.out.println("2.- Borrar paciente");
                System.out.println("3.- Consultar paciente");
                a = S.nextInt();
            }while(a < 1 || a > 3);
            switch(a){
                case 1: 
                if (index < 100 && pacientes[index]==null)
                    pacientes[index++] = new paciente(); break;
                case 2: 
                System.out.println("introduzca el dni del usuario que desea borrar");
                B_dni = S.nextLine();
                for(int i = 0;i < 100; ++i){
                    if(pacientes[i].nombre.equals(B_dni)){
                        pacientes[i]=null;
                    }
                }
                case 3: System.out.println("introduzca el parametro por el que desea buscar al paciente");
                        System.out.println("1.- nombre");
                        System.out.println("2.- dni");
                        System.out.println("3.- direccion");
                        System.out.println("4.- compania");
                        System.out.println("5.- telefono");
                        aux = S.nextInt();
                        switch(aux){
                            case 1: s_aux = S.nextLine();
                                    for(int i=0;i<100;++i){
                                        if(pacientes[i].nombre.equals(s_aux)){
                                            System.out.println("nombre: "+pacientes[i].nombre);
                                            System.out.println("dni: "+pacientes[i].dni);
                                            System.out.println("direccion: "+pacientes[i].direccion);
                                            System.out.println("compania: "+pacientes[i].compania);
                                            System.out.println("telefono: "+pacientes[i].telefono);
                                        }
                                    }
                                    break;
                            case 2: s_aux = S.nextLine();
                                    for(int i=0;i<100;++i){
                                        if(pacientes[i].dni.equals(s_aux)){
                                            System.out.println("nombre: "+pacientes[i].nombre);
                                            System.out.println("dni: "+pacientes[i].dni);
                                            System.out.println("direccion: "+pacientes[i].direccion);
                                            System.out.println("compania: "+pacientes[i].compania);
                                            System.out.println("telefono: "+pacientes[i].telefono);
                                        }
                                    }
                                    break;
                            case 3: s_aux = S.nextLine();
                                    for(int i=0;i<100;++i){
                                        if(pacientes[i].direccion.equals(s_aux)){
                                            System.out.println("nombre: "+pacientes[i].nombre);
                                            System.out.println("dni: "+pacientes[i].dni);
                                            System.out.println("direccion: "+pacientes[i].direccion);
                                            System.out.println("compania: "+pacientes[i].compania);
                                            System.out.println("telefono: "+pacientes[i].telefono);
                                        }
                                    }
                                    break;
                            case 4:  s_aux = S.nextLine();
                                    for(int i=0;i<100;++i){
                                        if(pacientes[i].compania.equals(s_aux)){
                                            System.out.println("nombre: "+pacientes[i].nombre);
                                            System.out.println("dni: "+pacientes[i].dni);
                                            System.out.println("direccion: "+pacientes[i].direccion);
                                            System.out.println("compania: "+pacientes[i].compania);
                                            System.out.println("telefono: "+pacientes[i].telefono);
                                        }
                                    }
                                    break;
                            case 5: tel = S.nextInt();
                                    for(int i=0;i<100;++i){
                                        if(pacientes[i].telefono==tel){
                                            System.out.println("nombre: "+pacientes[i].nombre);
                                            System.out.println("dni: "+pacientes[i].dni);
                                            System.out.println("direccion: "+pacientes[i].direccion);
                                            System.out.println("compania: "+pacientes[i].compania);
                                            System.out.println("telefono: "+pacientes[i].telefono);
                                        }
                                    }
                                    break;
                            default: System.out.println("valor erroneo");
                        }
            }
            System.out.println("desea continuar?(1 para seguir)");
            b = S.nextInt();
            S.close();
        }while(b==1);
    }
}