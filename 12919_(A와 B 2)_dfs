import java.io.*;
public class Main {

    public static String S;
    public static boolean check = false;
    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        String T = br.readLine();
        dfs(T);
        System.out.println(check ? 1 : 0);
    }

    public static void dfs(String T){
        if(S.length()==T.length()){
            if(S.equals(T)) check=true;
            return;
        }
        if(T.charAt(0)=='B'){
            StringBuilder sb = new StringBuilder(T.substring(1,T.length()));
            dfs(sb.reverse().toString());
        }
        if(T.charAt(T.length()-1)=='A') dfs(T.substring(0,T.length()-1));
    }
}
