#ifndef TAD_TREN_HPP
#define TAD_TREN_HPP
#include "PILA_ENLA.h"
#include <cassert>

class tren{

    struct vagon{
        int pos;
    };

    public:
        tren():
        p(),
        p2()
        {};

        void izquierda(){
            assert(!p.vacia());
            p2.push(p.tope());
            p.pop();
        }

        void derecha(){
            assert(!p2.vacia());
            p.push(p2.tope());
            p2.pop();
        }

        void eliminar(){
            assert(!p.vacia());
            p.pop();
        }

        void insertar_act(){
            vagon act;
            p.push(act);
        }

        vagon observar(){
            assert(!p.vacia());
            return p.tope();
        }

        bool vacio(){
            if(p.vacia())
                return true;
                else
                    return false;
        }

    private:
        Pila<vagon> p,p2;
};

#endif