package ejerciciosexamen.junio1314;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class sIBEX5 extends UnicastRemoteObject implements iIBEX5{

    private static final long serialVersionUID = -7473033990738236758L;
    private static int TRI;
    private static int CHN;
    private static int BMM;
    private static int CRR;
    private static int TDC;
    private static Object o = new Object();
    
    public void consultar_todos(){
        synchronized(o){
            System.out.println("TRINCOSA"+TRI);
            System.out.println("CHORIZOS NACIONALES"+CHN);
            System.out.println("BANCO MALO PERO MALO"+BMM);
            System.out.println("CORRUPTOLANDIA"+CRR);
            System.out.println("TOMA EL DINERO Y CORRE S.A."+TDC);
        }
    }

    public void consultar_uno(int val){
        synchronized(o){
            switch(val){
                case 1:System.out.println(TRI);
                break;
                case 2:System.out.println(CHN);
                break;
                case 3:System.out.println(BMM);
                break;
                case 4:System.out.println(CRR);
                break;
                case 5:System.out.println(TDC);
                break;
            }
        }
    }

    private static void empieza(int tri,int chn,int bmm,int crr,int tdc) throws RemoteException{
        TRI=tri;
        CHN=chn;
        BMM=bmm;
        CRR=crr;
        TDC=tdc;
    }
    
    public sIBEX5() throws RemoteException{
        super();
    }

    public sIBEX5(int server) throws RemoteException{
        super(server);
    }

    public static void main(String[] args) throws Exception{
        sIBEX5 server = new sIBEX5(8080);
        empieza(1, 1, 1, 1, 1);
        Naming.bind("servidor", server);
        System.out.println("el servidor esta listo");
    }
}
/*
 interface iIBEX5 extends Remote{
    void consultar_todos() throws RemoteException;
    void consultar_uno(int val) throws RemoteException;
}*/