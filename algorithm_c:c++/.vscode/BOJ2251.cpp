#include <iostream>
#include <algorithm>
#include <cmath>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int box[3];


bool isVisited[201];
bool isVisitedCheck[201][201][201];
int dir[6][2] = {
    {0, 1},
    {0, 2},
    {1, 0},
    {1, 2},
    {2, 0},
    {2, 1}
};


void dfs(int direction, int current_box[]){
    int cbox[] = {current_box[0], current_box[1], current_box[2]};
    int from = dir[direction][0];
    int to = dir[direction][1];
    int from_box_size = box[from];
    int to_box_size = box[to];
    int moveable_size = min(box[to] - cbox[to], cbox[from]);

   
    if(cbox[to] + moveable_size > to_box_size || cbox[from] - moveable_size < 0){ // 움직이는게 꽉 차있거나 옮기는 것 size = 0 인 경우 
        return;
    }

    cbox[to] += moveable_size;
    cbox[from] -= moveable_size;
    

    if(isVisitedCheck[cbox[0]][cbox[1]][cbox[2]]){
        return;
    }

    //cout << from << "=>" << to << " &&& " << cbox[0] << " : " << cbox[1] << " : " << cbox[2] << endl;

    isVisitedCheck[cbox[0]][cbox[1]][cbox[2]] =  true;
    
    if(cbox[0] == 0)
        isVisited[cbox[2]] = true;

    for(int i = 0 ; i < 6 ; i++){
        if(direction == i) continue;
        dfs(i, cbox);
    }
}

int main(){
    
    fastio;
    int current_box[3];
    cin >> box[0] >> box[1] >> box[2];
    current_box[0]  = current_box[1] = 0;
    current_box[2] = box[2];
    for(int i = 0 ; i < 6 ; i++){
        dfs(i, current_box);
    }
 
    for(int i = 0 ; i <= box[2] ; i++){
        if(isVisited[i]){
            cout << i << " ";
        }
    }
    return 0;
}
