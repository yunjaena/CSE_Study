#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string number_string;
    cin >> number_string;

    vector<int> numbers;

    for (int i = 0; i < number_string.length(); i++)
    {
        numbers.push_back(number_string[i] - '0');
    }

    
    sort(numbers.begin(), numbers.end(), greater<int>());

    for (int i = 0; i < numbers.size(); i++)
    {
        cout << numbers[i];
    }

    return 0;
}