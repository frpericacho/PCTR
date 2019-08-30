#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

class semaforocpp {
 public:
  std::mutex mutex;
  int n_ele;
  std::condition_variable lleno, vacio;
  int elementos[100];
  semaforocpp() : n_ele(0) {}

  void insertar(int indice, int valor) {
    std::unique_lock<std::mutex> cerrojo(mutex);
    if (n_ele == 100) {
      lleno.wait(cerrojo);
    }
    elementos[indice] = valor;
    n_ele++;
    vacio.notify_all();
  }

  int extraer(int indice) {
    std::unique_lock<std::mutex> cerrojo(mutex);
    if (n_ele == 0) vacio.wait(cerrojo);
    int res = elementos[indice];
    n_ele--;
    lleno.notify_all();
    return res;
  }
};

int indice = 0;
int i;
int main() {
  semaforocpp s;
  std::vector<std::thread> prod;
  std::vector<std::thread> cons;

  for (i = 0; i < 100; i++) {
    prod.push_back(std::thread([&s]() { s.insertar(indice, i); }));

    cons.push_back(
        std::thread([&s]() { std::cout << s.extraer(indice) << std::endl; }));

    indice = (indice - 1) % 100;
  }

  for (int i = 0; i < 100; i++) {
      prod[i].join();
      cons[i].join();
  }

  return 0;
}