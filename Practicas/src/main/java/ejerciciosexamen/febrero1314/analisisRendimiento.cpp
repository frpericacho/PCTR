#include <iostream>
#include <atomic>
#include <mutex>
#include <thread>
#include <vector>
#include <chrono>
#include <ctime>

class analisisRendimiento{
    public:
    std::mutex mutex;
    int val;
    std::atomic<int> val2;

    void fun_mutex(){
        mutex.lock();
        val++;
        mutex.unlock();
    }

    void fun_atom(){
        val2++;
    }

    void main(){
        std::vector<std::thread> hilos;

        std::chrono::time_point<std::chrono::system_clock> start, end;
        start = std::chrono::system_clock::now();
        fun_mutex();
        end = std::chrono::system_clock::now();

        std::chrono::duration<double> elapsed_seconds = end-start;
        std::time_t end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
            << "Tiempo: " << elapsed_seconds.count() << "s\n";

        std::chrono::time_point<std::chrono::system_clock> start2, end2;
        start2 = std::chrono::system_clock::now();
        fun_atom();
        end2 = std::chrono::system_clock::now();

        std::chrono::duration<double> elapsed_seconds2 = end2-start2;
        std::time_t end_time2 = std::chrono::system_clock::to_time_t(end2);
        std::cout << "Calculo terminado a las: " << std::ctime(&end_time2)
            << "Tiempo: " << elapsed_seconds2.count() << "s\n";
    }
};