#include <iostream>
#include <algorithm>
#include <queue>
#include <tuple>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int maze[1001][1001];
bool isVisited[1001][1001][11];

int dx[] = {1, 0, -1, 0};
int dy[] = {0, -1, 0, 1};

int total_move = 2147483647;

int n, m, k;

bool is_valid(int cx, int cy)
{
    if (cx < 1 || cy < 1 || cy > n || cx > m)
        return false;

    return true;
}

bool is_wall(int cx, int cy)
{
   return maze[cy][cx] == 1;
}

bool can_break(int current_break)
{
    return (current_break + 1 <= k);
}

int main()
{
    fastio;
    queue<tuple<int, int, int, int>> q;

    cin >> n >> m >> k;

    for (int y = 1; y <= n; y++)
    {
        for (int x = 1; x <= m; x++)
        {
		char ch;
		cin >> ch;
            	maze[y][x] = ch - '0';
        }
    }

    q.push(make_tuple(1, 1, 1, 0));

    while (!q.empty())
    {
        tuple<int, int, int, int> current = q.front();
        q.pop();

        int cx = get<0>(current);
        int cy = get<1>(current);
        int move = get<2>(current);
        int broke = get<3>(current);

        if (cx == m && cy == n)
        {
            total_move = move;
            break;
        }

        for (int i = 0; i < 4; i++)
        {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (is_valid(nx, ny))
            {
		if(!is_wall(nx, ny) && !isVisited[ny][nx][broke]){
			 q.push(make_tuple(nx, ny, move + 1, broke));
              		 isVisited[ny][nx][broke] = true;
		} else if(can_break(broke) && !isVisited[ny][nx][broke + 1]){
			q.push(make_tuple(nx, ny, move + 1, broke + 1));
              	        isVisited[ny][nx][broke + 1] = true;
		}
            }
          
        }
    }

    if (total_move == 2147483647)
        cout << "-1";
    else
        cout << total_move;

    return 0;
}