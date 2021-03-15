#include <iostream>
#include <algorithm>
#include <stack>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

stack<int> length_stack;
stack<int> repeat_stack;
string s;
int main()
{
    fastio;
    int length = 0;
    cin >> s;
    for (int i = 0; i < s.length(); i++)
    {
        char c = s[i];
        
        switch (c)
        {
        case '(':
            length--;
            length_stack.push(length);
            repeat_stack.push(s[i - 1] - '0');
            length = 0;
            break;
        case ')':
        {
            if (repeat_stack.empty()) continue;
            int repeat_length = repeat_stack.top();
            length *= repeat_length;
            repeat_stack.pop();

           if (length_stack.empty()) continue;
            int current_length = length_stack.top();
            length += current_length;
            length_stack.pop();
            break;
        }
        default:
            length++;
            break;
        }
    }

    cout << length;

    return 0;
}