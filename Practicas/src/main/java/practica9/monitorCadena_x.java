package practica9;

public class monitorCadena_x {
    private int i = 0,cont = 0,p = 0,s = 0;
    private Mat[] buffer = null;
    
    public synchronized void poner(double[][] v)throws Exception{
        while(cont == i){
            wait();
        }
        buffer[p] = new Mat(v);
        p = (p+1)%i;
        cont++;
        notifyAll();
    }

    public synchronized double[][] sacar()throws Exception{
        Mat v;
        while(cont == 0){
            wait();
        }
        v = buffer[s];
        s = (s+1)%i;
        cont--;
        notifyAll();
        return v.matriz;
    }

    public monitorCadena_x(int i){
        this.i = i;
        buffer = new Mat[i];
    }
}

class Mat{
    public double[][] matriz = new double[10][10];

    public Mat(double[][] mat){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				matriz[i][j]=mat[i][j];
			}
		}
    }
}