#ifndef TAD_CONSULTORIO_HPP
#define TAD_CONSULTORIO_HPP
#include "ListaDobleEnlazadaCabecera.hpp"
#include "ColaDinamica.hpp"
#include <iostream>
using namespace std;

class pacientes
{
  public:
    pacientes();
    pacientes(string n, int id_) : nom(n),
                                  id(id_)
    {}

  private:
    string nom;
    int id;
};

class medico
{
  public:
    int id;
    medico();
    medico(string n, int id_) : nom(n),
                               id(id_)
    {}
    Cola<pacientes> pa()
    {
        return c;
    }

  private:
    Cola<pacientes> c;
    string nom;
};


    bool operator==(const medico &x,const medico &y) { return x.id == y.id; }

class consultorio
{
  public:
    consultorio(){};

    void alta(medico m)
    {
        l.insertar(m, l.fin());
    }

    void baja(medico m)
    {
        l.eliminar(l.buscar(m));
    }

    void poner_lista(pacientes p, medico m)
    {
        l.elemento(l.buscar(m)).pa().push(p);
    }

    pacientes consulta(medico m)
    {
        return l.elemento(l.buscar(m)).pa().frente();
    }

    void atender(medico m)
    {
        l.elemento(l.buscar(m)).pa().pop();
    }

    bool comprobar(medico m)
    {
        return l.elemento(l.buscar(m)).pa().vacia();
    }

  private:
    Lista<medico> l;
};

#endif