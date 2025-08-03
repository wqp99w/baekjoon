#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> matmul(vector<vector<int>> x, vector<vector<int>> y,long long n) {
	vector<vector<int>> temp = x;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int sum = 0;
			for (int k = 0; k < n; k++) {
				sum += (x[i][k] * y[k][j]) % 1000;
			}
			temp[i][j] = sum%1000;
		}
	}
	return temp;
}

vector<vector<int>> matpow(vector<vector<int>> input, long long n, long long b) {
	if (b == 1) {
		return input;
	}
	else {
		if (b % 2 == 0) {
			vector<vector<int>> temp = matpow(input,n, b / 2);
			return matmul(temp, temp,n);
		}
		else {
			vector<vector<int>> temp = matpow(input, n, (b-1) / 2);
			temp = matmul(temp, temp, n);
			return matmul(input, temp, n);
		}
	}
}

int main() {
	long long n, b;
	cin >> n >> b;
	vector<int> tp(n);
	vector<vector<int>> input(n, tp);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			cin >> input[i][j];
	vector<vector<int>> output = matpow(input, n, b);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			cout<< output[i][j]%1000<<' ';
		cout << '\n';
	}
	return 0;
}
