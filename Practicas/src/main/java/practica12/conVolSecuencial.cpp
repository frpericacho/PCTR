#include <iostream>
using namespace std;

void inicializa();
int convolucion(int, int, int[3][3]);
void fun_enf();
void fun_dbor();
void fun_rbor();
void fun_sharpen();
void fun_sobel();
int size = 10000;
int escala[41];
int mat[10000][10000];
int mat2[10000][10000];
int enf[3][3] = {{0, -1, 0}, {-1, 5, 1}, {0, -1, 0}};
int rbor[3][3] = {{0, 0, 0}, {-1, 1, 0}, {0, 0, 0}};
int dbor[3][3] = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
int sobel[3][3] = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
int sharpen[3][3] = {{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}};

int main(int argc, char const *argv[])
{
    int opc;
    inicializa();
    cout << "introduzca una opcion" << endl;
    cout << "1.- enfocar" << endl;
    cout << "2.- realzar bordes" << endl;
    cout << "3.- detectar bordes" << endl;
    cout << "4.- filtro sobel" << endl;
    cout << "5.- filtro sharpenion" << endl;
    cin >> opc;
    switch (opc)
    {
    case 1:
        fun_enf();
        break;
    case 2:
        fun_rbor();
        break;
    case 3:
        fun_dbor();
        break;
    case 4:
        fun_sobel();
        break;
    case 5:
        fun_sharpen();
        break;
    }

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            printf("| %d |", mat2[i][j]);
        }
    }

    return 0;
}

int convolucion(int m, int n, int kernel[3][3])
{
    int res = 0;
    int i = (m - 1 + size) % size;
    int j = (n - 1 + size) % size;

    for (int a = 0; a != 3; a++)
    {
        for (int b = 0; b != 3; b++)
        {
            res += mat[i][j] * kernel[a][b];
            i = (i + 1) % size;
            j = (j + 1) % size;
        }
    }
    return res;
}

void fun_enf()
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void fun_sharpen()
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void fun_sobel()
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void fun_dbor()
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void fun_rbor()
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void inicializa()
{
    int cont = -20;
    for (int i = 0; i < 41; i++)
    {
        escala[i] = cont;
        cont++;
    }

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat[i][j] = escala[rand() % 42];
        }
    }

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            mat2[i][j] = 0;
        }
    }
}
