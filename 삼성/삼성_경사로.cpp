#include<iostream>
#include <vector>

using namespace std;

int main() {
	int n, l;
	cin >> n >> l;
	vector<int> tp(n, 0);
	vector<vector<int>> input(n, tp);
	for(int i=0;i<n;i++)
		for (int j = 0; j < n; j++)
			cin >> input[i][j];
	int con = 0;
	for (int i = 0; i < n; i++) {
		int temp = 0,j;
		vector<int> visited(n, 0);
		for (j = 0; j < n; j++) {
			if (j == 0) temp = input[i][j];
			else {
				if (temp != input[i][j]) {
					int check = 1, count = 0, save = 0;
					if (abs(temp - input[i][j]) > 1) break; //차이가 2이상이면 안되는거
					else if (temp > input[i][j]) //앞에 나오는 놈이 더 작으면 앞에 L칸 확인해서 경사로 두기
					{
						save = input[i][j];
						int s = j;
						while (j<n&&count < l && save == input[i][j]&&visited[j]==0) {
							count++;
							visited[j] = 1;
							j++;
						}
						if (count < l) { 
							j = s;
							break; 
						}
						temp = save;
						j--;
					}
					else { //앞에 나오는 놈이 더 크면 temp부터 뒤로 L칸 확인해서 경사로 두기
						int t = j-1;
						while (t>=0&&count < l && temp == input[i][t] && visited[t] == 0) {
							count++;
							visited[t] = 1;
							t--;
						}
						if (count < l) break;
						temp = input[i][j];
					}
				}
			}
		}
		if (j == n) con++;
	}

	for (int i = 0; i < n; i++) {
		int temp = 0, j;
		vector<int> visited(n, 0);
		for (j = 0; j < n; j++) {
			if (j == 0) temp = input[j][i];
			else {
				if (temp != input[j][i]) {
					int check = 1, count = 0, save = 0;
					if (abs(temp - input[j][i]) > 1) break; //차이가 2이상이면 안되는거
					else if (temp > input[j][i]) //앞에 나오는 놈이 더 작으면 앞에 L칸 확인해서 경사로 두기
					{
						save = input[j][i];
						int s = j;
						while (j<n&&count < l && save == input[j][i] && visited[j] == 0) {
							count++;
							visited[j] = 1;
							j++;
						}
						if (count < l) {
							j = s;
							break;
						}
						temp = save;
						j--;
					}
					else { //앞에 나오는 놈이 더 크면 temp부터 뒤로 L칸 확인해서 경사로 두기
						int t = j - 1;
						while (t>=0&&count < l && temp == input[t][i] && visited[t] == 0) {
							count++;
							visited[t] = 1;
							t--;
						}
						if (count < l) break;
						temp = input[j][i];
					}
				}
			}
		}
		if (j == n) con++;
	}
	cout << con << endl;
	return 0;
}
