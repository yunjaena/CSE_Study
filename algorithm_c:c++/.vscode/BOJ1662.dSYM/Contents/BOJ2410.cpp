#include <iostream>
#include <algorithm>
#include <cmath>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);
#define MOD 1000000000

using namespace std;

int n, dp[1000001];
int main() {
	fastio;

	cin >> n;
	dp[0] = 1;
	for (int i = 1; i <= n; i <<= 1) {
		for (int j = 0; j <= n; j++) {
			if (j - i >= 0) {
				dp[j] = ((dp[j] % MOD) + (dp[j - i] % MOD)) % MOD;
			}
		}
	}
	cout << dp[n];
}

/*
https://dlwnsdud205.tistory.com/25
*/ 