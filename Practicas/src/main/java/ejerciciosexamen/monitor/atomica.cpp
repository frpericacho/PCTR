#include<iostream>
#include<atomic>
#include<vector>
#include<thread>

struct contador{
    std::atomic<int> atom;
    void inc(){atom++;};
    void dec(){atom--;};
    int res(){ return atom.load();}
};

int main(){
    contador cont;
    cont.atom.store(0);
    std::vector<std::thread> hilos;
    for(int i = 0; i < 3; i++){
        hilos.push_back(std::thread([&cont](){
            for(int i = 0; i < 100; i++)
                cont.inc();
        }));
    }
    for(int i = 0; i < 3; i++){
        hilos.push_back(std::thread([&cont](){
            for(int i = 0; i < 100; i++)
                cont.dec();
        }));
    }
    for(auto& thread : hilos)
        thread.join();
    std::cout << cont.res();

    return 0;
}