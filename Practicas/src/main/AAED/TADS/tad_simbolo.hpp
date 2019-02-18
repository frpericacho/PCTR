#ifndef TAD_SIMBOLO_HPP
#define TAD_SIMBOLO_HPP
#include <iostream>
#include <cassert>
#include "ListaDobleEnlazadaCabecera.hpp"

class simbolo
{
  public:
    simbolo(){

    };
    enum DIRECCION{IZQ,ARB,DER,ABJ};
    friend std::ostream& operator <<(std::ostream&, simbolo);

    void poner(DIRECCION n)
    {
        l.insertar(n,l.fin());
    }

    void quitar(int n)
    {
        assert(l.fin()==l.primera());
        while (n != 0)
        {
            l.eliminar(l.primera());
            n--;
        }
    }

    simbolo eje_x()
    {
        simbolo res;
        while (l.fin()!=l.primera()){
            if (l.elemento(l.primera()) == ABJ){
                res.l.insertar(ARB,res.l.fin());
                l.eliminar(l.primera());
            }
            else{
                if (l.elemento(l.primera()) == ARB){
                    res.l.insertar(ABJ,res.l.fin());
                    l.eliminar(l.primera());
                }
                else{
                    res.l.insertar(l.elemento(l.primera()),res.l.fin());
                    l.eliminar(l.primera());
                }
            }
        }

        return res;
    }

    Lista<DIRECCION> eje_y(){
        Lista<DIRECCION> res;
        while (l.fin()!=l.primera()){
            if (l.elemento(l.primera()) == IZQ){
                res.insertar(DER,res.fin());
                l.eliminar(l.primera());
            }
            else{
                if (l.elemento(l.primera()) == DER){
                    res.insertar(IZQ,res.fin());
                    l.eliminar(l.primera());
                }
                else{
                    res.insertar(l.elemento(l.primera()),res.fin());
                    l.eliminar(l.primera());
                }
            }
        }
        return res;
    }

    simbolo eje_xy(){
        simbolo res;
        while (l.fin()!=l.primera()){
            if (l.elemento(l.primera()) == IZQ){
                res.l.insertar(DER,res.l.fin());
                l.eliminar(l.primera());
            }
            else{
                if (l.elemento(l.primera()) == DER){
                    res.l.insertar(IZQ,res.l.fin());
                    l.eliminar(l.primera());
                }
                else{
                    if (l.elemento(l.primera()) == ABJ){
                        res.l.insertar(ARB,res.l.fin());
                        l.eliminar(l.primera());
                    }
                    else{
                        if (l.elemento(l.primera()) == ARB){
                            res.l.insertar(ABJ,res.l.fin());
                            l.eliminar(l.primera());
                        }
                    }
                }
            }
        }
        
        return res;
    }


    private:
        Lista<DIRECCION> l;
    };

    std::ostream& operator <<(std::ostream& os, simbolo a){ 
        while(a.l.fin()!=a.l.primera()){
            switch(a.l.elemento(a.l.primera())){
                case simbolo::ARB: os << " arribita " << std::endl; break;
                case simbolo::ABJ: os << " abajito " << std::endl; break;
                case simbolo::IZQ: os << " izquierdita " << std::endl; break;
                case simbolo::DER: os << " derechita " << std::endl; break;
            }
            a.l.eliminar(a.l.primera());
        }
        return os;
    }

#endif