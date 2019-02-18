#ifndef TAD_TREN_HPP
#define TAD_TREN_HPP
#include "PILA_ENLA.h"

class tren{
    public:
        tren(): izq(),der(){}
        void desp_izq();
        void desp_der();
        void elimnar_act();
        void insertar_act(int v);
        int obs_act();
        bool estado();

    private:
        Pila<int> izq,der;
};

void tren::desp_izq(){
    assert(!der.vacia());
    izq.push(der.tope());
    der.pop();
}

void tren::desp_der(){
    assert(!izq.vacia());
    der.push(izq.tope());
    izq.pop();
}

void tren::elimnar_act(){
    assert(!izq.vacia());
    izq.pop();
}

void tren::insertar_act(int v){
    izq.push(v);
}

int tren::obs_act(){
    assert(!izq.vacia());
    return izq.tope();
}

bool tren::estado(){
    return izq.vacia();
}

#endif