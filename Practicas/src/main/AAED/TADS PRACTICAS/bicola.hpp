#ifndef BICOLA_HPP
#define BICOLA_HPP
#include <deque>
template <typename T>
class Bicola{
public:
    bool vacia() const { return d.empty(); }
    //Acceso Ultimo Elemento
    const T & back() const { return d.back(); }
    //Acceso Primer Elemento
    const T & front() const { return d.front(); }
    // Eliminar Ultimo Elemento
    void pop_back() { d.pop_back(); }
    // Eliminar Primer Elemento
    void pop_front() { d.pop_front(); }
    // Insertar Al Final
    void push_back(const T& e) { d.push_back(e);}
    // Inserat Al Principio
    void push_front(const T& e) { d.push_front(e); }
private:
    std::deque<T> d;
};
#endif
