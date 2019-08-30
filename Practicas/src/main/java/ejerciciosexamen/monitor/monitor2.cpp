#include <mutex>
#include <vector>
#include <thread>
#include <atomic>
#include <iostream>

class monitor2{
    public:
    std::atomic<int> val_a;
    int val_g;

    void fun_g(){
        std::lock_guard<std::mutex> cerrojo(std::mutex); 
        fun_a();
        std::cout << "hola soy fun_g" << std::endl;
    }

    void fun_a(){
        std::lock_guard<std::mutex> cerrojo(std::mutex); 
        std::cout << "hola soy fun_a" << std::endl;
    }
};

int main(){
    monitor2 a;
    std::vector<std::thread> hilos;

    for(int i = 0; i < 4; i++){
        hilos.push_back(std::thread([&a](){
            a.fun_g();
        }));
    }

    return 0;
}