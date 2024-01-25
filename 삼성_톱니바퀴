#include <iostream>
#include <vector>
#include <string>
#include <cmath>
using namespace std;

int main() {
	vector<int> tp(8, 0);
	vector<vector<int>> input(5,tp);
	string in;
	for (int i = 1; i <= 4; i++) {
		cin >> in;
		for (int j = 0; j < 8; j++) {
			input[i][j] = in[j] - '0';
		}
	}
	int k, c,index;
	cin >> k;
	while (k--) {
		cin >> index >> c;
		int turn = -1 * c;
		vector<pair<int,int>> check;
		check.push_back(pair<int, int>(index, c));
		for (int i = index - 1; i >= 1; i--) { //왼쪽 탐색
			if (input[i][2] != input[i + 1][6]) {
				check.push_back(pair<int, int>(i, turn));
				turn *= -1;
			}
			else break;
		}
		turn = -1 * c;
		for (int i = index + 1; i <= 4; i++) { //오른쪽 탐색
			if (input[i][6] != input[i - 1][2]) {
				check.push_back(pair<int, int>(i, turn));
				turn *= -1;
			}
			else break;
		}
		for (auto c : check) { //회전
			if (c.second > 0) { //시계방향 회전
				int temp = input[c.first][7];
				for (int i = input[c.first].size() - 1; i > 0; i--) {
					input[c.first][i] = input[c.first][i-1];
				}
				input[c.first][0] = temp;
			}
			else { //반시계
				int temp = input[c.first][0];
				for (int i = 0; i < input[c.first].size() - 1; i++) {
					input[c.first][i] = input[c.first][i+1];
				}
				input[c.first][7] = temp;
			}
		}
	}
	int sum = 0;
	for (int i = 1; i <= 4; i++)
		if (input[i][0] == 1)
			sum += pow(2, i-1);
	cout << sum << endl;
	return 0;
}
