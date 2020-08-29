#include <iostream>
#include <memory>
#include <thread>
#include <mutex>

void updateValue(const std::shared_ptr<int>& value, std::mutex& g_i_mutex)
{
    for(int i = 0; i<100;i++)
    {
        std::unique_lock<std::mutex> lk(g_i_mutex);
        (*value)++;
        lk.unlock();
    }
}

int main()
{
    auto sp = std::make_shared<int>(0);
    std::mutex g_i_mutex;
    std::thread incrementThread_1(updateValue, sp, std::ref(g_i_mutex));
    std::thread incrementThread_2(updateValue, sp, std::ref(g_i_mutex));
    incrementThread_1.join();
    incrementThread_2.join();
    std::cout << *sp << std::endl;
}
