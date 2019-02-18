#ifndef TAD_SIMBOLO_HPP
#define TAD_SIMBOLO_HPP
#include "lista.hpp"

class simbolo{
    public:
    simbolo(): l(){}
    enum DIRECCION{arriba,abajo,derecha,izquierda};
    void poner_trazo(DIRECCION d);
    void desahacer(int n);
    simbolo eje_x();
    simbolo eje_y();
    simbolo eje_xy();

    private:
        Lista<DIRECCION> l;
};

void simbolo::poner_trazo(DIRECCION d){
    l.insertar(d,l.fin());
}

void simbolo::desahacer(int n){
    assert(l.primera()!=l.fin());
    while((n<0)){
        l.eliminar(l.fin());
        n--;
    }
}

simbolo simbolo::eje_x(){
    assert(l.primera()!=l.fin());
    simbolo aux;
    while(l.primera()!=l.fin()){
        if(l.elemento(l.primera())==0){
            aux.l.insertar(simbolo::abajo,aux.l.fin());
        }else if(l.elemento(l.primera())==1){
            aux.l.insertar(simbolo::arriba,aux.l.fin());
        }else{
            aux.l.insertar(l.elemento(l.primera()),aux.l.fin());
        }
        l.eliminar(l.primera());        
    }
    return aux;
}

simbolo simbolo::eje_y(){
    assert(l.primera()!=l.fin());
    simbolo aux;
    while(l.primera()!=l.fin()){
        if(l.elemento(l.primera())==2){
            aux.l.insertar(simbolo::izquierda,aux.l.fin());
        }else if(l.elemento(l.primera())==3){
            aux.l.insertar(simbolo::derecha,aux.l.fin());
        }else{
            aux.l.insertar(l.elemento(l.primera()),aux.l.fin());
        }
        l.eliminar(l.primera());        
    }
    return aux;
}

simbolo simbolo::eje_xy(){
    assert(l.primera()!=l.fin());
    simbolo aux;
    while(l.primera()!=l.fin()){
        if(l.elemento(l.primera())==0){
            aux.l.insertar(simbolo::abajo,aux.l.fin());
        }else if(l.elemento(l.primera())==1){
            aux.l.insertar(simbolo::arriba,aux.l.fin());
        }else if(l.elemento(l.primera())==2){
            aux.l.insertar(simbolo::izquierda,aux.l.fin());
        }else if(l.elemento(l.primera())==3){
            aux.l.insertar(simbolo::derecha,aux.l.fin());
        }
        l.eliminar(l.primera());        
    }
    return aux;
}

#endif