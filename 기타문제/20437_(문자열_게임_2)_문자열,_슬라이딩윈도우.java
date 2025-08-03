import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            String input = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Check(input, n);
        }
    }

    public static void Check(String input, int n){
        LinkedList<Integer> []alpha = new LinkedList[26];
        for(int i=0;i<26;i++){
            alpha[i] = new LinkedList<>();
        }
        int min = 10001;
        int max = -1;
        for(int i=0;i<input.length();i++){
            alpha[input.charAt(i)-'a'].add(i);
            if(alpha[input.charAt(i)-'a'].size()==n){ //해당 문자가 n개 나왔다면
                int len = i-alpha[input.charAt(i)-'a'].getFirst()+1;
                if(min>len) min=len;
                if(max<len) max=len;
                alpha[input.charAt(i)-'a'].removeFirst();
            }
        }
        if(min == 10001 || max ==-1) System.out.println(-1);
        else{
            System.out.println(min + " "+ max);
        }
    }
}
