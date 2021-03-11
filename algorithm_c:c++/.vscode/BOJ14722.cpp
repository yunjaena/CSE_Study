#include <iostream>
#include <algorithm>

#define fastio                        \
    ios_base::sync_with_stdio(false); \
    cin.tie(NULL);                    \
    cout.tie(NULL);

using namespace std;

int city[1001][1001];
int dp[1001][1001][3];
int n;

int main()
{
    fastio;
    cin >> n;
    for (int y = 0; y < n; y++)
    {
        for (int x = 0; x < n; x++)
        {
            cin >> city[y][x];
        }
    }

    if (city[0][0] == 0)
    {
        dp[0][0][0] = 1;
    }

    for (int x = 1; x < n; x++)
    {
        int current = city[0][x];

        if (current == 0)
        {
            dp[0][x][0] = dp[0][x - 1][2] + 1;
        }
        else
        {
            dp[0][x][0] = dp[0][x - 1][0];
        }

        if (current == 1 && dp[0][x][2] < dp[0][x][0])
        {
            dp[0][x][1] = dp[0][x - 1][0] + 1;
        }
        else
        {
            dp[0][x][1] = dp[0][x - 1][1];
        }

        if (current == 2 && dp[0][x][0] < dp[0][x][1])
        {
            dp[0][x][2] = dp[0][x - 1][1] + 1;
        }
        else
        {
            dp[0][x][2] = dp[0][x - 1][2];
        }
    }

    for (int y = 1; y < n; y++)
    {
        int current = city[y][0];

        if (current == 0)
        {
            dp[y][0][0] = dp[y - 1][0][2] + 1;
        }
        else
        {
            dp[y][0][0] = dp[y - 1][0][0];
        }

        if (current == 1 && dp[y][0][2] < dp[y][0][0])
        {
            dp[y][0][1] = dp[y - 1][0][0] + 1;
        }
        else
        {
            dp[y][0][1] = dp[y - 1][0][1];
        }

        if (current == 2 && dp[y][0][0] < dp[y][0][1])
        {
            dp[y][0][2] = dp[y - 1][0][1] + 1;
        }
        else
        {
            dp[y][0][2] = dp[y - 1][0][2];
        }
    }

    for (int y = 1; y < n; y++)
    {
        for (int x = 1; x < n; x++)
        {
            int current = city[y][x];

            if (current == 0)
            {
                dp[y][x][0] = max(dp[y][x - 1][2] + 1, dp[y - 1][x][2] + 1);
            }
            else
            {
                dp[y][x][0] = max(dp[y][x - 1][0], dp[y - 1][x][0]);
            }

            if (current == 1 && dp[y][x][2] < dp[y][x][0])
            {
                dp[y][x][1] = max(dp[y][x - 1][0] + 1, dp[y - 1][x][0] + 1);
            }
            else
            {
                dp[y][x][1] = max(dp[y][x - 1][1], dp[y - 1][x][1]);
            }

            if (current == 2 && dp[y][x][0] < dp[y][x][1])
            {
                dp[y][x][2] = max(dp[y][x - 1][1] + 1, dp[y - 1][x][1] + 1);
            }
            else
            {
                dp[y][x][2] = max(dp[y][x - 1][2], dp[y - 1][x][2]);

            }
        }
    }

    cout << max(max(dp[n - 1][n - 1][0], dp[n - 1][n - 1][1]), dp[n - 1][n - 1][2]);

    return 0;
}