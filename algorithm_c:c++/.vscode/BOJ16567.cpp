#include <iostream>
#include <algorithm>
#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);
using namespace std;

int total = 0;
int a[1000002] = {
    0,
};

void filp(int i)
{
    if (a[i] != 1)
    {
        if ((a[i - 1] == 0 && a[i + 1] == 0))
        {
            total++;
        }
        else if ((a[i - 1] == 1 && a[i + 1] == 1))
        {
            if(total > 1)
                total--;
        }
    }
    a[i] = 1;
}
int main()
{
    fastio;

    int n, m;
    cin >> n >> m;

    for (int i = 1; i <= n; i++)
    {
        int value;
        cin >> value;
        if(value == 1)
            filp(i);
    }

    while (m-- > 0)
    {
        int type;
        cin >> type;
        if (type == 0)
        {
            cout << total << "\n";
        }
        else
        {
            int i;
            cin >> i;
            filp(i);
        }
    }

    return 0;
}