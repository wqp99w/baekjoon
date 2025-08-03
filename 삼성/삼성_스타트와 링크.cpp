#include <iostream>
#include <vector>

using namespace std;


int map[21][21];
int Min = 100000;

void comb(int depth, int next, vector<int>& arr) {
	if (depth == arr.size()/2) {
		int sum1 = 0, sum2 = 0;
		vector<int> temp1, temp2,check(arr.size());
		for (int i = 0; i < depth; i++)
			check[arr[i]] = 1;
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j < depth; j++) {
				if (arr[i] != arr[j])
					sum1 += map[arr[i]][arr[j]];
			}
		}
		for (int i = 0; i < check.size(); i++)
			if (check[i] == 0) {
				temp2.push_back(i);
			}
		for (int i = 0; i< temp2.size(); i++) {
			for (int j = 0; j < temp2.size(); j++) {
				if (temp2[i] != temp2[j])
					sum2+=+map[temp2[i]][temp2[j]];
			}
		}
		Min = min(Min, abs(sum1 - sum2));
	}
	else {
		for(int i=next;i<arr.size(); i++) {
			arr[depth] = i;
			comb(depth + 1, i + 1, arr);
		}
	}
}

int main() {
	int n;
	cin >> n;
	vector<int> arr(n, 0);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			cin >> map[i][j];
	comb(0, 0, arr);
	cout << Min << endl;
	return 0;
}
