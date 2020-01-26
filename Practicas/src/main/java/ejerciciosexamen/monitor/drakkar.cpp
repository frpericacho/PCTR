#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

class drakkar {
  inline static int cant{0};
  std::mutex mutex;
  std::condition_variable cv;

 public:
  drakkar() = default;
  void cocinar() {
    std::unique_lock<std::mutex> lock(mutex);
    cant += 10;
    std::cout << cant << std::endl;
    cv.notify_all();
    while (cant > 0) cv.wait(lock);
  }

  void comer() {
    std::unique_lock<std::mutex> lock(mutex);
    if (cant > 0) {
      std::cout << cant << std::endl;
      --cant;
    } else {
      cv.notify_one();
      cv.wait(lock);
    }
  }
};

void run(drakkar& d, int tipo) {
  while (true) {
    if (tipo == 0)
      d.comer();
    else
      d.cocinar();
  }
}

int main() {
  drakkar a;
  std::vector<std::thread> threads;
  int cores = std::thread::hardware_concurrency();
  for (size_t i = 0; i < cores; i++) {
    threads.push_back(std::thread(run, std::ref(a), i));
  }
  for (auto& t : threads) {
    t.join();
  }
}