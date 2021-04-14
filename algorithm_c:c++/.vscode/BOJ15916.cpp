#include <iostream>
#include <algorithm>
#include <cmath>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int main(){
    int n;
    cin >> n;
    double minValue = INT32_MAX;
    double maxValue = 0;

    for(int i = 1 ; i <= n ; i++){
        double gradient;
        cin >> gradient;
        minValue = min(minValue, gradient / i);
        maxValue = max(maxValue, gradient / i);
    }

    int k;
    cin >> k;
    if(minValue <= k && k <= maxValue){
        cout << "T";
    }else{
        cout << "F";
    }

    return 0;
}