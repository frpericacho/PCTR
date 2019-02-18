#ifndef TAD_DICCIONARIO_HPP
#define TAD_DICCIONARIO_HPP
//#include "ListaDobleEnlazadaCabecera.hpp"
#include "lista.hpp"
#include <iostream>
#include <string>
using namespace std;

class traduccion
{
  public:
    string n;
    traduccion(){}

    traduccion(string n_):n(n_){}

    void traduccion_nueva(string n){
        l.insertar(n,l.fin());
    }

    void eliminar_traduccion(string n){
        l.eliminar(l.buscar(n));
    }

    void mostrar()
    {
        Lista<string>::posicion p;

        for (p = l.primera(); p != l.fin(); p = l.siguiente(p))
        {
            cout << l.elemento(p) << endl;
        }
    }

  private:
    Lista<string> l;
};

    bool operator==(const traduccion &x, const traduccion &y) { return x.n == y.n;}

class diccionario{
    public:
        diccionario()
        {}

        void insertar_pal(traduccion t)
        {
            d.insertar(t, d.fin());
        }

        void nueva_trad(traduccion t, string n){
            d.elemento(d.buscar(t)).traduccion_nueva(n);
        }

        void eliminar_pal(traduccion t)
        {
            d.eliminar(d.buscar(t));
        }

        void eliminar_trad(traduccion t, string n){
            d.elemento(d.buscar(t)).eliminar_traduccion(n);
        }

        void consultar(traduccion t)
        {
            d.elemento(d.buscar(t)).mostrar();
        }

        ~diccionario(){}

    private: 
        Lista<traduccion> d;
};

#endif