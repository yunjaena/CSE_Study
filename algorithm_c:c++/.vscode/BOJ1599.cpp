#include <iostream>
#include <algorithm>
#include <queue>
#include <map>

#define fastio                         \
    ios_base ::sync_with_stdio(false); \
    cin.tie(NULL);                     \
    cout.tie(NULL);

using namespace std;

int n;
vector<string> letters;
map<string, int> misik_map;
string minsik[] = { "a", "b", "k", "d", "e", "g", "h", "i", "l", "m", "n", "ng", "o", "p", "r", "s", "t", "u", "w", "y"};

bool minsick_compare(string s1, string s2){
    int index1 = 0;
    int index2 = 0;
    int maxIndex = min(s1.length() , s2.length());
    
   while ((index1 < maxIndex && index2 < maxIndex))
   {
       string c1;
       c1 += s1[index1];
       string c2;
       c2 += s2[index2];

       if(index1 + 1 < s1.length() && (c1 == "n") && s1[index1+1]=='g'){
           c1 += "g";
           index1++;
       }

      if(index2 + 1 < s2.length() && (c2 == "n") && s2[index2+1]=='g'){
           c2 += "g";
           index2++;
       }

        if(misik_map[c1] > misik_map[c2]){
            return false;
        }else if(misik_map[c1] < misik_map[c2]){
            return true;
        }

       index1++;
       index2++;
   }

   return s1.length() < s2.length();
}


int main(){
    fastio;
    cin >> n;
    for(int i = 0 ; i < 20 ; i++){
        misik_map[minsik[i]] = i;
    }

    for(int i = 0 ; i < n ; i++){
        string l;
        cin >> l;
        letters.push_back(l);
    }
    

    sort(letters.begin(), letters.end(), minsick_compare);

    for(string s : letters){
        cout << s << "\n";
    }

    return 0;
}