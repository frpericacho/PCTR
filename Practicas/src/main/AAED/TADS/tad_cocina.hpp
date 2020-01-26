#ifndef TAD_COCINA_HPP
#define TAD_COCINA_HPP
#include "lista.hpp"

class mueble
{
  public:
    float distacia, anchura;
};

bool operator==(const mueble &a, const mueble &b)
{
    return (a.distacia == b.distacia && a.anchura == b.anchura);
}

class cocina
{
  public:
    cocina(float lon) : longitud(lon),
                        pared()
    {
    }
    typedef Lista<mueble>::posicion pos;
    bool det_colocar(mueble m, pos);
    void poner_mueble(mueble m, pos p);
    mueble mueble_pos(pos p);
    void eliminar_mueble(pos p);
    void mover_mueble(pos p);
    ~cocina();

  private:
    Lista<mueble> pared;
    float longitud;
};

bool cocina::det_colocar(mueble m, pos p)
{
    mueble tmp = pared.elemento(p);
    float lon{0};
    if (p == pared.primera())
    {
        lon = longitud - tmp.distacia + tmp.anchura;
    }
    else if (p == pared.anterior(pared.fin()))
    {
        lon = tmp.distacia;
    }
    else
    {
        lon = tmp.distacia + tmp.anchura - pared.elemento(pared.siguiente(p)).anchura;
    }
    return m.anchura <= lon;
}

void cocina::poner_mueble(mueble m, pos p)
{
    assert(det_colocar(m, p));
    pared.insertar(m, p);
}

mueble cocina::mueble_pos(pos p)
{
    return pared.elemento(p);
}

void cocina::eliminar_mueble(pos p)
{
    return pared.eliminar(p);
}

void cocina::mover_mueble(pos p)
{
    assert(p != pared.fin());
    if (pared.siguiente(p) != pared.fin())
    {
        pared.elemento(p).distacia = pared.elemento(pared.siguiente(p)).distacia + pared.elemento(pared.siguiente(p)).anchura;
    }
}

#endif