#include <iostream>
#include <mutex>
#include <thread>

int val;
std::mutex mutex;
void funcion();

int main(int argc, char const *argv[])
{
    std::thread h1(funcion);
    std::thread h2(funcion);
    h1.join();
    h2.join();
    std::cout << val << std::endl;
    return 0;
}

void funcion(){
    mutex.lock();
    val++;
    mutex.unlock();
}
