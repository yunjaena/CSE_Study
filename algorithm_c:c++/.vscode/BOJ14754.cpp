#include <iostream>
#include <algorithm>
#include <set>

#define fastio                        \
    ios_base::sync_with_stdio(false); \
    cin.tie(NULL);                    \
    cout.tie(NULL);

using namespace std;

int xMax[1001];
int yMax[1001];
long long sum = 0;
set<int> nums;

int main()
{
    fastio;
    int n, m;
    cin >> n >> m;
    for (int y = 0; y < n; y++)
    {
        for (int x = 0; x < m; x++)
        {
            int num;
            cin >> num;

            if (yMax[y] < num)
            {
                yMax[y] = num;
            }

            if (xMax[x] < num)
            {
                xMax[x] = num;
            }

            sum += num;
        }
    }

    for (int y = 0; y < n; y++)
    {
        nums.insert(yMax[y]);
    }

    for (int x = 0; x < m; x++)
    {
        nums.insert(xMax[x]);
    }

    long long maxSum = 0;

    for (int i : nums)
    {
        maxSum += i;
    }

    cout << (sum - maxSum);
    return 0;
}