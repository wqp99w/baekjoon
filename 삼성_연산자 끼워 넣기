#include<iostream>
#include <vector>

using namespace std;

long long Max = -1000000002,Min=1000000002;
vector<int> coun(4, 0);

void per(vector<int> input, vector<int> op,int depth,long long sum) {
	if (depth == input.size()) {
		Max = max(sum, Max); Min = min(sum, Min);
	}
	else {
		for (int i = 0; i < 4; i++) {
			if (coun[i] != op[i]) {
				coun[i]++;
				if (i == 0) per(input, op, depth + 1, sum + input[depth]);
				if (i == 1) per(input, op, depth + 1, sum - input[depth]);
				if (i == 2) per(input, op, depth + 1, sum * input[depth]);
				if (i == 3) per(input, op, depth + 1, sum / input[depth]);
				coun[i]--;
			}
		}
	}
}

int main() {
	int n;
	cin >> n;
	vector<int> input(n, 0),op(4);
	vector<bool> check(n, false);
	for (int i = 0; i < n; i++)
		cin >> input[i];
	int c;
	for (int i = 0; i < 4; i++)
		cin >> op[i];
	per(input, op, 1, input[0]);
	cout << Max << endl << Min << endl;
	return 0;
}
