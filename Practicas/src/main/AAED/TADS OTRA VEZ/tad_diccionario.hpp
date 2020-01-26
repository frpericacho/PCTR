#ifndef TAD_DICCIONARIO_HPP
#define TAD_DICCIONARIO_HPP
#include "lista.hpp"
#include <string>
#include <iostream>
using namespace std;

class trad{
    public:
        string da_palabra(){
            return palabra_esp;
        }

        void inserta_pal(string n){
            esp.insertar(n,esp.fin());
        }

    private:
        string palabra_esp;
        Lista<string> esp;
};

class diccionario{
    public:
        diccionario():ing(){}
        void insertar_ing(trad t);
        void insertar_esp(trad t,string n);
        void eliminar_esp(trad t);
        void consultar_ing(trad t);
        ~diccionario(){}

    private:
        string palabra_ing;
        Lista<trad> ing;
};

void  diccionario::insertar_ing(trad t){
    ing.insertar(t,ing.fin());
}

void diccionario::insertar_esp(trad t,string n){
    ing.elemento(ing.buscar(t)).inserta_pal(n);
}

void diccionario::eliminar_esp(trad t){
    assert(ing.primera()!=ing.fin());
    ing.elemento(ing.buscar(t));
}

void diccionario::consultar_ing(trad t){
    assert(ing.primera()!=ing.fin());
    while(ing.primera()!=ing.fin()){
        cout << ing.elemento(ing.buscar(t)).da_palabra() << endl;
    }
}

#endif