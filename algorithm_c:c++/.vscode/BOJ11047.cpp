#include <iostream>
#include <algorithm>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int n, k;
int coin[10];

int main(){
    fastio;
    int count = 0;
    cin >> n >> k;
    for(int i = 0 ; i < n ; i++){
        cin >> coin[i];
    }

    while(n-- > 0){
        int m = k / coin[n];
        k -= coin[n] * m;
        count += m;
        if(k <= 0) break;
    }

    cout << count;

    return 0;
}
