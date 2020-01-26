package seminarios;

class cruzafuncion_clase{
    public static void main(String[] args) {
        double a = 2,b = 3,error = 0.1;
        double f_a,f_c,c = 0;

        while(b-a > error){
            c = (a+b)/2;
            f_a = f2(a);
            f_c = f2(c);
            if(f_a * f_c > 0){
                a = c;
            }else if(f_a * f_c < 0){
                b = c;
            }else{
                a = b = c;
            }
        }
        System.out.println("el resultado es: "+c+" con un margen de error de: "+error);
    }
    static double f2(double x){ return ((x*x)-5);}
}