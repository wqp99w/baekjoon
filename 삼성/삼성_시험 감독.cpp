#include <iostream>
#include <vector>
#include <cmath>
using namespace std;


int main() {
	int n,a,b,c;
	cin >> n;
	vector<int> input;
	for (int i = 0; i < n; i++) {
		cin >> a;
		input.push_back(a);
	}
	cin >> b >> c;
	long long output = n;
	for (auto q : input) {
		q -= b;
		if (q > 0) {
			output += (long long)((ceil((double)q / c )));
		}
	}
	cout << output << endl;
}
