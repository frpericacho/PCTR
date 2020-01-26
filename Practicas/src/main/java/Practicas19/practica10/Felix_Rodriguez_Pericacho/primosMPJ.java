import mpi.*;
import java.lang.Math;
import java.util.Random;
 
public class primosMPJ{
    public static void main(String[] args){
      int myid,numprocs;
      long[] n = new long[1];
      long[] myhits = new long[1]; 
	    long[] totalhits = new long[1];
      Random myRand=new Random();
      double cx, cy;
      long i;
      long hits;
      long points = 10000000;
      n[0] =(long) points/8;

      
      MPI.Init(args);
      double iniT = MPI.Wtime();
      myid = MPI.COMM_WORLD.Rank(); 
      numprocs = MPI.COMM_WORLD.Size();

      MPI.COMM_WORLD.Bcast(n,0,1,MPI.LONG,0);

      hits=0;
      System.out.println((n[0]*(myid)) + " " + (n[0]*(myid+1)));
      for (i=(n[0]*(myid)); i<(n[0]*(myid+1)); i++){
        if(esPrimo(i)){
          hits++;  
        }
      }
      myhits[0]=hits;
       

	MPI.COMM_WORLD.Reduce(myhits,0,totalhits,0,1,MPI.LONG,
		MPI.SUM,0);
     
     double time=MPI.Wtime()-iniT;
     if (myid == 0){
        System.out.println("Numero total de numeros primos: "+totalhits[0]);
        System.out.println("En: "+time+" segundos");
     }
      MPI.Finalize();    
    }

    public static boolean esPrimo(long n){
    if(n<=1) return(false);
    for(long i=2; i<=Math.sqrt(n); i++)
      if(n%i == 0) return(false);
    return(true);
  } 
}
