import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    public static int []weight= new int[101];
    public static int []values=new int[101];
    public static int Max = -1;
    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        for(int i=0;i<n;i++) {
            input = br.readLine().split(" ");
            weight[i+1] = Integer.parseInt(input[0]);
            values[i+1] = Integer.parseInt(input[1]);
        }
        int [][]dp = new int[n+1][k+1];
        
        for(int i=0;i<=k;i++){
            for(int j=0;j<=n;j++){
                if(i==0||j==0){
                    dp[j][i]=0;
                    continue;
                }
                else{
                    if(weight[j]<=i){
                        dp[j][i]=Math.max(dp[j-1][i],(values[j]+dp[j-1][i-weight[j]]));
                    }
                    else{
                        dp[j][i]=dp[j-1][i];
                    }
                }
            }
        }
        System.out.println(dp[n][k]);
    }

}
