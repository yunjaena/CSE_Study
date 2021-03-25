#include <iostream>
#include <algorithm>
#include <cmath>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

char star[3][5] = {
    {' ', ' ', '*', ' ', ' '},
    {' ', '*', ' ', '*', ' '},
    {'*', '*', '*', '*', '*'}};

char print[3074][6146];
int n, middle, width, height, maxDepth;

void printStar(int x, int y, int n)
{
    cout << x << ":" << y << "=>" << n <<  "\n";
    if (n == 3)
    {
        int sx = 0;
        int sy = 0;
        for (int cy = y; cy < y + 3; cy++, sy++)
        {
            sx = 0;
            for (int cx = x; cx < x + 5; cx++, sx++)
            {
                print[cy][cx] = star[sy][sx];
            }
        }
        return;
    }


    printStar(x, y, n / 2);
    printStar(x - n / 2, y + n / 2, n / 2);
    printStar(x - n / 2 + n, y + n / 2, n / 2);
}

int main()
{
    cin >> n;
    maxDepth = n / 3;
    height = n - 1;
    width = maxDepth * 6 - 2;
    middle = width / 2 - 2;
    //cout << maxDepth << " " << middle << " " << height << " " << width << "\n";

    printStar(middle, 0, n);

    for (int y = 0; y <= height; y++)
    {
        for (int x = 0; x <= width; x++)
        {
            if (print[y][x] == '*')
                cout << "*";
            else
                cout << " ";
        }
        cout << "\n";
    }
    return 0;
}

/**
 
  *  
 * * 
*****
    *  
 * * * 
*******

 */