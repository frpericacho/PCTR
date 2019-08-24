package ejercicios_para_practicar;

public class Aeropuerto
{
	public static void main(String[] args) {
		Avion []aviones = new Avion[10000];
		for(int i=0; i<10000; i++)
		{
			aviones[i] = new Avion(i);
			aviones[i].start();
		}

		for(int i=0; i<10000; ++i)
		{
			try
			{
				aviones[i].join();
			}catch(InterruptedException ie){}
		}
	}
}