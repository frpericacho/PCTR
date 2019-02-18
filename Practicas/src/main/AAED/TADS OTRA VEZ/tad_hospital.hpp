#ifndef TAD_HOSPITAL_HPP
#define TAD_HOSPITAL_HPP
#include "lista_vectorial.hpp"

class paciente{
    public:
        int da_id(){
            return id;
        }

        int da_gr(){
            return gravedad;
        }

    private:
        int id,gravedad;
};

class hospital{
    public:
        hospital():planta(20),uci(20){}
        void ingreso(paciente p);
        void alta(paciente p);
        void muerte(paciente p);
        int pacientesUCI();
        int pacientesPLANTA();
        int pacientesGRAVEDAD(int g);
        ~hospital(){}

    private:
        Lista<paciente> planta,uci;
};

void hospital::ingreso(paciente p){
    if(p.da_gr() <= 9 && p.da_gr() >= 6){
        planta.insertar(p,planta.fin());
    }else if (p.da_gr() <= 5 && p.da_gr() >= 1){
        uci.insertar(p,uci.fin());
    }
}

void hospital::alta(paciente p){
    planta.eliminar(planta.buscar(p));
}

void hospital::muerte(paciente p){
    uci.eliminar(uci.buscar(p));
}

int hospital::pacientesUCI(){
    int cont=0;
    Lista<paciente>::posicion p;
    for(p = uci.primera();p != uci.fin();p = uci.siguiente(p)){
        cont++;
    }
    return cont;
}

int hospital::pacientesUCI(){
    int cont=0;
    Lista<paciente>::posicion p;
    for(p = planta.primera();p != planta.fin();p = planta.siguiente(p)){
        cont++;
    }
    return cont;
}

int hospital::pacientesGRAVEDAD(int g){
    int cont;
    Lista<paciente>::posicion p;
    if(g <= 9 && g >= 6){
        for(p = planta.primera();p != planta.fin();p = planta.siguiente(p)){
            if(planta.elemento(p).da_gr()==g)
                cont++;
        }
    }else if (g <= 5 && g >= 1){
        for(p = uci.primera();p != uci.fin();p = uci.siguiente(p)){
            if(uci.elemento(p).da_gr()==g)
                cont++;
        }
    }

    return cont;
}

#endif