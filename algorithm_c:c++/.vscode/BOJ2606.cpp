#include <iostream>
#include <algorithm>
#include <cmath>
#include <queue>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

vector<int> computer[101];
bool isVisited[101];

int main()
{
    int n, m;

    cin >> n >> m;

    for (int i = 0; i < m; i++)
    {
        int start, end;
        cin >> start >> end;
        computer[start].push_back(end);
        computer[end].push_back(start);
    }

    queue<int> node;
    int count = 0;
    for (int nextNode : computer[1])
    {
        node.push(nextNode);
        count++;
        isVisited[nextNode] = true;
    }
    while (!node.empty())
    {
        int currentNode = node.front();
        node.pop();
        for (int nextNode : computer[currentNode])
        {
            if(isVisited[nextNode]) continue;
            
            node.push(nextNode);
            isVisited[nextNode] = true;
            count++;
        }
    }

    cout << count - 1;
    return 0;
}