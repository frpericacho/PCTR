package practica3;

public class cuadrado extends poligono{

    public cuadrado(){
        super(4);
    }
    
    public void mas_puntos(Punto p){
        super.mas_puntos(p);
        if(size()==4 && !regular()){
            throw new IndexOutOfBoundsException("no es un cuadrado");
        }
    }

}