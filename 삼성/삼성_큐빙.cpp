#include <iostream>
#include <vector>
#include <string>
using namespace std;

vector<vector<vector<char>>> cube_orgin =
{   {{'w','w','w'},{'w','w','w'},{'w','w','w'}}, //위 0
	{{'y','y','y'},{'y','y','y'},{'y','y','y'}}, //아래 1
	{{'r','r','r'},{'r','r','r'},{'r','r','r'}}, //앞 2
	{{'o','o','o'},{'o','o','o'},{'o','o','o'}}, //뒤 3
	{{'g','g','g'},{'g','g','g'},{'g','g','g'}}, //왼 4
	{{'b','b','b'},{'b','b','b'},{'b','b','b'}} }; //오 5

vector<vector<vector<int>>> turn =
{   {{2,4,3,5,2},{2,5,3,4,2}},
	{{2,5,3,4,2},{2,4,3,5,2}},
	{{0,5,1,4,0},{0,4,1,5,0}},
	{{0,4,1,5,0},{0,5,1,4,0}},
	{{3,0,2,1,3},{3,1,2,0,3}},
	{{3,1,2,0,3},{3,0,2,1,3}} };

int Findindex(char in) {
	int index = 0;
	switch (in) {
	case('U'): {
		index = 0;
		break; }
	case('D'): {
		index = 1;
		break; }

	case('F'): {
		index = 2;
		break; }
	case('B'): {
		index = 3;
		break; }
	case('L'): {
		index = 4;
		break; }
	case('R'): {
		index = 5;
		break; }
	}
	return index;
}

