#include<iostream>
#include <vector>
using namespace std;

int dfs(vector<vector<int>> input, vector<vector<int>> check, int con,int n) {
	if (con >= 5) {
		return 0;
	}
	int Max = 0;
	for (int i = 0; i < 4; i++) {
		vector<vector<int>> temp = input,che=check;
		if (i == 0) { //왼쪽
			for (int j = 1; j <= n; j++) {
				int point = 1;
				Max = max(Max, temp[j][1]);
				for (int k = 1; k <= n; k++) {
					if (temp[j][k] != 0&&k!=1) {
						if (temp[j][k] == temp[j][point] && che[j][point] == 0) { //같고 합쳐지지 않았을 때
							temp[j][point] = temp[j][point] * 2;
							Max = max(Max,temp[j][point]);
							che[j][point] = 1;
							temp[j][k] = 0;
						}
						else { //다르거나 이미 합쳐 졌을 때
							Max = max(Max, temp[j][k]);
							if(temp[j][point]==0)
								temp[j][point] = temp[j][k];
							else
								temp[j][++point] = temp[j][k];
							if(point!=k)
								temp[j][k] = 0;
						}
					}
				}
			}
		}
		if (i == 1) { //오른쪽
			for (int j = 1; j <= n; j++) {
				int point = n;
				Max = max(Max, temp[j][n]);
				for (int k = n; k >= 1; k--) {
					if (temp[j][k] != 0 && k != n) {
						if (temp[j][k] == temp[j][point] && che[j][point] == 0) { //같고 합쳐지지 않았을 때
							temp[j][point] = temp[j][point] * 2;
							Max = max(Max,temp[j][point]);
							che[j][point] = 1;
							temp[j][k] = 0;
						}
						else { //다르거나 이미 합쳐 졌을 때
							Max = max(Max, temp[j][k]);
							if (temp[j][point] == 0)
								temp[j][point] = temp[j][k];
							else
								temp[j][--point] = temp[j][k];
							if (point != k)
								temp[j][k] = 0;
						}
					}
				}
			}
		}
		if (i == 2) { //위쪽
			for (int j = 1; j <= n; j++) { //열 고정
				int point = 1;
				Max = max(Max, temp[1][j]);
				for (int k = 1; k <= n; k++) {
					if (temp[k][j] != 0 && k != 1) {
						if (temp[k][j] == temp[point][j] && che[point][j] == 0) { //같고 합쳐지지 않았을 때
							temp[point][j] = temp[point][j] * 2;
							Max = max(Max,temp[point][j]);
							che[point][j] = 1;
							temp[k][j] = 0;
						}
						else { //다르거나 이미 합쳐 졌을 때
							Max = max(Max, temp[k][j]);
							if (temp[point][j] == 0)
								temp[point][j] = temp[k][j];
							else
								temp[++point][j] = temp[k][j];
							if (point != k)
								temp[k][j] = 0;
						}
					}
				}
			}
		}
		if (i == 3) { //아래쪽
			for (int j = 1; j <= n; j++) { //열 고정
				int point = n;
				Max = max(Max, temp[n][j]);
				for (int k = n; k >= 1; k--) {
					if (temp[k][j] != 0 && k != n) {
						if (temp[k][j] == temp[point][j] && che[point][j] == 0) { //같고 합쳐지지 않았을 때
							temp[point][j] = temp[point][j] * 2;
							Max = max(Max,temp[point][j]);
							che[point][j] = 1;
							temp[k][j] = 0;
						}
						else { //다르거나 이미 합쳐 졌을 때
							Max = max(Max, temp[k][j]);
							if (temp[point][j] == 0)
								temp[point][j] = temp[k][j];
							else
								temp[--point][j] = temp[k][j];
							if (point != k)
								temp[k][j] = 0;
						}
					}
				}
			}
		}
		Max = max(dfs(temp, check, con + 1, n),Max);
	}
	return Max;
}

int main() {
	int n,k;
	cin >> n;
	vector<int> tp(n + 1, 0);
	vector<vector<int>> input(n + 1, tp);
	vector<vector<int>> check(n + 1, tp);
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> k;
			input[i][j] = k;
		}
	}
	int output = dfs(input, check, 0, n);
	cout << output << endl;
}
