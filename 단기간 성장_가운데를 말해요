#include<iostream>
#include <vector>
#include <queue>
using namespace std;


int main() {
	ios::sync_with_stdio(false); //c와 싱크로를 false한다는 의미로, 이거 적으면 c 스타일의 입출력을 하지 못하는 대신 c++ 스타일 입출력이 빨라짐
	cin.tie(NULL); //이거는 원래 cout을 먼저 하고 cin을 해야 하는데, 이거 하면 그런 과정 없이 수행하기 때문에 빨라진다.
	cout.tie(NULL); //근데 평소라면 입력하세요 하고 입력하는게 자연스러우니까 이거 쓰는게 마냥 좋지는 않지만, 알고리즘은 오케이임
	priority_queue<int> QL;
	priority_queue<int, vector<int>, greater<int>> QR;
	int n, c;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> c;
		if (QR.size() == QL.size()) {
			QL.push(c);
		}
		else {
			QR.push(c);
		}
		if (!QR.empty() && !QL.empty() && QL.top() > QR.top()) {
			int t1 = QL.top(), t2 = QR.top();
			QL.pop(); QR.pop();
			QL.push(t2); QR.push(t1);
		}
		cout << QL.top() << '\n';
	}

	return 0;
}
