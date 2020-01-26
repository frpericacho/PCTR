#ifndef TAD_BICOLA_HPP
#define TAD_BICOLA_HPP
#include "ColaDinamica.hpp"

class bicola{
    public:
        Cola<int> push_inicio(int a);
        Cola<int> pop_fin(int a);

    private:
        Cola<int> c;
};

Cola<int> bicola::push_inicio(int a){
    Cola<int> aux;
    aux.push(a);
    while(!c.vacia()){
        aux.push(c.frente());
        c.pop();
    }
    return aux;
}

Cola<int> bicola::pop_fin(int a){
    Cola<int> aux;
    
    return aux;
}

#endif