import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    public static class pair{
        int first,second;
        pair(int x, int y){
            this.first=x;
            this.second=y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int [][]input=new int[n+1][n+1];
        int [][]visited=new int[n+1][n+1];
        char []direction=new char[10001];
        StringTokenizer st;
        int a=Integer.parseInt(br.readLine());
        for(int i=0;i<a;i++){
            st = new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            input[q][w]=2;
        }
        a=Integer.parseInt(br.readLine());
        for(int i=0;i<a;i++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            char in=st.nextToken().charAt(0);
            direction[q]=in;
        }
        int []dx={0,1,0,-1};
        int []dy = {1,0,-1,0};
        int x=1, y=1, dir=0, time=1;
        visited[x][y]=1;
        Queue<pair> snake=new LinkedList<>();
        snake.offer(new pair(x,y));
        while(true){
            int nx=dx[dir]+x;
            int ny=dy[dir]+y;
            if((nx>=1&&nx<=n&&ny>=1&&ny<=n)&&visited[nx][ny]!=1){
                if(input[nx][ny] != 2){
                    pair temp=snake.poll();
                    visited[temp.first][temp.second]=0;
                }
                input[nx][ny]=0;
                visited[nx][ny]=1;
                snake.offer(new pair(nx,ny));
                x=nx; y=ny;
            }
            else
                break;
            if(time<=10000&&(direction[time]=='D'||direction[time]=='L'))
                dir=direction[time]=='L'?(dir+4-1)%4:(dir+1)%4;
            time++;
        }
        System.out.println(time);
    }

}
