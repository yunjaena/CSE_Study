#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>
#include <map>

#define INT32_MAX 2147483647
#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int n;
int num[5001];
int dp[5001][5001];

int palindrome(int left, int right)
{
    if (left >= right)
        return 0;

    int &ans = dp[left][right];

    if (ans != -1)
        return ans;

    if (num[left] == num[right])
        ans = palindrome(left + 1, right - 1);
    else
        ans = min(palindrome(left, right - 1), palindrome(left + 1, right)) + 1;
        
    return ans;
}

int main()
{
    fastio;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> num[i];
    }
    memset(dp, -1, sizeof(dp));
    cout << palindrome(0, n - 1);
    return 0;
}