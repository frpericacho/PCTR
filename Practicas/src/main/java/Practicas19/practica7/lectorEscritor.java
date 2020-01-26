public class lectorEscritor{
	static boolean escribiendo=false;
	static int lectores=0;
	public lectorEscritor(){

	}

	public synchronized void leer_ini()throws Exception{
		while(escribiendo){
			wait();
		}
		lectores++;
		notifyAll();
	}
	public synchronized void leer_fin()throws Exception{
		lectores--;
		if(lectores==0)
			notifyAll();
	}
	public synchronized void escribir_ini()throws Exception{
		while(escribiendo || lectores!=0){
			wait();
		}
		escribiendo=true;
	}
	public synchronized void escribir_fin()throws Exception{
		escribiendo=false;
		notifyAll();
	}

}