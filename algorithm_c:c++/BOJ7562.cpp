#include <iostream>
#include <string>
#include <cstring>
#include <algorithm>
#include <queue>

using namespace std;

const int cx[8] = {1, 2, 2, 1, -1, -2, -2, -1};
const int cy[8] = {2, 1, -1, -2, -2, -1, 1, 2};
bool is_visited[301][301];
pair<int, int> start_point;
pair<int, int> end_point;
int movement;
int board_size;

void bfs(int sx, int sy, int ex, int ey);
bool can_move(int x, int y);

class Node
{
public:
    int x;
    int y;
    int move;

    Node(int x, int y, int move)
    {
        this->x = x;
        this->y = y;
        this->move = move;
    }
};

int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n;

    cin >> n;

    while (n-- > 0)
    {
        memset(is_visited, false, sizeof(is_visited));
        movement = 2147483647;
        cin >> board_size;
        cin >> start_point.first >> start_point.second >> end_point.first >> end_point.second;
        bfs(start_point.first, start_point.second, end_point.first, end_point.second);
        cout << movement << "\n";
    }

    return 0;
}

void bfs(int sx, int sy, int ex, int ey)
{

    queue<Node> q;
    q.push(Node(sx, sy, 0));

    while (!q.empty())
    {
        Node next = q.front();
        q.pop();

        if (next.x == ex && next.y == ey)
        {
            if (next.move < movement)
                movement = next.move;

            continue;
        }
        for (int i = 0; i < 8; i++)
        {

            int nx = next.x + cx[i];
            int ny = next.y + cy[i];
            int move = next.move + 1;

            if (can_move(nx, ny))
            {
                q.push(Node(nx, ny, move));
                is_visited[nx][ny] = true;
            }
        }
    }
}

bool can_move(int x, int y)
{
    if (x < 0 || y < 0 || x >= board_size || y >= board_size || is_visited[x][y])
        return false;

    return true;
}