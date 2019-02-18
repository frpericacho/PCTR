package practica2;

class Elipse{
    public Elipse(double a,double b){
        this.a=a;
        this.b=b;
    }
    
    public double area(){
        return (this.a*this.b*Math.PI);
    }

    public double perimetro(){
        return (2*Math.PI*Math.sqrt(((Math.pow( this.a/2, 2))+Math.pow(this.b/2, 2))/2));
    }
 
    public boolean punto(double x,double y){
        if(((Math.pow(x, 2))/Math.pow(this.a, 2))+((Math.pow(y, 2))/Math.pow(this.b, 2))<=1){
            return true;
        }
        else
            return false;
    }


    private double a;
    private double b;
}