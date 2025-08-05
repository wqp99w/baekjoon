import java.io.*;
import java.util.*;

public class Main{
    public static int calc(String s, String p){
        int output = 0;
        for(int i = 0; i < p.length(); i++){
            boolean check = false;
            int max = 0;
            for(int j = 1; j < s.length(); j++){
                if(i + j >=p.length()) break;
                if(s.contains(p.substring(i,i+j+1))){
                    check = true;
                    max = j;
                }
            }
            if(check) i += max;
            output++;
        }
        return output;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();
        System.out.println(calc(s,p));
    }
}