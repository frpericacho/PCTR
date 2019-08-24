package ejercicios_para_practicar;

public class Avion extends Thread
{
	private static Torre T = new Torre();
	private int id;

	public Avion(int id)
	{
		this.id = id;
	}

	public void run()
	{
		int pista = T.despega(id);
		try{ 
            sleep(1000);
        } catch (InterruptedException ie) {
        }
		T.despega_fin(id, pista);
		try{ 
            sleep(1000);
        } catch (InterruptedException ie) {
        }
		T.aterriza(id);
		T.aterriza_fin(id);
	}
}