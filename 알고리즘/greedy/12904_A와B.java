import java.io.*;
import java.util.*;

public class Main{
    public static boolean check(String s, String t){
        Deque<Character> deque = new LinkedList<>();
        for(int i = 0; i < t.length(); i++){
            deque.addLast(t.charAt(i));
        }
        // state가 0이면 정방향 -> 01234 이런식으로 접근해서 맨뒤를 없애면 4가 사라지는 것
        // 1이면 역방향 -> 43210 이런식으로 접근해서 맨 뒤를 없애면 0번째 자리인 4가 사라짐
        int state = 0;

        // t와 s의 길이가 같아져서 둘이 같은지 비교
        while(deque.size()>s.length()){
            // 정방향이면 맨 뒤를 제거
            char temp;
            if(state == 0) temp = deque.removeLast();
            else temp = deque.removeFirst();
            // B를 제거한거면 뒤집어야 하니까 상태를 변환
            if (temp == 'B') state = (state == 1 ? 0 : 1);
        }
        // 정방향이면 앞에서부터 비교
        if(state == 0){
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != deque.removeFirst()) return false;
            }
        }
        // 역방향이면 뒤에서부터 비교
        else{
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != deque.removeLast()) return false;
            }
        }
        return true;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        if(check(s,t)) System.out.println(1);
        else System.out.println(0);
    }
}