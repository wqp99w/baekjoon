#include <iostream>
#include <vector>

using namespace std;

int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };
int con = 0;
int dfs(vector<vector<int>>& input, int r, int c, int dir) {
	if (input[r][c] == 0) {
		input[r][c] = 2;
		con++;
	}
	int check = 0,che=0;
	for (int i = 0; i < 4; i++) { //4방향에 청소할 칸이 있는지 확인
		if (input[r + dx[dir]][c + dy[dir]] == 0) {
			check = 1;
		}
		if (--dir < 0) dir += 4;
	}
	if (check) {
		for (int i = 0; i < 4; i++) {
			if (--dir < 0) dir += 4;//일단 돌고 봐야댐
			if (input[r + dx[dir]][c + dy[dir]] == 0) { //지울 수 있는 놈이 있다면
				check = 1;
				che = dfs(input, r + dx[dir], c + dy[dir], dir);
				break;
			}
		}
	}
	else { 
		if (input[r - dx[dir]][c - dy[dir]] != 1) {
			che = dfs(input, r - dx[dir], c - dy[dir], dir);
		}
		else //청소할 칸이 없는데 뒤에 벽이라면 종료
			return 0;
	}
	if (!che) return 0;
}

int main() {
	int n, m,r,c,dir;
	cin >> n >> m;
	cin >> r >> c >> dir;
	vector<int> TP(m);
	vector<vector<int>> input(n, TP);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> input[i][j];
	dfs(input, r, c, dir);
	cout << con << endl;
	return 0;
}
