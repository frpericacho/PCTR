#ifndef TAD_TEXTO_HPP
#define TAD_TEXTO_HPP
#include <cassert>
#include "PILA_ENLA.h"
#include <string>

class texto{
    public:
        texto(std::string cad){
            int i = 0;
            while(cad[i] != '\0'){
                p.push(cad[i]);
                i++;
            }
        };

        void mostrartexto(){
            Pila<char> aux;
            while(!p.vacia()){
                if(p.tope()=='@'){
                    while(p.tope()=='@'){
                        p.pop();
                    }
                    p.pop();
                }else{
                    if(p.tope()=='#'){
                        while(p.tope() !='\n'){
                            p.pop();
                        }
                        p.pop();
                    }else{
                        aux.push(p.tope());
                        p.pop();
                    }
                }
            }

            while(!aux.vacia()){
                std::cout << aux.tope();
                aux.pop();
            }
        }

    private:
        Pila<char> p;
};

#endif