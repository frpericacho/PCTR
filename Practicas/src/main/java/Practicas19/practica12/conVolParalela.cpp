#include <iostream>
#include <stdlib.h>
#include <random>
#include <chrono>
#include <ctime>
#include <stdio.h>
#include <thread>
#include <vector>
using namespace std;

void inicializa();
int convolucion(int, int, int[3][3]);
void fun_enf(int, int);
void fun_dbor(int, int);
void fun_rbor(int, int);
void fun_sharpen(int, int);
void fun_sobel(int, int);
void imprime();
int escala[256];
int mat[1000][1000];
int mat2[1000][1000];
int enf[3][3] = {{0, -1, 0}, {-1, 5, 1}, {0, -1, 0}};
int rbor[3][3] = {{0, 0, 0}, {-1, 1, 0}, {0, 0, 0}};
int dbor[3][3] = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
int sobel[3][3] = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
int sharpen[3][3] = {{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}};

int main(int argc, char const *argv[])
{
    int opc, cant, inicio = 0;
    inicializa();
    cant =  atoi(argv[1]);
    std::chrono::time_point<std::chrono::system_clock> start, end;
    cout << "introduzca una opcion" << endl;
    cout << "1.- enfocar" << endl;
    cout << "2.- realzar bordes" << endl;
    cout << "3.- detectar bordes" << endl;
    cout << "4.- filtro sobel" << endl;
    cout << "5.- filtro sharpenion" << endl;
    cin >> opc;
    thread hilos[cant];
    int rango = 1000 / cant;

    start = std::chrono::system_clock::now();
    switch (opc)
    {
    case 1:
        for (int cont = 0; cont < cant; cont++)
        {
            if (cont == cant - 1)
            {
                hilos[cont] = thread(fun_enf, inicio, 1000);
            }
            else
            {
                hilos[cont] = thread(fun_enf, inicio, inicio + rango);
            }

            inicio += rango;
        }
        for (int i = 0; i < cant; i++)
            hilos[i].join();

        break;
    case 2:
        for (int cont = 0; cont < cant; cont++)
        {
            if (cont == cant - 1)
            {
                hilos[cont] = thread(fun_rbor, inicio, 1000);
            }
            else
            {
                hilos[cont] = thread(fun_rbor, inicio, inicio + rango);
            }

            inicio += rango;
        }
        for (int i = 0; i < cant; i++)
            //hilos[i].join();
            break;
    case 3:
        for (int cont = 0; cont < cant; cont++)
        {
            if (cont == cant - 1)
            {
                hilos[cont] = thread(fun_dbor, inicio, 1000);
            }
            else
            {
                hilos[cont] = thread(fun_dbor, inicio, inicio + rango);
            }

            inicio += rango;
        }
        for (int i = 0; i < cant; i++)
            hilos[i].join();
        break;
    case 4:
        for (int cont = 0; cont < cant; cont++)
        {
            if (cont == cant - 1)
            {
                hilos[cont] = thread(fun_sobel, inicio, 1000);
            }
            else
            {
                hilos[cont] = thread(fun_sobel, inicio, inicio + rango);
            }

            inicio += rango;
        }
        for (int i = 0; i < cant; i++)
            hilos[i].join();
        break;
    case 5:
        for (int cont = 0; cont < cant; cont++)
        {
            if (cont == cant - 1)
            {
                hilos[cont] = thread(fun_sharpen, inicio, 1000);
            }
            else
            {
                hilos[cont] = thread(fun_sharpen, inicio, inicio + rango);
            }

            inicio += rango;
        }
        for (int i = 0; i < cant; i++)
            hilos[i].join();
        break;
    default:
        cout << "valor incorrecto" << endl;
    }
    end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end - start;
    std::cout << "Tiempo: " << elapsed_seconds.count() << "s\n";

    //imprime();

    return 0;
}

void imprime()
{
    for (int x = 0; x < 1000; x++)
    {
        for (int y = 0; y < 1000; y++)
        {
            cout << mat2[x][y];
        }
        cout << endl;
    }
}

int convolucion(int m, int n, int kernel[3][3])
{
    int res = 0;
    int i = (m - 1 + 1000) % 1000;
    int j = (n - 1 + 1000) % 1000;

    for (int a = 0; a != 3; a++)
    {
        for (int b = 0; b != 3; b++)
        {
            res += mat[i][j] * kernel[a][b];
            i = (i + 1) % 1000;
            j = (j + 1) % 1000;
        }
    }
    return res;
}

void fun_enf(int inicio, int fin)
{
    for (int i = inicio; i < fin; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void fun_sharpen(int inicio, int fin)
{
    for (int i = inicio; i < fin; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, sharpen);
        }
    }
}

void fun_sobel(int inicio, int fin)
{
    for (int i = inicio; i < fin; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, sobel);
        }
    }
}

void fun_dbor(int inicio, int fin)
{
    for (int i = inicio; i < fin; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, dbor);
        }
    }
}

void fun_rbor(int inicio, int fin)
{
    for (int i = inicio; i < fin; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, rbor);
        }
    }
}

void inicializa()
{
    for (int i = 0; i < 256; i++)
    {
        escala[i] = i;
    }

    for (int i = 0; i < 1000; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat[i][j] = escala[rand() % 256];
        }
    }

    for (int i = 0; i < 1000; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = 0;
        }
    }
}