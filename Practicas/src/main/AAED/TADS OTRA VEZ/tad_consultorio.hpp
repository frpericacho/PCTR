#ifndef TAD_CONSULTORIO_HPP
#define TAD_CONSULTORIO_HPP
#include "lista.hpp"
#include "ColaDinamica.hpp"

class paciente{
    public:
        int da_id(){
            return id;
        }

    private:
        int id;
};

class medico{
    public:
        Cola<paciente> pacientes(){
            return c;
        }

    private:
        Cola<paciente> c;
};

class consultorio{
    public:
        consultorio(): l(){}
        void nuevo_med(medico m);
        void eliminar_med(medico m);
        void poner_paciente(medico m,paciente p);
        int consultar_paciente(medico m,paciente p);
        void atender_paciente(medico m);
        bool comprobar(medico m);

    private:
        Lista<medico> l;
};

void consultorio::nuevo_med(medico m){
    l.insertar(m,l.fin());
}

void consultorio::eliminar_med(medico m){
    assert(l.primera()!=l.fin()); //esto seria correcto?
    l.eliminar(l.buscar(m));
}

void consultorio::poner_paciente(medico m,paciente p){
    assert(l.primera()!=l.fin());
    l.elemento(l.buscar(m)).pacientes().push(p);
}

int consultorio::consultar_paciente(medico m,paciente p){
    assert(l.primera()!=l.fin());
    return l.elemento(l.buscar(m)).pacientes().frente().da_id();
}

void  consultorio::atender_paciente(medico m){
    assert(l.primera()!=l.fin());
    l.elemento(l.buscar(m)).pacientes().pop();
}

bool consultorio::comprobar(medico m){
    assert(l.primera()!=l.fin());
    if(l.elemento(l.buscar(m)).pacientes().vacia()){
        return true;
    }else{
        return false;
    }
}

#endif