import java.io.*;
import java.util.*;

public class Main{
    // tails 어디로 들어가야 하는지 확인 (과정 1)
    public static int findPos(int[] tails, int len, int temp){
        int left = 0, right = len; // 이진 탐색용 좌우
        while(left < right) {
            int mid = (left + right) / 2;
            if (tails[mid] >= temp) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // LIS 구현 (과정 2)
    public static int LIS(int[] input, int[] tails, int[] tailsidx, int[] prev, int n){
        int len = 0; // 초기 LIS의 길이는 0
        for (int i = 0; i < n; i++) {
            int temp = input[i];
            int x = i;
            int pos = findPos(tails, len, temp); // 들어갈 자리 찾기
            if(pos == len){ // 들어갈 자리가 현재 LIS의 길이라면 하나 더 증가한다는 의미 (과정 3)
                tails[pos] = temp;
                len++;
                tailsidx[pos] = x;
                if(pos > 0)
                    prev[x] = tailsidx[pos-1];

            }
            else if(tails[pos] > temp) {// 더 작은 값으로 LIS 만들 수 있으면 그게 더 좋음
                tails[pos] = temp;
                tailsidx[pos] = x;
                if(pos > 0)
                    prev[x] = tailsidx[pos-1];
            }
        }
        return len;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // tails 배열은 각 인덱스 + 1의 LIS의 수열의 마지막 값을 저장하는 수열임
        // -> idx가 0이면 길이가 1인 수열의 마지막 값을 저장 50, 10 이런식이면 50이었다가 10이었다가 할거임
        // -> 작은 값으로 유지하는 이유는 다음에 올 수 있는 경우가 더 많아지기 때문, 50보다 10보다 큰 경우가 더 많을테니까
        // 50, 10, 20 이러면 1인 경우는 10이고, 2인 경우는 20일거임 -> 10, 20 이런식으로 완성될테니까
        // 50, 10, 20, 15인 경우는 10, 15의 경우밖에 안되는거고, 1은 10, 2는 15가 된다
        // 그러면 새로 들어오는 수는 tails 배열에서 길이가 몇인 lis의 끝 값으로 갈 수 있는지 확인해야 한다. (과정 1)
        // 어디로 가야 하는지 확인하면 그 값을 갱신 (과정 2)
        // 만약 tails 배열에서 제일 끝 값으로 들어갈 수 있다면 (과정3) tails의 길이를 늘리기
        // -> 그냥 중간에 들어가야 한다면(과정3-1) 해당 자리의 값과 비교해서 작은 값 들어가기

        // 추가 -> 위의 방식으로 하면 LIS의 길이는 알 수 있지만 숫자를 출력할 수는 없음
        // -> 각 LIS의 끝 값을 tails에 저장했으면, 그 값이 실제 input의 어디에 있는 값인지를 알 수 없기에 저장하는 tailsidx 배열에 끝값의 idx 저장
        // -> 만약 input의 원소 중 LIS에 참여한 원소값이 있다면, prev 배열에 그 원소의 이전 값의 idx를 저장한다
        // -> 부모 찾는 것처럼 길이가 3이었다면 끝값의 idx는 tailsidx에서 찾고, 그 idx부터 이전 값의 idx를 추적해서 LIS를 완성시키면 된다.

        // 구현
        int n = Integer.parseInt(br.readLine());
        // 필요한 배열 선언
        int[] input = new int[n]; // 입력용 배열
        int[] tails = new int[n]; // 각 길이의 lis별 끝 값 저장용 배열
        int[] tailsidx = new int[n];
        int[] prev = new int[n]; // 현재 idx의 끝값의 바로 앞 숫자의 idx 저장 -> 없으면 -1
        Arrays.fill(prev, -1);

        String[] st = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            input[i] = Integer.parseInt(st[i]);
        }
        int length = LIS(input, tails, tailsidx, prev, n);
        int[] output = new int[length];
        System.out.println(length);
        int start = tailsidx[length-1];
        for(int i = length - 1 ; i >= 0 ; i--){
            output[i] = input[start];
            start = prev[start];
        }
        for(int i = 0; i < length; i++)
            System.out.print(output[i] + " ");

    }
}