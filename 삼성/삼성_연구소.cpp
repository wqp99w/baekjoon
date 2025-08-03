#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int dx[4] = { 0,0,-1,1 };
int dy[4] = { 1,-1,0,0 };

int con = 0;

int wall = 0;
int Min = 66;
void bfs_vir(vector<vector<int>> temp,queue<pair<int,int>> vir) {
	int n = temp.size(), m = temp[0].size();
	int count = vir.size();
	while (!vir.empty()) {
		int x = vir.front().first, y = vir.front().second;
		vir.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i], ny = y + dy[i];
			if (nx >= 0 && nx < n && ny >= 0 && ny < m&&temp[nx][ny]==0) { //바이러스가 퍼져 나가는데
				temp[nx][ny] = 2;
				count++;
				vir.push(pair<int, int>(nx, ny));
			}
		}
	}
	Min = min(Min, count);
}

vector<int> arr(3, 0);
int r = 3;
void combination(int depth, int next, vector<pair<int, int>> space, vector<vector<int>> map, queue<pair<int, int>> vir) {
	if (depth == r) {
		con++;
		for (int i = 0; i < depth; i++) {
			int x = space[arr[i]].first, y = space[arr[i]].second;
			map[x][y] = 1;
		}
		bfs_vir(map, vir);
		return;
	}
	for (int i = next; i < space.size(); i++) {
		arr[depth] = i;
		combination(depth + 1, i + 1,space,map,vir);
	}
}


int main() {
	int n, m;
	cin >> n >> m;
	vector<int> tp(m, 0);
	vector<vector<int>> map(n, tp);
	queue<pair<int, int>> vir;
	vector<pair<int,int>> space;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
			if (map[i][j] == 0) space.push_back(pair<int, int>(i, j));
			if (map[i][j] == 1) wall++;
			if (map[i][j] == 2) vir.push(pair<int, int>(i, j));
		}
	combination(0, 0, space, map,vir);
	cout << (n*m)-Min-(wall+3) << endl;
	return 0;
}
