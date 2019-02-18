package ejerciciosexamen.septiembre1112;

class matSum extends Thread{
    public static Object cerrojo = new Object();
    public matSum(int fila){
        this.fila = fila;
    }

    public void run(){//aqui hacemos la suma de matrices
        int cont=0;

        for(int i = 0;i < 10; i++){
            cont += pruebamatSum.mat[fila][i];
        }
        
        synchronized(cerrojo){//palabra reservada syncronzed consigue que un solo hilo alcanze el bloque de codigo
           pruebamatSum.cont += cont;//esto lo lleva acabo usando un object como cerrojo
        }
    }

    private int fila;
}