void Cube_turn(vector<vector<vector<char>>>& cube, int index, int dir) {
	if (index == 0 || index == 1) {
		if (index == 0) {
			vector<char> temp = cube[turn[index][dir][0]][0];
			for (int i = 4; i > 1; i--) {
				cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][0];
				cube[turn[index][dir][i]][0][1] = cube[turn[index][dir][i - 1]][0][1];
				cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][2];
			}
			cube[turn[index][dir][1]][0][0] = temp[0];
			cube[turn[index][dir][1]][0][1] = temp[1];
			cube[turn[index][dir][1]][0][2] = temp[2];
		}
		else {
			vector<char> temp = cube[turn[index][dir][0]][2];
			for (int i = 4; i > 1; i--) {
				cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][0];
				cube[turn[index][dir][i]][2][1] = cube[turn[index][dir][i - 1]][2][1];
				cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][2];
			}
			cube[turn[index][dir][1]][2][0] = temp[0];
			cube[turn[index][dir][1]][2][1] = temp[1];
			cube[turn[index][dir][1]][2][2] = temp[2];
		}
		
	}
	else if (index == 2 || index == 3) {
		if (index == 2) { //F
			vector<char> temp = cube[turn[index][dir][0]][2]; //05140 0번 면 789저장
			if (dir == 0) { //시계
				for (int i = 4; i > 1; i--) {
					if (i  == 4) { //옆면에서 윗면으로 4번면 369 0번면 789로 이동 9-7 6-8 3-9
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][2];
						cube[turn[index][dir][i]][2][1] = cube[turn[index][dir][i - 1]][1][2];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][0][2];
					}
					else if (i == 2) { //옆면에서 아랫면으로, 5번면 147 1번면 123으로 이동
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][2][0];
						cube[turn[index][dir][i]][0][1] = cube[turn[index][dir][i - 1]][1][0];
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][0];
					}
					else { // 1번면 123 4번면 369로 이동 1-3 2-6 3-9. 4번면
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][0];
						cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][0][1];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][0][2];
					}
				}//5번면 1 4 7에 0번면 789이동 7-1 8-4 7-9
				cube[turn[index][dir][1]][0][0] = temp[0]; 
				cube[turn[index][dir][1]][1][0] = temp[1];
				cube[turn[index][dir][1]][2][0] = temp[2];
			}
			else { //반시계 04150
				for (int i = 4; i > 1; i--) {
					if (i == 4) { //옆면에서 윗면 5번 147이 0번 789로 이동 1-7 4-8 7-9
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][0][0];
						cube[turn[index][dir][i]][2][1] = cube[turn[index][dir][i - 1]][1][0];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][0];
					}
					else if (i == 2) { //옆면에서 아랫면 4번 369가 1번 123으로 이동 3-1 6-2 9-3
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][2];
						cube[turn[index][dir][i]][0][1] = cube[turn[index][dir][i - 1]][1][2];
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][2][2];
					}
					else {// 1번 123이 5번 147로 이동 1-7 2-4 3-1
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][2];
						cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][0][1];
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][0][0];
					}
				}//0번 789가 4번 369로 이동 7-9 8-6 9-3
				cube[turn[index][dir][1]][0][2] = temp[2];
				cube[turn[index][dir][1]][1][2] = temp[1];
				cube[turn[index][dir][1]][2][2] = temp[0];
			}
		}
		else {
			vector<char> temp = cube[turn[index][dir][0]][0];
			if (dir == 0) {
				for (int i = 4; i > 1; i--) {
					if (i  == 4) {//옆면에서 윗면
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][2];
						cube[turn[index][dir][i]][0][1] = cube[turn[index][dir][i - 1]][1][2];
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][2][2];
					}
					else if (i == 2) {//옆면에서 아랫면
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][0][0];
						cube[turn[index][dir][i]][2][1] = cube[turn[index][dir][i - 1]][1][0];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][0];
					}
					else {
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][2][2];
						cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][2][1];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][0];
					}
				}
				cube[turn[index][dir][1]][0][0] = temp[2];
				cube[turn[index][dir][1]][1][0] = temp[1];
				cube[turn[index][dir][1]][2][0] = temp[0];
			}
			else {
				for (int i = 4; i > 1; i--) {
					if (i == 4) { //옆면에서 윗면
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][2][0];
						cube[turn[index][dir][i]][0][1] = cube[turn[index][dir][i - 1]][1][0];
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][0];
					}
					else if (i == 2) { //옆면에서 아랫면
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][2];
						cube[turn[index][dir][i]][2][1] = cube[turn[index][dir][i - 1]][1][2];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][0][2];
					}
					else { 
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][2][0];
						cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][2][1];
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][2];
					}
				}
				cube[turn[index][dir][1]][0][2] = temp[0];
				cube[turn[index][dir][1]][1][2] = temp[1];
				cube[turn[index][dir][1]][2][2] = temp[2];
			}
		}

	}
	else {
		if (index == 4) {
			vector<char> temp;
			if (dir == 0) {
				temp.push_back(cube[turn[index][dir][0]][2][2]);
				temp.push_back(cube[turn[index][dir][0]][1][2]);
				temp.push_back(cube[turn[index][dir][0]][0][2]);
				for (int i = 4; i > 1; i--) {
					if (i % 2 == 0) {
						if (turn[index][dir][i] == 3) { //3번 면일때
							cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][2][0];
							cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][1][0];
							cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][0][0];
						}
						else {
							cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][0];
							cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][1][0];
							cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][0];
						}
					}
					else {
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][0];
						cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][1][0];
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][0];
					}
				}
				cube[turn[index][dir][1]][0][0] = temp[0];
				cube[turn[index][dir][1]][1][0] = temp[1];
				cube[turn[index][dir][1]][2][0] = temp[2];
			}
			else {
				temp.push_back(cube[turn[index][dir][0]][2][2]);
				temp.push_back(cube[turn[index][dir][0]][1][2]);
				temp.push_back(cube[turn[index][dir][0]][0][2]);
				for (int i = 4; i > 1; i--) {
					if (i % 2 != 0) {
						cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][0];
						cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][1][0];
						cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][0];
					}
					else {
						if (turn[index][dir][i] == 3) {
							cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][2][0];
							cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][1][0];
							cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][0][0];
						}
						else {
							cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][0][0];
							cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][1][0];
							cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][2][0];
						}
					}
				}
				cube[turn[index][dir][1]][0][0] = temp[0];
				cube[turn[index][dir][1]][1][0] = temp[1];
				cube[turn[index][dir][1]][2][0] = temp[2];
			}
		}
		else {
			vector<char> temp;
			if (dir == 0) {
				temp.push_back(cube[turn[index][dir][0]][2][0]);
				temp.push_back(cube[turn[index][dir][0]][1][0]);
				temp.push_back(cube[turn[index][dir][0]][0][0]);
				for (int i = 4; i > 1; i--) {
					if (i % 2 != 0) {
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][2];
						cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][1][2];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][2];
					}
					else {
						if (turn[index][dir][i] == 3) {
							cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][2][2];
							cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][1][2];
							cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][0][2];
						}
						else {
							cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][2];
							cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][1][2];
							cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][2];
						}
					}
				}
				cube[turn[index][dir][1]][0][2] = temp[0];
				cube[turn[index][dir][1]][1][2] = temp[1];
				cube[turn[index][dir][1]][2][2] = temp[2];
			}
			else {
				temp.push_back(cube[turn[index][dir][0]][2][0]);
				temp.push_back(cube[turn[index][dir][0]][1][0]);
				temp.push_back(cube[turn[index][dir][0]][0][0]);
				for (int i = 4; i > 1; i--) {
					if (i % 2 == 0) {
						if (turn[index][dir][i] == 3) {
							cube[turn[index][dir][i]][0][0] = cube[turn[index][dir][i - 1]][2][2];
							cube[turn[index][dir][i]][1][0] = cube[turn[index][dir][i - 1]][1][2];
							cube[turn[index][dir][i]][2][0] = cube[turn[index][dir][i - 1]][0][2];
						}
						else {
							cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][2];
							cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][1][2];
							cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][2];
						}
					}
					else {
						cube[turn[index][dir][i]][0][2] = cube[turn[index][dir][i - 1]][0][2];
						cube[turn[index][dir][i]][1][2] = cube[turn[index][dir][i - 1]][1][2];
						cube[turn[index][dir][i]][2][2] = cube[turn[index][dir][i - 1]][2][2];
					}
				}
				cube[turn[index][dir][1]][0][2] = temp[0];
				cube[turn[index][dir][1]][1][2] = temp[1];
				cube[turn[index][dir][1]][2][2] = temp[2];
			}
		}
	}
}

