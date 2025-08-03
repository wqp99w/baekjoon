#include <iostream>
#include <vector>

using namespace std;
const int MOD = 1000000007;


long long PP(long long a, long long n) {
	if (n == 0) return 1;
	if (n == 1) return a;
	if (n % 2 == 0) {
		long long temp = PP(a, n / 2);
		return (temp * temp) % MOD;
	}
	else {
		long long temp = PP(a, (n-1) / 2);
		return (a * (temp * temp%MOD)) % MOD;
	}
}

long long dp(int n,int k) {
	vector<long long> save(n + 1, 1);
	for (long long i = 1; i <= n; i++) {
		save[i] = (save[i - 1] * i) % MOD;
	}
	//a^(p-1) mod p=1, p가 소수일 때
	//a^(p-1) mod p * a^-1 mod p= a^-1 mod p ==> a^(p-2) mod p = a^-1 mod p /////p-1승 곱하게 -1승이면 p-2승이니까
	//c mod p/(a mod p * b mod p) mod p// 여기서 mod 연산은 나눗셈에서는 분배법칙이 안되기 때문에 분모의 -1을 구해서 곱해줘야 함
	long long temp = PP((save[k] * save[n - k])% MOD, MOD-2) % MOD;
	//a=save[k], b=save[n-k] ==> a*b=q라고 하면 a mod p * b mod p) mod p 이거는 (q mod p) mod p가 된다.
	return (save[n]*temp)% MOD;
		
}

int main() {
	int n=5, k=2;
	cin >> n >> k;
	cout << dp(n, k);
	return 0;
}
