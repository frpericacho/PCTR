#ifndef TAD_EXAMENFEB19_HPP
#define TAD_EXAMENFEB19_HPP
#include "PILA_ENLA.h"
#include <string>
#include <cassert>
using namespace std;

class funcion{
    public:
        int operacion(string s);

    private:
        Pila<char> p;
};

int funcion::operacion(string s){
    Pila<char> aux;
    int i = 0,res = 0;
    while(s[i]!='\0'){
        switch (s[i])
        {
            case '+':   while(!p.vacia()){
                            res += p.tope();
                            p.pop();
                        }
                        p.push(res);
                        i++;
                break;
            case '-': while(!p.vacia()){
                            res -= p.tope();
                            p.pop();
                        }
                        p.push(res);
                        i++;
                break;
            case '*': while(!p.vacia()){
                            res *= p.tope();
                            p.pop();
                        }
                        p.push(res);
                        i++;
                break;
            case '/':   while(!p.vacia()){
                            res /= p.tope();
                            p.pop();
                        }
                        p.push(res);
                        i++;
                break;
            case ' ': i++;
                break;
            default:    p.push(s[i]);
                        i++;
                break;
        }
    }

    return p.tope();
}

#endif