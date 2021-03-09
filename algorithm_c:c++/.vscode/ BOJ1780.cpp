#include <iostream>
#include <algorithm>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

const int DIVIDER = 3;

int paper[2188][2188];
int n;
int cnt[3] = {0, 0, 0};

void paperAdd(int type)
{
    switch (type)
    {
    case -1:
        cnt[0]++;
        break;
    case 0:
        cnt[1]++;
        break;
    case 1:
        cnt[2]++;
        break;
    }
}

bool isSameBlock(int cx, int cy, int length)
{
    bool isSame = true;
    int num = paper[cy][cx];
    for (int y = cy; y < cy + length; y++)
    {
        for (int x = cx; x < cx + length; x++)
        {
            if (num != paper[y][x])
            {
                isSame = false;
                break;
            }
            num = paper[y][x];
        }
        if (!isSame)
            break;
    }
    if (isSame)
    {
        paperAdd(num);
    }

    return isSame;
}

void divide(int cx, int cy, int length)
{

    if (isSameBlock(cx, cy, length))
        return;

    if (length == DIVIDER)
    {
        for (int y = cy; y < cy + length; y++)
        {
            for (int x = cx; x < cx + length; x++)
            {
                paperAdd(paper[y][x]);
            }
        }
        return;
    }
    
    int divided_length = length / DIVIDER;

    // 1
    divide(cx, cy, divided_length);

    // 2
    divide(cx + divided_length, cy, divided_length);

    // 3
    divide(cx + (divided_length)*2, cy, divided_length);

    // 4
    divide(cx, cy + divided_length, divided_length);

    // 5
    divide(cx + (divided_length), cy + divided_length, divided_length);

    // 6
    divide(cx + (divided_length)*2, cy + divided_length, divided_length);

    // 7
    divide(cx, cy + (divided_length)*2, divided_length);

    // 8
    divide(cx + (divided_length), cy + (divided_length)*2, divided_length);

    // 9
    divide(cx + (divided_length)*2, cy + (divided_length)*2, divided_length);
}

int main()
{
    fastio;
    cin >> n;
    for (int y = 0; y < n; y++)
    {
        for (int x = 0; x < n; x++)
        {
            cin >> paper[y][x];
        }
    }
    divide(0, 0, n);

    cout << cnt[0] << "\n"
         << cnt[1] << "\n"
         << cnt[2];
    return 0;
}