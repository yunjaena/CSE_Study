#include <iostream>
#include <algorithm>
#include <cmath>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int n;
long long dp[31][61];

long long solve(int one, int half){
    if(one == 0) return 1;
    
    long long &current = dp[one][half];

    if(current != -1) 
        return current;

    current = 0;

    if(one > 0){
        current += solve(one - 1, half + 1);
    }

    if(half > 0){
        current += solve(one, half - 1);
    }

    return current;

}

int main()
{
    cin >> n;

    while (n != 0)
    {
            for (int j = 0; j <= n; j++)
            {
                for (int k = 0; k <= 2 * n; k++)
                {
                    dp[j][k] = -1;
                }
            }
        cout << solve(n, 0) << "\n";
        cin >> n;
    }
    return 0;
}