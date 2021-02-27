#include <iostream>
#include <algorithm>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int n, k, b;
int result = 2147483647;

int road[100002];


int getSum(int from, int to){
    return road[to] - road[from - 1];
}
int main()
{
    cin >> n >> k >> b;

    fill_n(road, n + 1, 1);

    while (b-- > 0)
    {
        int broken;
        cin >> broken;
        road[broken] = 0;
    }

    for (int i = 1; i <= n; i++)
    {
        road[i] += road[i - 1];
    }


    for(int i = 0 ; i <= n - k + 1; i++){
        result = min(result, k - (getSum(1 + i, k + i)));
    }
    
    cout << result;








    return 0;
}