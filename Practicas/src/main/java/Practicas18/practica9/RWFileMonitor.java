package practica9;
import java.io.File;
import java.io.RandomAccessFile;

public class RWFileMonitor{
  volatile int readers = 0;
  volatile boolean writing = false;
  static File f = new File("datos.dat");
  static RandomAccessFile fich;

  public RWFileMonitor()throws Exception{
    fich = new RandomAccessFile(f, "rw");
  }

  synchronized void StartRead() {
    while (writing)
      try {
         wait();
      } catch (InterruptedException e) {}
    readers = readers + 1;
    System.out.println("Lector inicia lectura...");
    notifyAll();
  }

  synchronized void EndRead() throws Exception{
    fich.seek(0);
    System.out.println(fich.readLine());
    readers = readers - 1;
    if (readers == 0) notifyAll();
    System.out.println("Lector finaliza lectura...");
  }

  synchronized void StartWrite() {
    while (writing || (readers != 0))
      try {
         wait();
      } catch (InterruptedException e) {}
    writing = true;
    System.out.println("Escritor inicia escritura...");
  }

  synchronized void EndWrite() throws Exception{
    fich.writeBytes("hola\n");
    writing = false;
    notifyAll();
    System.out.println("Escritor finaliza escritura...");
  }
}