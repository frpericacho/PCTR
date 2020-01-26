#ifndef TAD_CURSOR_HPP
#define TAD_CURSOR_HPP
#include "PILA_ENLA.h"

class cursor{
    public:
    cursor(){}
        void avanza();
        void retrasa();
        void ir_final();
        void ir_inicio();
        void borra_pos();
        void borra_ant();
        void inserta_pos(char a);
        void sobreescribe(char a);

    private:
        Pila<char> p1,p2;
};

void cursor::avanza(){
    assert(!p1.vacia());
    p2.push(p1.tope());
    p1.pop();
}

void cursor::retrasa(){
    assert(!p2.vacia());
    p1.push(p2.tope());
    p2.pop();
}

void cursor::ir_final(){
    assert(!p2.vacia());
    while(!p2.vacia()){
        p1.push(p2.tope());
        p2.pop();
    }
}

void cursor::ir_inicio(){
    assert(!p1.vacia());
    while(!p1.vacia()){
        p2.push(p1.tope());
        p1.pop();
    }
}

void cursor::borra_pos(){
    assert(!p1.vacia());
    p1.pop();
}

void cursor::borra_ant(){
    assert(!p2.vacia());
    p2.pop();
}

void cursor::inserta_pos(char a){
    p1.push(a);
}

void cursor::sobreescribe(char a){
    assert(!p2.vacia());
    p2.pop();
    p2.push(a);
}

#endif