#include <atomic>
#include <chrono>
#include <ctime>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

class rendimientocpp {
 public:
  std::mutex mutex;
  std::atomic<int> val_a;
  int val_m;

  void fun_m() {
    mutex.lock();
    val_m++;
    mutex.unlock();
  }

  void fun_a() { val_a++; }
};

int main() {
  std::vector<std::thread> hilos;
  rendimientocpp a;
  std::chrono::time_point<std::chrono::system_clock> start, end, start2, end2;

  start = std::chrono::system_clock::now();
  for (int i = 0; i < 4; i++) {
    hilos.push_back(std::thread([&a]() {
      for (int i = 0; i < 10; i++) {
        a.fun_a();
      }
    }));
  }
  end = std::chrono::system_clock::now();

  std::chrono::duration<double> elapsed_seconds = end - start;
  std::time_t end_time = std::chrono::system_clock::to_time_t(end);
  std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
            << "Tiempo atomic: " << elapsed_seconds.count() << "s\n";
  
  start2 = std::chrono::system_clock::now();
  for (int i = 0; i < 4; i++) {
    hilos.push_back(std::thread([&a]() {
      for (int i = 0; i < 10; i++) {
        a.fun_m();
      }
    }));
  }
  end2 = std::chrono::system_clock::now();
  std::chrono::duration<double> elapsed_seconds2 = end2 - start2;
  std::time_t end_time2 = std::chrono::system_clock::to_time_t(end);
  std::cout << "Calculo terminado a las: " << std::ctime(&end_time2)
            << "Tiempo mutex: " << elapsed_seconds2.count() << "s\n";
}