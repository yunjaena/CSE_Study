#include <iostream>
#include <string>
#include <algorithm>
#include <math.h>

using namespace std;

unsigned long long a[51][2] = {0, };

int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    a[0][0] = 1;
    a[0][1] = 1;
    a[1][0] = 1;
    a[1][1] = 2;

    for (int y = 2; y <= 50; y++)
    {
        a[y][0] = a[y - 1][0] + a[y - 2][1];
        a[y][1] = a[y - 1][0] + a[y - 1][1];
    }

    int n;
    cin >> n;
    while (n-- > 0)
    {
        int t;
        cin >> t;
        int x = ceil(t / 2.0) - 1;
        cout << a[x][(t + 1) % 2] << "\n";
    }

    return 0;
}