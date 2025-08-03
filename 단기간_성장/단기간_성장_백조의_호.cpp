#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int dx[4] = { 0,-1,1,0 };
int dy[4] = { 1,0,0,-1 };

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int r, c;
	cin >> r >> c;
	vector<char> tp(c, '0');
	vector<vector<char>> input(r, tp);
	vector<bool> TP(c, false);
	vector<pair<int, int>> swan;
	queue<pair<int, int>> water;
	char in;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cin >> in;
			input[i][j] = in;
			if ('X' != in)
				water.push(pair<int, int>(i, j));
			if ('L' == in)
				swan.push_back(pair<int, int>(i, j));
		}
	}
	int con = 0;
	vector<vector<bool>> visited(r, TP);
	visited[swan[0].first][ swan[0].second] = true;

	queue<pair<int, int>> que;
	que.push(pair<int, int>(swan[0].first, swan[0].second));

	while (true) {
		queue<pair<int, int>> Temp;
		int check = 0;
		while (!que.empty()) {
			int x = que.front().first, y = que.front().second;
			que.pop();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if ((nx >= 0 && nx < r && ny >= 0 && ny < c)&& !visited[nx][ny]){
					if (input[nx][ny] != 'X') {
						if (nx == swan[1].first && swan[1].second == ny) {
							check = 1;
							break;
						}
						visited[nx][ny] = true;
						que.push(pair<int, int>(nx, ny));
					}
					else {
						visited[nx][ny] = true;
						Temp.push(pair<int, int>(nx, ny));
					}
				}
			}
			if (check)
				break;
		}
		que = Temp;
		if (check) {
			cout << con << endl;
			return 0;
		}
		int wc = water.size();
		while (wc--) {
			int x = water.front().first, y = water.front().second;
			water.pop();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if ((nx >= 0 && nx < r && ny >= 0 && ny < c) && input[x][y] != 'X' && input[nx][ny] == 'X') {
						input[nx][ny] = '.';
						water.push(pair<int, int>(nx, ny));
				}
			}
		}
		con++;
	}

	
	return 0;
}
