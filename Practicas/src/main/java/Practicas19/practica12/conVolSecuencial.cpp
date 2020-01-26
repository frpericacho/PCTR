#include <iostream>
using namespace std;

void inicializa();
int convolucion(int, int, int[3][3]);
void fun_enf();
void fun_dbor();
void fun_rbor();
void fun_sharpen();
void fun_sobel();
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
    default:
        cout << "valor incorrecto" << endl;
    }

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

void fun_enf()
{
    for (int i = 0; i < 1000; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, enf);
        }
    }
}

void fun_sharpen()
{
    for (int i = 0; i < 1000; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, sharpen);
        }
    }
}

void fun_sobel()
{
    for (int i = 0; i < 1000; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, sobel);
        }
    }
}

void fun_dbor()
{
    for (int i = 0; i < 1000; i++)
    {
        for (int j = 0; j < 1000; j++)
        {
            mat2[i][j] = convolucion(i, j, dbor);
        }
    }
}

void fun_rbor()
{
    for (int i = 0; i < 1000; i++)
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