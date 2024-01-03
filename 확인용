#include<iostream>
#include <vector>
#include <queue>
using namespace std;

int dx[4] = {0, 0, -1, 1};
int dy[4] = { -1,1,0,0 };

int main() {
	int m, n;
	char k;
	queue<pair<int, int>> RR,BB;
	string temp = "           ";
	cin >> n >> m;
	vector<string> input(n+1,temp);
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			cin >> k;
			input[i][j] = k;
			if (k == 'R') RR.push(pair<int, int>(i, j));
			if (k == 'B') BB.push(pair<int, int>(i, j));
		}
	}
	int con = 0;
	while (1) {
		con++;
		if (con > 10) {
			cout << -1;
			break;
		}
		queue<pair<int, int>> tR, tB;
		while (!RR.empty() && !BB.empty()) {
			pair<int, int> r = RR.front(), b = BB.front();
			RR.pop(); BB.pop();
			for (int i = 0; i < 4; i++) {
				pair<int, int> temp = r, temp2 = b;
				int check1 = 0, check2 = 0;
				
				while (input[temp.first][temp.second] != '#'|| input[temp2.first][temp2.second] != '#') {
					int do1 = 0, do2 = 0;
					if (!check1&&input[temp.first + dx[i]][temp.second + dy[i]] != '#'&& (temp.first + dx[i]!=temp2.first||temp.second + dy[i] != temp2.second)) {
						temp.first += dx[i]; temp.second += dy[i];
						do1 = 1;
					}
					if (!check2&&input[temp2.first + dx[i]][temp2.second + dy[i]] != '#' && (temp2.first + dx[i] != temp.first || temp2.second + dy[i] != temp.second)) {
						temp2.first += dx[i]; temp2.second += dy[i];
						do2 = 1;
					}
					if (!do1 && !do2) break;
					if (input[temp.first][temp.second] == 'O') {
						temp.first = 0; temp.second = 0;
						check1 = 1;
					}
					if (input[temp2.first][temp2.second] == 'O') {
						temp2.first = 1; temp2.second = 0;
						check2 = 1;
					}
				}
				if (check1 && !check2) {
					cout << con << endl;
					return 0;
				}
				if (!check1 && !check2 && (temp != r || temp2 != b)) {
					tR.push(temp); tB.push(temp2);
				}
			}
		}
		while (!tR.empty() && !tB.empty()) {
			RR.push(tR.front()); BB.push(tB.front());
			tR.pop(); tB.pop();
		}
		if (RR.empty() && BB.empty()) {
			cout << -1;
			break;
		}
	}
	return 0;
}
