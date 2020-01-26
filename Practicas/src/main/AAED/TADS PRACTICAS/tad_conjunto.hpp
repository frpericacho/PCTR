#ifndef TAD_CONJUNTO_HPP
#define TAD_CONJUNTO_HPP
#include "lista.hpp"

class conjunnto{
    public:
        conjunnto():
        l()
        {}
        typedef Lista<int>::posicion pos;
        void poner(pos p,int a);
        void quitar(pos p,int a);
        void unio();
        void interseccion();
        void diferencia();

    private:
        Lista<int> l;
};

#endif