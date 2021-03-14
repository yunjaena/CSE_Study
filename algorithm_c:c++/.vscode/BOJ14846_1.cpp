#include <iostream>
#include <algorithm>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);
using namespace std;

int dp[303][303][11];
int arr[303][303];



int main()
{
    fastio;

    int n;
    cin >> n;

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            cin >> arr[i][j];

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            for (int k = 1; k <= 10; k++)
            {
                dp[i][j][k] += dp[i][j - 1][k];
                dp[i][j][k] += dp[i - 1][j][k];
                dp[i][j][k] -= dp[i - 1][j - 1][k];
            }

            dp[i][j][arr[i][j]]++;
        }
    }

    int q;
    cin >> q;

    while (q--)
    {
        int x1, y1, x2, y2;
        int cnt = 0;

        cin >> x1 >> y1 >> x2 >> y2;

        int nums[11];

        for (int i = 1; i <= 10; i++)
            nums[i] = dp[x2][y2][i];

        for (int i = 1; i <= 10; i++)
        {
            nums[i] -= dp[x1 - 1][y2][i];
            nums[i] -= dp[x2][y1 - 1][i];
            nums[i] += dp[x1 - 1][y1 - 1][i];
        }

        for (int i = 1; i <= 10; i++)
            if (nums[i] > 0)
                cnt++;

        cout << cnt << "\n";
    }

    return 0;
}