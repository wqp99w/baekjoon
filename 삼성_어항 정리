#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

int map[100][100];

struct tank {
	int x;
	int y;
	int num;
};

int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };

void dist(int n) {
	queue<tank> temp;
	for (int i = 100 - n; i < 100; i++) { //n*n 정사각형 범위 탐색
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 0) continue; //어항 없는 곳은 스킵
			for (int k = 0; k < 4; k++) {
				int nx = i + dx[k], ny = j + dy[k];
				if ((nx<100 && nx>-1 && ny<n && ny>-1) && (map[nx][ny] != 0) && (map[nx][ny] < map[i][j])) {
					//nx ny가 범위를 벗어나지 않고, 0이 아니면서 현재 위치 값보다 작으면
					int d = (map[i][j] - map[nx][ny]) / 5;
					if (d > 0) {
						temp.push({ nx,ny,d }); //해당 좌표에 더해줄 값 추가
						temp.push({ i,j,-d }); //원래 위치에서 빼줄 값 추가
					}
				}
			}
		}
	}
	while (!temp.empty()) {
		tank tk = temp.front();
		map[tk.x][tk.y] += tk.num;
		temp.pop();
	}
}

void stra(int n) {
	vector<queue<tank>> temp(n);
	for (int i = 0; i < n; i++) { //일단 세로로 다 큐에 넣어주고
		if (map[99][i] != 0) {
			int j = 99;
			while (map[j][i] != 0) {
				temp[i].push({ j,i,map[j][i] });
				j--;
			}
		}
	}
	int j = 0;
	for (int i = 0; i < n; i++) {
		if (!temp[i].empty()) {
			while (!temp[i].empty()) {
				tank tk = temp[i].front();
				map[99][j] = tk.num;
				if (tk.x != 99)
					map[tk.x][tk.y] = 0;
				temp[i].pop();
				j++;
			}
		}
	}
}

void arange(int n) {
	vector<stack<tank>> st(n);
	for (int i = 0; i < 2; i++) {
		if (i == 0) {
			for (int j = 0; j < n / 2; j++) {
				st[0].push({ 99,j,map[99][j] });
				map[99][j] = 0;
			}
			int j = n / 2;
			while (!st[0].empty() && j < n) {
				tank tk = st[0].top();
				map[98][j] = tk.num;
				st[0].pop();
				j++;
			}
		}
		//처음 과정 끝
		else {
			int count = 0;
			while (count < 2) {
				for (int j = n / 2; j < n / 2 + n / 4; j++) {
					st[count].push({ 99 - count, j, map[99 - count][j] });
				}
				count++;
			}
			count--;
			int k = 97;
			while (count > -1) {
				for (int j = n / 2 + n / 4; j < n; j++) {
					tank tk = st[count].top();
					map[k][j] = tk.num;
					map[tk.x][tk.y] = 0;
					st[count].pop();
				}
				count--;
				k--;
			}
		}
	}
}

int calc(int n) {
	int Max = -1, Min = 1000000;
	queue<tank> MM;
	for (int i = 0; i < n; i++) {
		if (map[99][i] < Min) {
			while (!MM.empty()) MM.pop();
			MM.push({ 99,i,map[99][i] });
		}
		else if (map[99][i] == Min) MM.push({ 99,i,map[99][i] });
		Max = max(Max, map[99][i]);
		Min = min(Min, map[99][i]);
	}
	while (!MM.empty()) { //제일 작은놈들 하나 더 주기
		map[MM.front().x][MM.front().y]++;
		MM.pop();
	}
	return Max - Min;
}

int main() {
	int n, k;
	cin >> n >> k;
	vector<tank> input(n);
	int Min = 1000000;
	queue<tank> MM;
	for (int i = 0; i < n; i++) {
		cin >> map[99][i]; //바닥에 다 넣어두고
	}
	int con = 0;
	while (calc(n) > k) {
		con++;
		vector<queue<tank>> que(n / 2);
		for (int i = 0; i < n; i++) {
			int check = 0;
			if (i == 0) { //처음엔 맨 왼쪽 위로 올리기
				map[98][i + 1] = map[99][i];
				map[99][i] = 0;
			}
			else {
				int count = -1;
				if (map[99][i] != 0 && map[98][i] != 0) { //위에 뭐가 쌓여있다면
					for (int j = 99; j >= 0; j--) { //90도 돌리면 오른쪽으로 길이 구하기
						if (map[j][i] != 0)
							check++;
						else break;
					}
					while (i < n && map[98][i] != 0) { //위에 쌓여있는 애들 다 탐색
						count++;//몇번째로 나온지 세주는거
						for (int j = 99; j >= 0; j--) { //큐에 넣어주기
							if (map[j][i] != 0)
								que[count].push({ j,i, map[j][i] }); //90도 돌리기 위해서 큐에 넣어주기, tank 쓰는 이유는 나중에 움직일때 좌표 기억하기 위해서
							else break;
						}
						i++;
					}

				}
				if (map[99][i] != 0 && map[98][i] == 0) { //위에 안쌓여 있는 애가 처음으로 나온다면
					if (n - i >= check) { //오른쪽에 남아있는 어항이 쌓여있는 놈들보다 길면 90도 돌릴 수 있는거임
						int j = 98;
						while (count >= 0) {
							for (int k = i; k < i + check; k++) {
								tank temp = que[count].front();
								que[count].pop();
								map[j][k] = temp.num;
								map[temp.x][temp.y] = 0;//위로 올렸으면 없다고 처리하기
							}
							count--;
							j--;
						}
						i--;
					}
					else break;//돌릴 수 없으면 멈추기
				}
			}
		}
		//첫번째 정리 끝
		//물고기 분배
		dist(n);
		//다시 일렬로 정렬
		stra(n);
		arange(n); //두번째 정리
		//분배하고 일렬로 정렬
		dist(n);
		stra(n);
	}
	cout << con << endl;
	return 0;
}
