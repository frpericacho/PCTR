package ejerciciosexamen.junio1112;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.Scanner;

/**
 * cMatriz
 */
public class cMatriz {
  private static iMatriz look_up;

  public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
    Scanner sc = new Scanner(System.in);
    look_up = (iMatriz) Naming.lookup("//localhost/cinema");
    String movie = args[0];
    String seat = args[1];
    String response = "412";
    while (response.equals("412")) {
      System.out.println("in");
      response = look_up.buyTicket(movie, Integer.parseInt(seat));
      switch (response) {
      case "200":
        System.out.println("Your buy was succesful");
        break;
      case "404":
        System.out.println("The movie was not found");
        break;
      case "412":
        System.out.println("The seat you choose is no available choose another");
        seat = sc.nextLine();
        break;
      }
    }
    sc.close();
  }

}