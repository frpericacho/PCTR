package practica2;

class Ack{
    public static void main(String[] args) {
        double res = 0;
        int n = 0,m = 0;
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        res = ackerman(n, m);
        System.out.println("El resultado es: "+res);
    }
    public static int ackerman(int n,int m){
        if(n==0) return(m+1);
        else if(m==0) return(ackerman(n-1, 1));
        else return(ackerman(n-1, ackerman(n, m-1)));
    }
}