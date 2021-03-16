#include <iostream>
#include <algorithm>
#include <string>
#include <queue>
#include <tuple>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

string s;
bool isSelected[101];

int main()
{
    fastio;
    cin >> s;

    for (int i = 0; i < s.length(); i++)
    {
        vector<pair<string, int> > letters;

        // 문자수를 늘려가면서 비교
        for (int j = 0; j < s.length(); j++)
        {
            if (!isSelected[j])
            {
                string predict;
                for (int k = 0; k < s.length(); k++)
                {
                    // 선택이 되어있지 않거나 이미 선택되어있는 경우
                    if (j == k || isSelected[k])
                    {
                        predict += s[k];
                    }
                }
                letters.push_back(make_pair(predict, j));
            }
        }
        sort(letters.begin(), letters.end());
        cout << letters[0].first << "\n";
        isSelected[letters[0].second] = true; 
    }

    return 0;
}
