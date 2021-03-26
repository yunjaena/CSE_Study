#include <iostream>
#include <string>
#include <algorithm>
#include <stack>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

string s;
bool isCorrect = true;

int calc(string letter)
{
    if (!isCorrect)
        return 0;

    stack<char> st;

    if (letter.length() < 2)
    {
        isCorrect = false;
        return 0;
    }
    else if (letter.length() == 2)
    {
        if (letter[0] == '(' && letter[1] == ')')
        {
            return 2;
        }
        else if (letter[0] == '[' && letter[1] == ']')
        {
            return 3;
        }
        else
        {
            isCorrect = false;
            return 0;
        }
    }

    char first_char = letter[0];
    int start = 0;
    int end = letter.length();
    int count = 0;
    int value;

    switch (first_char)
    {
    case '(':
        value = 2;
        break;
    case '[':
        value = 3;
        break;
    default:
        isCorrect = false;
        return 0;
        break;
    }

    st.push(first_char);

    for (int i = start + 1; i < end; i++)
    {
        count++;

        if (!st.empty() && st.top() == '(' && letter[i] == ')')
        {
            st.pop();
        }

        if (!st.empty() && st.top() == '(' && letter[i] == '(')
        {
            st.push('(');
        }

        if (!st.empty() && st.top() == '[' && letter[i] == ']')
        {
            st.pop();
        }

        if (!st.empty() && st.top() == '[' && letter[i] == '[')
        {
            st.push('[');
        }

        if (st.size() == 0)
        {
            break;
        }
    }

    if (st.size() != 0)
    {
        isCorrect = false;
        return 0;
    }

    if (count + 1 != letter.length())
    {
        return calc(letter.substr(start, count + 1)) + calc(letter.substr(count + 1));
    }
    else
    {
        return value * calc(letter.substr(start + 1, count - 1));
    }
}

int main()
{
    fastio;
    cin >> s;
    int ans = calc(s);

    if (s.length() >= 2 && isCorrect)
        cout << ans << "\n";
    else
        cout << 0 << "\n";
    return 0;
}
