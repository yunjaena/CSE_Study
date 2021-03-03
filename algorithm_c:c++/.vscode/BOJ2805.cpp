#include <iostream>
#include <algorithm>

using namespace std;

long long trees[10000000] = {0,};

bool isPossible(long long height, long long minHeight, long long tressCount){
    long long len = 0;
    for (int i = 0; i < tressCount; i++)
    {
        if (trees[i] - height > 0)
            len += trees[i] - height;
    }
    if (len >= minHeight)
        return true;
    return false;
}
int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    long long  n,m;

    cin >> n >> m;
    for(int i = 0 ; i < n ; i++){
        cin >> trees[i];
    }

    sort(trees, trees+n);

    long long low = 1;
    long long high = trees[n-1];
 
    
    long long result = 0;

    while (low <= high)
    {
        long long mid = (low + high) / 2;
    
        if (isPossible(mid, m, n))
        {
            result = max(result, mid);
            low = mid + 1;
        }
        else
        {
            high = mid - 1;
        }
    }

    cout << result;

}