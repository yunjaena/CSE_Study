#include <iostream>
#include <cstring>
#include <string>
#include <algorithm>

using namespace std;

int N;
int M;

bool is_print[9] = {
    false,
};
int number[9] = {0};

void print(int num, int depth);

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    for (int i = 1; i <= N; i++)
    {
        number[0] = i;
        is_print[i] = true;
        print(i, 1);
        is_print[i] = false;
    }
    return 0;
}

void print(int num, int depth)
{
    if (depth == M)
    {
        for (int i = 0; i < M; i++)
        {
            cout << number[i] << " ";
        }
        cout << "\n";
        return;
    }

    for (int i = 1; i <= N; i++)
    {
        if (is_print[i])
            continue;
        number[depth] = i;
        is_print[i] = true;
        print(i, depth + 1);
        is_print[i] = false;
    }
}