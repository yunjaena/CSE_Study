#include <iostream>
#include <algorithm>
#include <cmath>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int bottom[500005];
int top[500005];
int bottomSum[500005];
int topSum[500005];
int minVal;
int range;

int main(){
    int n,h;
    cin >> n >> h;
    minVal = n;
    range = 0;

    for(int i = 0 ; i < n/2; i++){
        int t,b;
        cin >> b >> t;
        bottom[b]++;
        top[t]++;
    }

    for(int i = 1; i <= h; i++){
        topSum[i] = topSum[i - 1] + top[i];
        bottomSum[i] = bottomSum[i - 1] + bottom[i];
    }

    for(int i = 1 ; i <= h ; i++){
        int broke = 0;

        broke += bottomSum[h] - bottomSum[i-1];
        broke += topSum[h] - topSum[h-i];

        if(minVal > broke){
            minVal = broke;
            range = 1;
        }else if(minVal == broke){
            range++;
        }
    }

    cout << minVal << " " << range;

    return 0;
}