#include<iostream>
#include <vector>
using namespace std;
#define P pair<int,int>

int map[501][501];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) 
			cin >> map[i][j];
	vector<vector<P>> O = { { P(0,0),P(0,1),P(1,0) ,P(1,1)} };
	vector<vector<P>> I = { {P(0,0),P(0,1),P(0,2),P(0,3)},{P(0,0),P(1,0),P(2,0),P(3,0)} };
	vector<vector<P>> T = { {P(0,0),P(0,1),P(0,2),P(1,1)},{P(1,0),P(0,1),P(1,1),P(2,1)},{P(1,0),P(1,1),P(1,2),P(0,1)},{P(1,1),P(0,0),P(1,0),P(2,0)} };
	vector<vector<P>> Z = { {P(0,0),P(1,0),P(1,1),P(2,1)}, {P(2,0),P(1,0),P(1,1),P(0,1)}, {P(0,0),P(0,1),P(1,1),P(1,2)}, {P(0,2),P(0,1),P(1,1),P(1,0)} };
	vector<vector<P>> L = { {P(0,0),P(1,0),P(2,0),P(2,1)}, {P(0,0),P(0,1),P(0,2),P(1,0)},{P(0,0),P(0,1),P(1,1),P(2,1)},{P(0,2),P(1,0),P(1,1),P(1,2)},
							{P(0,1),P(1,1),P(2,1),P(2,0)}, {P(1,0),P(1,1),P(1,2),P(0,0)},{P(0,1),P(0,0),P(1,0),P(2,0)},{P(1,2),P(0,0),P(0,1),P(0,2)}};
	vector<vector<vector<P>>> Mino = { {O},{I}, {T},{Z},{L} };
	
	int Max = -1;
	for (auto c : Mino) {
		for (auto q : c) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					int temp = 0, check = 1;
					for (int k = 0; k < 4; k++) {
						int nx = q[k].first + i, ny = q[k].second + j;
						if (nx >= 0 && nx < n && ny >= 0 && ny < m)
							temp += map[nx][ny];
						else {
							check = 0;
							break;
						}
					}
					if (check)
						Max = max(Max, temp);
				}
			}
		}
	}

	cout << Max << endl;
	return 0;
}
