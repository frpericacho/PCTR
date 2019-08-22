#include <atomic>
#include <chrono>
#include <ctime>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

class analisisRendimiento {
 public:
  std::mutex mutex;
  int val;
  std::atomic<int> val2;

  void fun_mutex() {
    mutex.lock();
    val++;
    mutex.unlock();
  }

  void fun_atom() { val2++; }
};

int main() {
  std::vector<std::thread> hilos;
  analisisRendimiento cont;

  std::chrono::time_point<std::chrono::system_clock> start, end;
  start = std::chrono::system_clock::now();
  for (int j = 0; j < 10; j++) {
    hilos.push_back(std::thread([&cont]() {
      for (int i = 0; i < 10; i++) cont.fun_mutex();
    }));
  }
  for (int i = 0; i < 10; i++) hilos[i].join();
  end = std::chrono::system_clock::now();

  std::chrono::duration<double> elapsed_seconds = end - start;
  std::time_t end_time = std::chrono::system_clock::to_time_t(end);
  std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
            << "Tiempo: " << elapsed_seconds.count() << "s\n";

  std::chrono::time_point<std::chrono::system_clock> start2, end2;
  start2 = std::chrono::system_clock::now();
  for (int j = 0; j < 10; j++) {
    hilos.push_back(std::thread([&cont]() {
      for (int i = 0; i < 10; i++) cont.fun_atom();
    }));
  }
  for (int i = 0; i < 10; i++) hilos[i].join();
  end2 = std::chrono::system_clock::now();

  std::chrono::duration<double> elapsed_seconds2 = end2 - start2;
  std::time_t end_time2 = std::chrono::system_clock::to_time_t(end2);
  std::cout << "Calculo terminado a las: " << std::ctime(&end_time2)
            << "Tiempo: " << elapsed_seconds2.count() << "s\n";

  return 0;
}