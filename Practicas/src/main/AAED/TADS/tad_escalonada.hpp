#ifndef TAD_ESCALONADA_HPP
#define TAD_ESCALONADA_HPP
#include "lista.hpp"

class intervalo{
    public:
        const int da_x(){return x;}
        const int da_y(){return y;}
        void cambia_x(int v){x = v;}
        void cambia_y(int v){y = v;}

    private:
        int x,y;
};

class escalonada{
    public:
        escalonada(): 
        funcion(){};
        void nuevo_salto(intervalo i); //done
        void elimina_escalon(int x); //done
        int cal_valor(int x); //donde
        int cal_min(escalonada esc); //done
        int cal_max(escalonada esc); //done

    private:
        Lista<intervalo> funcion;
};

int escalonada::cal_min(escalonada esc){
    Lista<intervalo>::posicion p;
    int min;
    for(p = esc.funcion.primera(); p != esc.funcion.fin(); p = esc.funcion.siguiente(p)){
        if(esc.funcion.elemento(p).da_y() < min){
            min = esc.funcion.elemento(p).da_y();
        }
    }
    return min;
}

int escalonada::cal_max(escalonada esc){
    Lista<intervalo>::posicion p;
    int max;
    for(p = esc.funcion.primera(); p != esc.funcion.fin(); p = esc.funcion.siguiente(p)){
        if(esc.funcion.elemento(p).da_y() > max){
            max = esc.funcion.elemento(p).da_y();
        }
    }
    return 0;
}

int escalonada::cal_valor(int x){
    Lista<intervalo>::posicion p = funcion.primera();
    while(funcion.elemento(p).da_x() != x){
        p = funcion.siguiente(p);
    }
    return funcion.elemento(p).da_y();
}

void escalonada::nuevo_salto(intervalo i){
    if(funcion.primera() == funcion.fin()){
        funcion.insertar(i,funcion.fin());
    }else{
        Lista<intervalo>::posicion p;
        p = funcion.primera();
        while(funcion.elemento(p).da_x() < i.da_x()){
            p = funcion.siguiente(p);
        }
        if(funcion.elemento(p).da_x() == i.da_x()){
            funcion.elemento(p).cambia_x(i.da_y());
        }else{
            funcion.insertar(i,p);
        }
    }
}

void escalonada::elimina_escalon(int x){
    Lista<intervalo>::posicion p;
    for(p = funcion.primera(); p != funcion.fin(); p = funcion.siguiente(p)){
        if(funcion.elemento(p).da_x() == x){
            funcion.elemento(p);
        }
    }
}

#endif