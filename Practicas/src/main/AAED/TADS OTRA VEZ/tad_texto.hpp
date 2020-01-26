#ifndef TAD_TEXTO_HPP
#define TAD_TEXTO_HPP  
#include "PILA_ENLA.h"
#include <iostream>
#include <string>
using namespace std;

class texto{
    public:
        texto(string cad){
            int i = 0;
            while(cad[i] != '\0'){
                p.push(cad[i]);
                i++;
            }
        };

        void mostrar_texto();

    private:
        Pila<char> p;
};

void texto::mostrar_texto(){
    Pila<char> aux;
    while(!p.vacia()){
        if(p.tope()=='@'){
            while(p.tope()=='@')
                p.pop();
            p.pop();
        }else if(p.tope()=='#'){
            while(p.tope()=='#')
                p.pop();
            while(p.tope()!='\n')
                p.pop();
            p.pop();
        }
        else{
            aux.push(p.tope());
            p.pop();
        }
    }

    while(!aux.vacia()){
        cout << aux.tope();
        aux.pop();
    }

}

#endif