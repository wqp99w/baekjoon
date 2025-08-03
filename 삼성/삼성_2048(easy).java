package coding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main {

    public static int dfs(final int [][] input, final int [][]check, int con, int n)
    {
        if(con>=5)
            return 0;
        int Max=0;
        for(int i=0;i<4;i++)
        {
            int [][]temp= new int[n+1][n+1];
            int [][]che=new int[n+1][n+1];
            for (int j = 0; j <= n; j++) {
                temp[j] = Arrays.copyOfRange(input[j], 0, n+1);
                che[j]=Arrays.copyOfRange(check[j],0,n+1);
            }
            if(i==0)
            {
                for(int j=1;j<=n;j++)
                {
                    int point = 1;
                    Max=Math.max(Max,temp[j][1]);
                    for(int k=1;k<=n;k++)
                    {
                        if(temp[j][k]!=0&&k!=1)
                        {
                            if(temp[j][k]==temp[j][point]&&che[j][point]==0)
                            {
                                temp[j][point] = temp[j][point] * 2;
                                Max= Math.max(Max,temp[j][point]);
                                che[j][point]=1;
                                temp[j][k]=0;
                            }
                            else
                            {
                                Max=Math.max(Max,temp[j][k]);
                                if(temp[j][point]==0)
                                    temp[j][point]=temp[j][k];
                                else
                                    temp[j][++point]=temp[j][k];
                                if(point!=k)
                                    temp[j][k]=0;
                            }
                        }
                    }
                }
            }
            if(i==1)
            {
                for(int j=1;j<=n;j++)
                {
                    int point = n;
                    Max=Math.max(Max,temp[j][n]);
                    for(int k=n;k>=1;k--)
                    {
                        if(temp[j][k]!=0&&k!=n)
                        {
                            if(temp[j][k]==temp[j][point]&&che[j][point]==0)
                            {
                                temp[j][point] = temp[j][point] * 2;
                                Max= Math.max(Max,temp[j][point]);
                                che[j][point]=1;
                                temp[j][k]=0;
                            }
                            else
                            {
                                Max=Math.max(Max,temp[j][k]);
                                if(temp[j][point]==0)
                                    temp[j][point]=temp[j][k];
                                else
                                    temp[j][--point]=temp[j][k];
                                if(point!=k)
                                    temp[j][k]=0;
                            }
                        }
                    }
                }
            }
            if(i==2)
            {
                for(int j=1;j<=n;j++)
                {
                    int point = 1;
                    Max=Math.max(Max,temp[1][j]);
                    for(int k=1;k<=n;k++)
                    {
                        if(temp[k][j]!=0&&k!=1)
                        {
                            if(temp[k][j]==temp[point][j]&&che[point][j]==0)
                            {
                                temp[point][j]=temp[point][j]*2;
                                Max= Math.max(Max,temp[point][j]);
                                che[point][j]=1;
                                temp[k][j]=0;
                            }
                            else
                            {
                                Max=Math.max(Max,temp[k][j]);
                                if(temp[point][j]==0)
                                    temp[point][j]=temp[k][j];
                                else
                                    temp[++point][j]=temp[k][j];
                                if(point!=k)
                                    temp[k][j]=0;
                            }
                        }
                    }
                }
            }
            if(i==3)
            {
                for(int j=1;j<=n;j++)
                {
                    int point = n;
                    Max=Math.max(Max,temp[n][j]);
                    for(int k=n;k>=1;k--)
                    {
                        if(temp[k][j]!=0&&k!=n)
                        {
                            if(temp[k][j]==temp[point][j]&&che[point][j]==0)
                            {
                                temp[point][j]=temp[point][j]*2;
                                Max= Math.max(Max,temp[point][j]);
                                che[point][j]=1;
                                temp[k][j]=0;
                            }
                            else
                            {
                                Max=Math.max(Max,temp[k][j]);
                                if(temp[point][j]==0)
                                    temp[point][j]=temp[k][j];
                                else
                                    temp[--point][j]=temp[k][j];
                                if(point!=k)
                                    temp[k][j]=0;
                            }
                        }
                    }
                }
            }
            Max=Math.max(Max,dfs(temp,check,con+1,n));
        }
        return Max;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int [][]input = new int [n+1][n+1];
        int [][]check=new int [n+1][n+1];
        StringTokenizer st;
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                input[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int output=dfs(input,check,0,n);
        System.out.println(output);
    }
}
