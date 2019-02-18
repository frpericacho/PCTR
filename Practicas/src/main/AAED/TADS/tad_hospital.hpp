#ifndef TAD_HOSPITAL_HPP
#define TAD_HOSPITAL_HPP
#include "lista_vectorial.hpp"

class paciente{
    public:
        paciente()
        {}
        paciente(int id,int gr):
        identificacion(id),
        gravedad(gr)
        {}

        paciente(const paciente & p) = default;

        int gravedad;
        int identificacion;
};

bool operator==(const paciente &x,const paciente &y){return x.identificacion == y.identificacion;}

class hospital{
    public:
        hospital():
        planta(40),
        uci(40),
        morgue(40)
        {}

        void ingreso(paciente p,int grado){
            assert(grado <= 10 || grado >= -1);
            if(grado <= 9 && grado >= 6)
                if(planta.llena()){
                    //falta
                }else{
                    planta.insertar(p,planta.fin());
                }
            else if(grado <= 5 && grado >= 1)
                    if(uci.llena()){
                        //falta
                    }else{
                        uci.insertar(p,uci.fin());
                    }
                    else if(grado == 0)
                        morgue.insertar(p,morgue.fin());
        }

        void dar_alta(paciente p){
            assert(planta.size()!=0);
            planta.eliminar(planta.buscar(p));
        }

        void muerte(paciente p){
            assert(uci.size()!=0);
            uci.elemento(uci.buscar(p)).gravedad = 0;
            morgue.insertar(p,morgue.fin());
            uci.eliminar(uci.buscar(p));
        }

        int pacientesUCI(){
            int cont=0;
            for(int i = uci.primera();i != uci.fin(); i = uci.siguiente(i)){
                cont++;
            }
            return cont;
        }

        int pacientesPLANTA(){
            int cont=0;
            for(int i = planta.primera();i != planta.fin(); i = planta.siguiente(i)){
                cont++;
            }
            return cont;
        }

        int gravedad_especifica(int grado){
            assert(grado >= 10 || grado <= -1);
            int cont=0;
            if(grado <= 9 && grado >= 6)
                for(int i = planta.primera();i != planta.fin(); i = planta.siguiente(i)){
                    cont++;
                }
            else if(grado <= 5 && grado >= 1)
                    for(int i = uci.primera();i != uci.fin(); i = uci.siguiente(i)){
                        cont++;
                    }
                    else if(grado == 0)
                        for(int i = morgue.primera();i != morgue.fin(); i = morgue.siguiente(i)){
                            cont++;
                        }
            return cont;
        }

        ~hospital(){}

    private:
        Lista<paciente> planta;
        Lista<paciente> uci;
        Lista<paciente> morgue;
        bool anomalo;
};

#endif