#include <atomic>
#include <chrono>
#include <ctime>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

class analisisRendimiento {
 public:
  int m_cont;
  std::atomic<int> a_cont;
  std::mutex mutex;

  void fun_atomic() { a_cont++; }

  void fun_mutex() {
    mutex.lock();
    m_cont++;
    mutex.unlock();
  }
};
int main() {
  analisisRendimiento a;
  std::vector<std::thread> hilos;
  std::chrono::time_point<std::chrono::system_clock> start, end, start2, end2;
  start = std::chrono::system_clock::now();
  for (int i = 0; i < 10; i++) {
    hilos.push_back(std::thread([&a]() {
      for (int i = 0; i < 10; i++) a.fun_mutex();
    }));
  }
  end = std::chrono::system_clock::now();
  std::chrono::duration<double> elapsed_seconds = end - start;
  std::time_t end_time = std::chrono::system_clock::to_time_t(end);
  std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
            << "Tiempo mutex: " << elapsed_seconds.count() << "s\n";

  start2 = std::chrono::system_clock::now();
  for (int i = 0; i < 10; i++) {
    hilos.push_back(std::thread([&a]() {
      for (int i = 0; i < 10; i++) a.fun_atomic();
    }));
  }
  end2 = std::chrono::system_clock::now();
  std::chrono::duration<double> elapsed_seconds2 = end2 - start2;
  std::time_t end_time2 = std::chrono::system_clock::to_time_t(end2);
  std::cout << "Calculo terminado a las: " << std::ctime(&end_time2)
            << "Tiempo atomic: " << elapsed_seconds2.count() << "s\n";

    for(int i = 0; i < 20; i++)
        hilos[i].join();
}