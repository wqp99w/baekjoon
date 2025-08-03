import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Main {
    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int DP[] = new int[10001]; // DP 테이블 생성, 10000까지니까 10001개 생성

        DP[0] = 1; //0일 때 조합 가능 수 1개

        for (int i = 1; i <= 3; i++) { // 조합 가능한 수가 1,2,3이기 때문
            for (int j = 1; j <= 10000; j++) { //최대 10000까지의 경우의 수 저장하는 테이블
                if (j - i >= 0) { //i는 1,2,3 --> 현재 수에서 1로 시작하는 조합, 2로 시작하는 조합, 3으로 시작하는 조합 각각 구해서 더하는거
                    DP[j] += DP[j - i];
                }
            }
        }

        int t = Integer.parseInt(br.readLine());
        for (int i=1; i<=t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(DP[n]);
        }
    }
}
