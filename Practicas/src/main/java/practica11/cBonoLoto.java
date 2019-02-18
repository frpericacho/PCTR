package practica11;

import java.rmi.Naming;

public class cBonoLoto{
    public static int[]a = new int[6];
    public static void main(String[] args) throws Exception{
        
        iBonoLoto refObjRem = (iBonoLoto) Naming.lookup("Server");
        
        for(int i = 0; i < 6; i++){
            a[i] = (int)(Math.random() + 1);
        }
        
        if(refObjRem.acierto(refObjRem.genera())){
            System.out.println("El cliente ha acertado la bonoloto");
        }else{
            System.out.println("El cliente ha fallado la bonoloto");
        }
    }
}