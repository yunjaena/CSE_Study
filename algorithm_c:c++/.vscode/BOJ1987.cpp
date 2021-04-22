#include <iostream>
#include <algorithm>
#include <stack>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int r,c;
char board[21][21];
bool alphabetCheck[26];
int dir[][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
int ans = 0;



int getLetterIndex(int x, int y){
    return (int)(board[y][x] - 'A');
}

bool isPossible(int x, int y){
    if(x < 0 || y < 0 || x >= c || y >= r || alphabetCheck[getLetterIndex(x, y)]) return false;
    return true;
}

void bfs(int x, int y, int length){     
    if(length > ans){
        ans =  length;
    }

    for(int i = 0; i < 4 ; i++){
        int nx = x + dir[i][0];
        int ny = y + dir[i][1];

        if(!isPossible(nx, ny)) continue;

        int letter = getLetterIndex(nx, ny);
        alphabetCheck[letter] = true;
        bfs(nx, ny, length + 1);
        alphabetCheck[letter] = false;
    }
}

int main(){
    fastio;
    cin >> r >> c;

    for(int y = 0 ; y < r ; y++){
        for(int x = 0; x < c; x++){
            cin >> board[y][x];
        }
    }

    int letter = getLetterIndex(0, 0);
    alphabetCheck[letter] = true;
    bfs(0, 0, 1);

    cout << ans;

    return 0;
}