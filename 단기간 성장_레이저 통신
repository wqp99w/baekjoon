#include <iostream>
#include <vector>
#include <queue>

using namespace std;

char map[101][101];

int dx[4] = { 0,-1,0,1 }; //우 상 좌 하
int dy[4] = { 1,0,-1,0 };
int w, h;
struct lay {
	int x;
	int y;
	int dir;
};
queue<lay> save;

int visit[101][101][4];

int dir_change(int dir, int state) {
	if (state == 0) { //오른쪽으로 꺾기
		switch (dir)
		{
		case 0:
			dir = 3;
			break;
		case 1:
			dir = 0;
			break;
		case 2:
			dir = 1;
			break;
		case 3:
			dir = 2;
		}
	}
	else if (state == 1) { //왼쪽으로 꺾기
		switch (dir)
		{
		case 0:
			dir = 1;
			break;
		case 1:
			dir = 2;
			break;
		case 2:
			dir = 3;
			break;
		case 3:
			dir = 0;
		}
	}
	return dir;
}

lay goal;

int bfs() {
	int con = 0;
	while (true) {
		queue<lay> T_que;
		while (!save.empty()) {
			lay temp = save.front();
			save.pop();
			int x = temp.x, y = temp.y;
			while ((x > -1 && x<h && y>-1 && y < w) && map[x][y] != '*') { //범위 벗어나거나 벽을 만날 때 까지
				if (visit[x][y][temp.dir] == 0) {
					for (int i = 0; i < 2; i++) {
						int dir = dir_change(temp.dir, i);
						T_que.push({ x,y,dir });
					}
				}
				else break;
				if (x == goal.x && y == goal.y)
					return con;
				visit[x][y][temp.dir] = 1;
				x += dx[temp.dir]; y += dy[temp.dir];
			}
		}
		while (!T_que.empty()) {
			save.push(T_que.front());
			T_que.pop();
		}
		con++;
	}
}

int main() {
	cin >> w >> h;
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			cin >> map[i][j];
			if (map[i][j] == 'C' && save.empty()) {
				for (int k = 0; k < 4; k++)
					save.push({ i,j,k });
			}
			else if (map[i][j] == 'C' && !save.empty())
				goal = { i,j,0 };
		}
	}
	cout << bfs() << endl;
	return 0;
}
