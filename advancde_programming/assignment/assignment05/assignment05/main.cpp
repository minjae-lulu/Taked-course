// 20161190 leeminjae
#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <climits>
using namespace std;
const int MAX = INT_MAX;

int ans = MAX;
int Sx, Sy, Ex, Ey; // i set global variable cuz using avoid many parameter with sol and main function.
int n;
bool visited[22][22]; // to check knight visit or not
vector<pair<int, int>> path;
vector<pair<int, int>> real_path; // to save real path vector

void sol(int x, int y, int cnt){
    if (cnt > 20 || cnt > ans)  return; // base condition
    if (x == Ex && y == Ey){
        path.push_back({x, y});
        if (ans > path.size()){ // select minist value
            ans = path.size();
            real_path = path;
        }
        path.pop_back(); // to find minist cnt if ans < pathsize we delete value.
        return;
    }
    int dx[8] = {1, 1, 2, 2, -1, -1, -2, -2}; // knight can move 8 position.
    int dy[8] = {2, -2, 1, -1, 2, -2, 1, -1};

    visited[x][y] = true; // If knight visit this potision, change true.
    path.push_back({x, y}); // save knight path.
    
    for (int i = 0; i < 8; i++){ // Move knight 8 position
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && nx < n && ny >= 0 && ny < n){ // ignore out of board case
            if (visited[nx][ny] == false)
                sol(nx, ny, cnt + 1);
        }
    }
    visited[x][y] = false; // Think revisiting in another case
    path.pop_back();       // Think revisiting in another case
}

int main(void)
{
    cout << "Size of board: ";
    cin >> n;
    cout << "Start position: ";
    cin >> Sx >> Sy;
    cout << "End position: ";
    cin >> Ex >> Ey;

    sol(Sx, Sy, 0);

    if (ans == MAX){
        cout << "The knight cannot move there" << '\n';
    }
    else{
        cout << "Can be done in " << ans - 1 << " move" << '\n';
        for (int i = 0; i < ans - 1; i++){
            cout << "(" << real_path[i].first << ", " << real_path[i].second << ") -> ";
        }
        cout << "(" << real_path[ans - 1].first << ", " << real_path[ans - 1].second << ")" << '\n';
    }
}