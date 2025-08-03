import java.io.*;
import java.util.*;
public class Main{

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] input = s.split(" ");
        int[] in = new int[n];
        int[] gap = new int[n-1];
        for(int i=0;i<n;i++){
            in[i] = Integer.parseInt(input[i]);
        }
        // 좌표 오름차순으로 정렬 -> 순서대로 좌표 확인해서 간격 확인 용
        Arrays.sort(in);
        // 슬라이딩하면서 한칸씩 간격 저장
        for(int i=0;i<n-1;i++){
            gap[i] = in[i+1] - in[i];
        }
        // 간격들 오름차순으로 정렬 -> 이러면 뒤로 갈 수록 간격이 크다는 의미 -> 이 간격이 큰 구간을 제거하면 수신 가능 영역이 짧아진다
        // 하나도 제거 안한 상태 -> 1 - 9 면 8이 나오는데, 중간에 3에서 6으로 가는 구간을 지우면, 1-3, 6-9로 두 개의 구간으로 나누어지고 길이는 5가 된다
        // 입력 값 k가 집중국의 갯수니까 k가 2이라면 구간이 두개가 나와야 한다 -> k가 3이면 구간이 세개 -> 따라서 k-1 개의 간격을 제거하면 k개의 구간이 생성된다
        // gap의 마지막 값을 하나씩 제거하면 간격이 제일 큰 구간이 사라지는 것이기 때문에 오름차순으로 정렬하고 gap.length-(k-1)의 길이의 값을 더하면 전체 길이가 된다
        Arrays.sort(gap);
        int output = 0;
        for(int i=0; i < gap.length-(k-1);i++){
            output += gap[i];
        }
        System.out.println(output);
    }
}