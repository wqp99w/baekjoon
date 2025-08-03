#include <iostream>
#include <vector>

using namespace std;

int Max = -1;

int n, m, cnt = 0, cnt2 = 0;
int input[9][9];
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };

vector<vector<vector<int>>> view = { {{1,0}},{{1,0},{-1,0}}, {{1,0},{0,-1}},{{1,0},{-1,0},{0,-1}} };

void go(int x, int y, int dir) {
	int nx = x + dx[dir], ny = y + dy[dir];
	while (nx >= 0 && nx < n && ny >= 0 && ny < m && input[nx][ny] != 6) {
		if (input[nx][ny] <= 0) {
			input[nx][ny]--;
			cnt++;
			if (input[nx][ny] < -1) cnt2++;
		}
		nx += dx[dir]; ny += dy[dir];
	}
}

void back(int x, int y, int dir) {
	int nx = x + dx[dir], ny = y + dy[dir];
	while (nx >= 0 && nx < n && ny >= 0 && ny < m && input[nx][ny] != 6) {
		if (input[nx][ny] <= 0) {
			input[nx][ny]++;
			cnt--;
			if (input[nx][ny] < 0) cnt2--;
		}
		nx += dx[dir]; ny += dy[dir];
	}
}

void dfs(vector < pair<int, pair<int, int>>> &CCTV, int depth) {
	if (depth == CCTV.size()) {
		Max = max(Max, cnt - cnt2);
		return;
	}
	int i = depth;
	int dir = 0, x = CCTV[i].second.first, y = CCTV[i].second.second, type = CCTV[i].first;
	if (type == 1) {
		for (int j = 0; j < 4; j++) {
			go(x, y, j);
			dfs(CCTV, depth + 1);
			back(x, y, j);
		}
	}
	else if (type == 2) {
		for (int j = 0; j < 2; j++) {
			go(x, y, j);
			go(x, y, j + 2);
			dfs(CCTV, depth + 1);
			back(x, y, j);
			back(x, y, j + 2);
		}
	}
	else if (type == 3) {
		for (int j = 0; j < 4; j++) {
			go(x, y, j);
			go(x, y, (j + 4 - 1) % 4);
			dfs(CCTV, depth + 1);
			back(x, y, j);
			back(x, y, (j + 4 - 1) % 4);
		}
	}
	else if (type == 4) {
		for (int j = 0; j < 4; j++) {
			go(x, y, j);
			go(x, y, (j + 2) % 4);
			go(x, y, (j + 4 - 1) % 4);
			dfs(CCTV, depth + 1);
			back(x, y, j);
			back(x, y, (j + 2) % 4);
			back(x, y, (j + 4 - 1) % 4);
		}
	}
	else {
		for (int j = 0; j < 4; j++) {
			go(x, y, j);
		}
		dfs(CCTV, depth + 1);
		for (int j = 0; j < 4; j++) {
			back(x, y, j);
		}
	}
}

int main() {
	int count=0;
	cin >> n >> m;
	vector < pair<int, pair<int, int>>> CCTV;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			cin >> input[i][j];
			if (input[i][j] == 0) count++;
			if (input[i][j] != 0 && input[i][j] != 6) {
				CCTV.push_back(pair<int, pair<int, int>>(input[i][j], pair<int, int>(i, j)));
			}
		}
	dfs(CCTV, 0);
	cout << count-Max << endl;

	return 0;
}
