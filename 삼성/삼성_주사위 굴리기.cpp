#include <iostream>
#include <vector>

using namespace std;

int dx[5] = { 0,0,0,-1,1 };
int dy[5] = { 0,1,-1,0,0 };

int map[21][21];

int main() {
	int n, m, t, x, y, c;
	cin >> n >> m >> x >> y >> t;
	for(int i=0;i<n;i++)
		for (int j = 0; j < m; j++) {
			cin >> c;
			map[i][j] = c;
		}
	vector<int> tp(3, 0);
	vector<vector<int>> dice(4, tp);
	while (t--) {
		cin >> c;
		int nx = x + dx[c];
		int ny = y + dy[c];
		if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
			vector<vector<int>> temp = dice;
			switch (c) {
			case 1: //동
				dice[1][0] = temp[3][1];
				dice[1][1] = temp[1][0];
				dice[1][2] = temp[1][1];
				dice[3][1] = temp[1][2];
				break;
			case 2: //서
				dice[1][0] = temp[1][1];
				dice[1][1] = temp[1][2];
				dice[1][2] = temp[3][1];
				dice[3][1] = temp[1][0];
				break;
			case 3: //북
				dice[0][1] = temp[1][1];
				dice[1][1] = temp[2][1];
				dice[2][1] = temp[3][1];
				dice[3][1] = temp[0][1];
				break;
			case 4: //남
				dice[0][1] = temp[3][1];
				dice[1][1] = temp[0][1];
				dice[2][1] = temp[1][1];
				dice[3][1] = temp[2][1];
				break;
			default:
				break;
			}
			if (map[nx][ny] == 0) // 맵이 0이면 주사위 바닥 값을 복사
				map[nx][ny] = dice[3][1];
			else {
				dice[3][1] = map[nx][ny];
				map[nx][ny] = 0;
			}
			x = nx; y = ny;
			cout << dice[1][1] << endl;
		}
	}
	return 0;
}
