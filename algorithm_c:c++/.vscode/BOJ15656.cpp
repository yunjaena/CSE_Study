#include <iostream>
#include <algorithm>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int backtracking[8];
int numbers[8];
bool isPrint[8][8];
int m, n;

void print(int depth){
    if(depth == n){
        int num = 0;
        
        for(int i = 0 ; i < depth ; i++){
            cout << backtracking[i] << " ";
        }

        cout << "\n";
        return;
    }

    for(int i = 0 ; i < m ; i++){
        if(isPrint[i][depth] || backtracking[depth] == numbers[i]) continue;
        backtracking[depth] = numbers[i];
        isPrint[i][depth] = true;
        print(depth + 1);
        isPrint[i][depth] = false;
    }
}

int main(){
    fastio;
   
    cin >> m >> n;
    for(int i = 0 ; i < m ; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers+m);
     
    print(0);
    return 0;
}

