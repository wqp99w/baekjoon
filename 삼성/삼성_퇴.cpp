#include <iostream>
#include <vector>

using namespace std;

int dfs(vector<pair<int, int>> input, int c) {
	int n = input.size()-1, i = c,sum=0;
	if (i <= n && (i + input[i].first) - 1 <= n) {
		sum = input[c].second;
		int Maxj = i, Max = 0;
		for (int j = i+input[i].first; j <= n; j++) {
			if (j + input[j].first - 1 <= n) {
				int temp = dfs(input, j);
				if (Max < temp) {
					Max = temp;
					Maxj = j;
				}
			}
		}
		sum += Max;
		i += Maxj;
	}
	return sum;
}

int main() {
	int n,d,c;
	cin >> n;
	vector<pair<int, int>> input(n + 1);
	for (int i = 1; i <= n; i++) {
		cin >> d >> c;
		input[i]=(pair<int, int>(d, c));
	}
	int Max = -1;
	for (int i = 1; i <= n; i++) {
		Max = max(Max, dfs(input, i));
	}
	cout << Max << endl;
	return 0;
}
