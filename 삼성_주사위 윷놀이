#include <iostream>
#include <vector>

using namespace std;
vector<vector<int>> map = {
	{ 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38},//갈림길 안가는 경우
	{0,13,16,19}, //첫번째 갈림길
	{0,22,24}, //두번째 갈림길
	{0,28,27,26},//세번째 갈림길
	{25,30,35,40} }; //마지막 부분
int visited[5][22];
int Max = -1;

struct Pon {
	int index;
	int pass;
	int goal;
};

vector<int> root(10, 0);
vector<int> toto(10, 0);
vector<int> go(10, 0);
void dfs(vector<int> input, vector<Pon> &pon, int step,int sum) {
	if (step == 10) {
		Max = max(Max, sum);
		return;
	}
	for (int i = 0; i < 4; i++) {
		int tindex=0, tpass=0,tsum=0;
		Pon temp = pon[i];
		if (pon[i].goal == 1) continue;
		if (pon[i].pass == 4||(pon[i].pass!=4&&(pon[i].index + input[step]) >= map[pon[i].pass].size())) { //마지막 경로로 들어가는 경우
			if (pon[i].pass == 4) {
				tindex = pon[i].index+input[step];
				tpass = 4;
			}
			else {
				if (pon[i].pass == 0) {
					tpass = 4;
					tindex = (pon[i].index + input[step]) - map[pon[i].pass].size()+3;
				}
				else {
					tpass = 4;
					tindex = (pon[i].index + input[step]) - map[pon[i].pass].size();
				}
			}
			if (tindex < map[4].size()) {
				tsum = map[tpass][tindex];
			}
		}
		else if (pon[i].pass == 0) {
			tindex = pon[i].index + input[step];
			tpass = pon[i].pass;
			tsum = map[tpass][tindex];
			if (tindex<=15&&(tindex % 5 == 0)) { //갈림길을 만나는 경우라면
				tsum = map[pon[i].pass][tindex];
				tpass = tindex / 5; //해당 갈림길로 이동하고
				tindex = 0; //그 지점으로
			}
		}
		else {
			tindex = pon[i].index + input[step];
			tpass = pon[i].pass;
			tsum = map[tpass][tindex];
		}
		if ((!(tpass == 4 && tindex >= map[4].size())) && visited[tpass][tindex] != 0) continue; // 골에 안들어가고 이미 방문되었으면 다음거로
		if (tpass == 4 && tindex >= map[4].size())//골에 들어가는 경우
		{
			visited[pon[i].pass][pon[i].index] = 0;
			pon[i].goal = 1;
			root[step] = i + 1;
			toto[step] = tsum;
			go[step] = 1;
			dfs(input, pon, step + 1, sum);
			pon[i] = temp;
			visited[pon[i].pass][pon[i].index] = 1;
		}
		else {
			visited[pon[i].pass][pon[i].index] = 0;
			visited[tpass][tindex] = 1;
			pon[i].index = tindex; pon[i].pass = tpass;
			root[step] = i + 1;
			toto[step] = tsum;
			go[step] = 0;
			dfs(input, pon, step + 1, sum + tsum);
			visited[tpass][tindex] = 0;
			pon[i] = temp;
			visited[pon[i].pass][pon[i].index] = 1;
		}
		
	}
}

int main() {
	vector<int> input(10);
	for (int i = 0; i < 10; i++)
		cin >> input[i];
	vector<Pon> pon = { {0,0,0},{0,0,0} ,{0,0,0} ,{0,0,0} };
	dfs(input, pon, 0, 0);
	cout << Max << endl;
	return 0;
}
