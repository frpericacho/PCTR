package Practicas19.practica6;

public class cuentaCorrienteSegura 
{
  private int cod;     
  private double saldo; 
  private Object cerrojo = new Object();
  public cuentaCorrienteSegura (){} 
  
  public cuentaCorrienteSegura (int id, double disponible)
  { 
    cod=id;
    saldo=disponible;
  }
  
  public double Saldo()
  {
      return (saldo);
    }

  public void Deposito (double Cantidad)
  {
    if (saldo>0)
      synchronized(cerrojo){
        saldo=saldo+Cantidad;
      }
  }
  
  public boolean Reintegro (double Cantidad)
  {
    if((Cantidad <=0)||(Cantidad>saldo))
      return (false);
    else {
      synchronized(cerrojo){
        saldo=saldo-Cantidad;
      }
      return (true);          
    }
  }
}