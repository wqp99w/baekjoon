#include<iostream>
#include <vector>
#include <queue>

using namespace std;

int Map[5][5];
int Fmap[5][5];

int dfx[9] = { 0,0,-1,-1,-1,0,1,1,1 }; //물고기 방향
int dfy[9] = { 0,-1,-1,0,1,1,1,0,-1 };

int dx[5] = { 0,-1,0,1,0 }; //상어 방향 상 좌 하 우
int dy[5] = {0, 0,-1,0,1 };

struct Fish {
	int x;
	int y;
	int d;
};

struct in {
	int x;
	int y;
};

vector<in> check(3), Sgo(3);
int Maxfish = -1, Minindex=1000;
void dfs(int x,int y, int step,int fish, int index) {
	if (step == 3) {
		index /= 10;
		if ((fish == Maxfish && Minindex > index) || Maxfish < fish) {
			Minindex = index;
			Maxfish = fish;
			for (int i = 0; i < 3; i++) {
				check[i] = Sgo[i];
			}
		}
		return;
	}
	for (int i = 1; i <= 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx >= 1 && nx <= 4 && ny >= 1 && ny <= 4) {
			int temp = Map[nx][ny];
			Map[nx][ny] = 0;
			Sgo[step] = { nx,ny };
			dfs(nx, ny, step + 1, fish+temp,(index+i)*10);
			Map[nx][ny] = temp;
		}

	}
}


int main() {
	int m, s,fish=0;
	queue<Fish> FF;
	cin >> m >> s;
	for (int i = 0; i < m; i++) {
		int fx, fy, d;
		cin >> fx >> fy >> d;
		Map[fx][fy]++;
		FF.push({ fx, fy, d });
	}
	int sx, sy;
	cin >> sx >> sy;
	queue<in> isF;
	while (s--) {
		Maxfish = -1; Minindex = 1000;
		queue<Fish> TF=FF,temp; //물고기 복제
		while (!FF.empty()) { //물고기 이동
			Fish c = FF.front();
			FF.pop();
			for (int i = 1; i <= 8; i++) { //물고기 이동
				int nfx = c.x + dfx[c.d];
				int nfy = c.y + dfy[c.d];
				if ((nfx != sx || nfy != sy)&&(nfx >= 1 && nfx <= 4 && nfy >= 1 && nfy <= 4) && (Fmap[nfx][nfy] == 0)) { //범위 안벗어나고 물고기 냄새 없으면 이동
					Map[c.x][c.y]--;
					Map[nfx][nfy]++;
					c.x = nfx; c.y = nfy;
					break;
				}
				c.d--;
				if (c.d == 0) c.d = 8;
			}
			temp.push(c);
		}
		
		dfs(sx, sy, 0, 0,0);
		sx = check[2].x; sy = check[2].y;
		//냄새 지우기
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (Fmap[i][j] != 0) {
					Fmap[i][j]++;
					if (Fmap[i][j] == 3) Fmap[i][j] = 0;
				}
			}
		}
		//여까지 물고기 냄새
		for (auto c : check) { //상어가 물고기 먹고 냄새 남기기
			if (Map[c.x][c.y] != 0) {
				Map[c.x][c.y] = 0;
				Fmap[c.x][c.y] = 1;
			}
		}
		while (!temp.empty()) { //물고기 잡아 먹는거
			Fish c = temp.front();
			temp.pop();
			int che = 0;
			for (auto q : check) { //상어가 해당 물고기 먹었는지 확인
				if (c.x == q.x && c.y == q.y) { //먹었으면
					che = 1;
					break;
				}
			}
			if (che) continue;
			FF.push(c); //물고기 안먹었으면 원래 물고기 좌표에 넣어주기
		}
		while (!TF.empty()) { //물고기 복제하기
			Fish c = TF.front();
			TF.pop();
			Map[c.x][c.y]++;
			FF.push(c);
		}
	}
	for (int i = 1; i <= 4; i++)
		for (int j = 1; j <= 4; j++)
			fish += Map[i][j];
	cout << fish << endl;
}
