#include <iostream>
#include <thread>

using namespace std;
static int mat[10000][10000];
void convolucion(int [10000][10000],int ,int );

void convolucion(int m[10000][10000],int ini,int fin){
    for(int i = ini;i < fin; i++){
        for(int j = ini;j < fin; j++){
            for(int x = 0;x < 3; x++){
                for(int y = 0;y < 3; y++){
                    if (mat[i][j] > 20)
                        mat[i][j] = 20;
                    else if(mat[i][j] < -20)
                        mat[i][j] = -20;
                    else{
                        if(i > 0 && j > 0)
                            mat[i][j] += mat[i -1][j - 1] * m[x][y];
                    }
                }
            }
        }
    }
}

int main(int argc, char const *argv[])
{
    int enf [10000][10000]  = { { 0, -1, 0 }, { -1, 5, 1 }, { 0, -1, 0 } };
    int rbor[10000][10000]  = { { 0, 0, 0 }, { -1, 1, 0 }, { 0, 0, 0 } };
    int dbor[10000][10000]  = { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };
    int sobel[10000][10000]  = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
    int sharpen[10000][10000]  = { { 1, -2, 1 }, { -2, 5, -2 }, { 1, -2, 1 } };
    int escala[41],cont = -20,inicio,fin,opc;

    for(int i = 0;i < 41; i++){
        escala[i] = cont;
        cont++;
    }

    cout << "Elija que opción desea realizar" << endl;
    cout << "1 - Enfocar" << endl;
    cout << "2 - Realzar bordes" << endl;
    cout << "3 - Detectar bordes" << endl;
    cout << "4 - Sobel" << endl;
    cout << "5 - Sharpen" << endl;
    cin >> opc;
    cout << "indique el inicio" << endl;
    cin >> inicio;
    cout << "indique el fin" << endl;
    cin >> fin;

    switch(opc){
        case 1: convolucion(enf, inicio, fin);
        break;
        case 2: convolucion(rbor, inicio, fin);
        break;
        case 3: convolucion(dbor, inicio, fin);
        break;
        case 4: convolucion(sobel, inicio, fin);
        break;
        case 5: convolucion(sharpen, inicio, fin);
        break;
        case 6:
        break;
    }

    return 0;
}
