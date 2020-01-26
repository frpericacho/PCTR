package practica3;

class poligono{

    public poligono(int n){
        puntos = new Punto[n];
        size = 0;
    }

    public void mas_puntos(Punto p){
        if(size < puntos.length)
            puntos[size++]=p;
    }

    public void nuevo_punto(){
        puntos[1].x = Math.random();
        puntos[1].y = Math.random();
    }

    public double perimetro(){
        double per=0;
        for(int i = 1; i <= size; ++i){
            System.out.println(lado(i));
            per += lado(i);
        }
        return per;
    }

    public double lado(int lado){
        if(lado==size){
            return Math.hypot(puntos[lado-1].x - puntos[0].x, puntos[0].y - puntos[lado-1].y);
        }else{
            return Math.hypot(puntos[lado-1].x - puntos[lado].x, puntos[lado].y - puntos[lado-1].y);
        }
    }

    protected boolean regular(){
        double a = lado(1);
        for(int i = 2;i <= size; ++i){
            if(a != lado(i)){
                return false;
            }
        }
        return true; 
    }

    public int size(){
        return size;
    }

    private Punto[] puntos;
    private int size;
}