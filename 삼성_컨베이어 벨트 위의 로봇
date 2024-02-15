#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n, k, a,con=0;
	cin >> n >> k;
	vector<int> life(2 * n+1, 0); //1부터 2n까지 벨트 내구도
	vector<int> belt(n+1, 0); //1부터 n번째 칸 
	for (int i = 1; i <= 2 * n; i++) {
		cin >> life[i]; //벨트 내구도 넣어주기
		if (i <= n) belt[i] = i; //초기 벨트는 1부터 n으로 되어있음
	}
	int step = 0;
	vector<int> robot(n + 1, 0); //로봇은 1부터 n까지 칸에서만 움직이니까
	queue<int> robot_que;
	while (con < k) { //내구도가 0인 벨트 칸이 k개보다 적을 때 반복
		step++;
		queue<int> temp;
		for (int i = 1; i <= n; i++) { //벨트 회전
			belt[i]--; //벨트 움직이는거 구현
			if (belt[i] == 0) belt[i] = 2 * n;
		}
		if (!robot_que.empty()) { //로봇이 있는 경우
			while (!robot_que.empty()) {
				int index = robot_que.front();
				robot_que.pop();
				if (robot[index + 1] == 0) { //벨트랑 로봇 같이 한칸 이동하는 경우
					robot[index++] = 0;
					if (index != n) robot[index] = 1; //내리는 곳 안갔으면 이동
				}
				//한칸 이동한 후에 또 한칸 갈 수 있는지
				if (index+1<=n&&life[belt[index+1]]>0 && robot[index + 1] == 0) { //벨트 범위로 움직일 수 있고,로봇이 자동으로 움직이고, 혼자 움직일 때 앞에 로봇이 없다면,
					robot[index++] = 0;
					if (index != n) { //로봇이 내리는 위치로 안갔으면
						robot[index] = 1;
					}
					life[belt[index]]--;
					if (life[belt[index]] == 0) con++;
				}
				if (index != n) temp.push(index);
			}
		}
		if (life[belt[1]] > 0&&robot[1]==0) { //로봇 올리는 칸의 내구도가 0보다 크면 로봇 올리기
			robot[1] = 1; //1번째 칸에 로봇 올리니까 이렇게
			if (--life[belt[1]] == 0) con++; //내구도 0되면 con증가
			temp.push(1);
		}
		while (!temp.empty()) {
			robot_que.push(temp.front());
			temp.pop();
		}
	}
	cout << step << endl;
}