void Cube_curve(vector<vector<vector<char>>>& cube, int index, char dir) {
	if (dir == '+') {
		vector<char> tp(3, 0);
		vector<vector<char>> temp(3, tp);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) temp[j][2] = cube[index][i][j];
				else if (i == 1) temp[j][1] = cube[index][i][j];
				else temp[j][0] = cube[index][i][j];
			}
		}
		cube[index] = temp;
		Cube_turn(cube, index, 0);
	}
	else {
		vector<char> tp(3, 0);
		vector<vector<char>> temp(3, tp);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) temp[2-j][0] = cube[index][i][j];
				else if (i == 1) temp[2-j][1] = cube[index][i][j];
				else temp[2-j][2] = cube[index][i][j];
			}
		}
		cube[index] = temp;
		Cube_turn(cube, index, 1);
	}
	
}

int main() {
	int t, n,p=1;
	cin >> t;
	while (t--) {
		cin >> n;
		string input;
		vector<vector<vector<char>>> cube = cube_orgin;
		while (n--) {
			cin >> input;
			Cube_curve(cube, Findindex(input[0]), input[1]);
			/*cout << p++ << "번째 큐브" << endl << endl;
			for (int t = 0; t < 6; t++) {
				cout << t<<"번째 면" << endl;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						cout << cube[t][i][j];
					}
					cout << endl;
				}
			}*/
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cout<<cube[0][i][j];
			}
			cout << endl;
		}
	}
}
