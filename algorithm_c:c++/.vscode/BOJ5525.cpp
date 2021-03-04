#include <iostream>
#include <algorithm>
#include <string>
#include <cmath>

using namespace std;

int *create_kmp_table(string searchString)
{
    int *table = new int[searchString.length() + 1];
    fill(table, table + searchString.length() + 1, 0);

    table[0] = -1;

    for (int k = 1; k <= searchString.length(); k++)
    {
        int distance = ceil(k / 2.0);
        while (distance != k)
        {
            int same = 0;
            bool isSame = true;
            for (int i = 0; i < k - distance; i++)
            {
                int backIndex = distance + i;
                if (searchString[i] != searchString[backIndex])
                {
                    isSame = false;
                    break;
                }
                same++;
            }
            if (isSame)
            {
                table[k] = same;
                break;
            }
            distance++;
        }
    }
    return table;
}

int main()
{
    ios::sync_with_stdio(false);
	cin.tie(NULL), cout.tie(NULL);
    int n;
    char ch[2] = {'I', 'O'};
    int *kmpTable;
    string searchString;
    string totalString;
    int length;
    cin >> n;
    for (int i = 0; i < n * 2 + 1; i++)
    {
        searchString += ch[i % 2];
    }

    cin >> length;
    cin >> totalString;
    kmpTable = create_kmp_table(searchString);

    // for (int i = 0; i < searchString.length() + 1; i++)
    // {
    //     cout << kmpTable[i];
    // }

    int count = 0;
    int index = 0;

    while (true)
    {
        if (index > totalString.length())
            break;

        int sameLetter = 0;

        for (int i = 0; i < searchString.length(); i++)
        {
            if (totalString[index + i] == searchString[i]){
                sameLetter++;
            }
            else
                break;
        }

         int distance = sameLetter - kmpTable[sameLetter];
        if (sameLetter == searchString.length())
        {
            count++;
            distance = 1;
        }

       
        // cout<< index << ":" << sameLetter << ":"<< kmpTable[sameLetter] << "\n";
        index += distance;
       
    }

    // cout << "\n";
    cout << count;

    delete[] kmpTable;
    return 0;
}
