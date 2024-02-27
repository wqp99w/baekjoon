#include <iostream>
#include <vector>

using namespace std;
vector<vector<long long>> matmul(vector<vector<long long>> x, vector<vector<long long>> y) {
	vector<vector<long long>> temp{ {0,0},{0,0} };

	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 2; j++) {
			long long sum = 0;
			for (int k = 0; k < 2; k++) {
				sum += x[i][k] * y[k][j];
			}
			temp[i][j] = sum % 1000000;
		}
	}
	return temp;
}


vector<vector<long long>> fibo(long long n) {
	vector<vector<long long>> base = {{0,1} ,{1,1}};
	if (n == 0) {
		base[0][1] = 0;
		return base;
	}
	else if (n == 1) {
		return base;
	}
	if (n % 2 == 0) { //짝수면
		vector<vector<long long>> temp = fibo(n / 2);
		return matmul(temp, temp);
	}
	else {
		vector<vector<long long>> temp = fibo((n-1) / 2);
		temp = matmul(temp, temp);
		return matmul(base, temp);
	}
}

int main() {
	long long n;
	cin >> n;
	vector<vector<long long>> output = fibo(n);
	cout << output[0][1] << endl;
	return 0;
}
