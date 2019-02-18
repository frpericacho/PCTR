#ifndef TAD_AERODROMO_HPP
#define TAD_AERODROMO_HPP
#include "bicola.hpp"

class aerodromo{
    public:
        aerodromo(){}
        void entra();
        void sale();
        

    private:
        int total = 12;
        Bicola<int> b;
};

#endif