package ejercicios_para_practicar;

public class examen extends Thread {
  private static Integer i = new Integer(1);
  private static int j = 0;
  private examen h;

  public examen() {
    j++;
  }

  public void run() {
    if (j < 500) {
      h = new examen();
      synchronized (i) {
        i++;
      }
      h.start();
      try {
        h.join();
      } catch (InterruptedException e) {
      }
    }
  }

  public static void main(String[] args) throws Exception {
    examen h = new examen();
    h.start();
    h.join();
    System.out.println(i.toString());
  }
}