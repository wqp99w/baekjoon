#include <iostream>
#include <vector>

using namespace std;

int people[21][21];

int vote[21][21];

int main() {
	int n;
	cin >> n;

	for (int i = 1; i <= n; i++) { //입력
		for (int j = 1; j <= n; j++) {
			cin >> people[i][j];
		}
	}
	int Min = 100000, con = 0;
	for (int x = 1; x <= n; x++) { //탐색
		for (int y = 1; y <= n; y++) {
			for (int d1 = 1; d1 < n; d1++) {
				for (int d2 = 1; d1 + d2 < n; d2++) {
					//cout << ++con << endl;
					if (y - d1<1 || y - d1 >= y || y + d2 <= y || y + d2>n || x + d1 + d2 > n) continue;
					for (int i = 1; i <= n; i++) {
						for (int j = 1; j <= n; j++) {
							vote[i][j] = 0;
						}
					}
					for (int i = 0; i <= d2; i++) {
						vote[x + d1 + i][y - d1 + i] = 5;
					}
					for (int i = 0; i <= d1; i++) {
						vote[x + d2 + i][y + d2 - i] = 5;
					}
					for (int i = 0; i <= d1; i++) {
						vote[x + i][y - i] = 5;
						if (i != d1) {
							int k = x + i + 1;
							while (vote[k][y - i] != 5) {
								vote[k][y - i] = 5;
								k += 1;
							}
						}
					}
					for (int i = 0; i <= d2; i++) {
						vote[x + i][y + i] = 5;
						if (i != d2) {
							int k = x + i + 1;
							while (vote[k][y + i] != 5) {
								vote[k][y + i] = 5;
								k += 1;
							}
						}
					}
					//여기까지가 5번 선거구역 나누는거
					vector<int> num(5, 0);
					for (int i = 1; i <= n; i++) { //맵 순회하면서 구역 나누기
						for (int j = 1; j <= n; j++) {
							if (vote[i][j] == 0) {
								if (1 <= i && i < x + d1 && 1 <= j && j <= y) {
									vote[i][j] = 1;
									num[0] += people[i][j];

								}
								else if (1 <= i && i <= x + d2 && y < j && j <= n) {
									vote[i][j] = 2;
									num[1] += people[i][j];
								}
								else if (x + d1 <= i && i <= n && 1 <= j && j < y - d1 + d2) {
									vote[i][j] = 3;
									num[2] += people[i][j];
								}
								else if (i >= x + d2 && i <= n && j >= y - d1 + d2 && j <= n) {
									vote[i][j] = 4;
									num[3] += people[i][j];
								}
							}
							else {
								num[4] += people[i][j];
							}
						}
					}
					//여기까지가 선거 구역 나누는거
					int maxmax = -1, minmin = 200000;
					for (int i = 0; i < 5; i++) {
						maxmax = max(maxmax, num[i]);
						minmin = min(minmin, num[i]);
					}
					if (Min > maxmax - minmin) {
						Min = maxmax - minmin;
					}
				}
			}
		}
	}
	cout << Min << endl;
	return 0;
}
