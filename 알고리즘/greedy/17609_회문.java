import java.io.*;
import java.util.*;

public class Main{
    public static int ispal(String input, int check){
        int start = 0, end = input.length()-1;
        // 맨앞 맨뒤 비교하면서 같은지 확인, 맨 중앙은 비교할 필요 없으므로 =은 넣지 않음
        while(start<end){
            if(input.charAt(start) == input.charAt(end)){
                start++;
                end--;
            }
            // 하나 빼면 회문이 되는지 확인
            else if(check==0){
                // abxaba 이런거 같이 짝수 길이면서 중앙만 다른 경우는 start랑 end가 3 4 이런 경우인데, 이러면 재귀 들어가면 무조건 1 나옴
                int left = ispal(input.substring(start+1, end+1), 1);
                int right = ispal(input.substring(start, end), 1);
                if(left == 1 || right == 1) return 1;
                else return 2;
            }
            else
                return 2;
        }
        if(check == 0) return 0;
        return 1;
    }
    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            String input = br.readLine();
            System.out.println(ispal(input,0));
        }
    }
}