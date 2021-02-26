#include <iostream>
#include <algorithm>
#include<queue>

using namespace std;
int n, m, f[101][101], check[101][101];

int main(void){
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;

    int a, b;

    for (int i = 0; i < m; i++){
        cin >> a >> b;
        f[a][b] = f[b][a] = 1;
    }
 
    int min = 2147483647 , ans = 0;
    queue<int> q;

    for (int i = 1; i < n + 1; i++){
        q.push(i);
        check[i][i] = true;
 
        int temp = 0;
        while (!q.empty()){
            int now = q.front();
            q.pop();
 
            for (int j = 1; j < n + 1; j++){
                if (!check[i][j] && f[now][j] == 1){
                    q.push(j);
                    f[i][j] = f[i][now] + f[now][j];
                    check[i][j] = true;
                    temp += f[i][j];
                }
            }
        }
 
        if (temp < min){
            min = temp;
            ans = i;
        }
    }
    cout << ans << "\n";

    return 0;
}
