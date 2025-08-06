import java.io.*;
import java.util.*;

public class Main{
    public static class Pos{
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // 거리 계산
    public static int menhaton(Pos x, Pos y){
        return Math.abs(x.x-y.x) + Math.abs(x.y - y.y);
    }

    // bfs -> 현재 위치에서 거리 1000이하로 갈 수 있는 곳 확인
    // 이런식으로 계속 확인하면 목적지까지 갈 수 있는지 확인 가능
    public static boolean bfs(Pos[] poss){
        boolean[] visited = new boolean[poss.length];
        visited[0] = true;
        Queue<Pos> que = new ArrayDeque<>();
        que.offer(poss[0]);
        while(!que.isEmpty()){
            Pos temp = que.poll();
            for(int i = 0; i < poss.length; i++){
                if(!visited[i] && menhaton(temp, poss[i]) <= 1000){
                    que.offer(poss[i]);
                    visited[i] = true;
                }
            }
        }
        // 마지막 값이 목적지니까 방문 했는지 확인
        return visited[visited.length-1];
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        Pos[] poss;
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            poss = new Pos[n+2];
            for(int j = 0; j < n+2; j++){
                String[] input = br.readLine().split(" ");
                poss[j] = new Pos(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            }
            if(bfs(poss)) System.out.println("happy");
            else System.out.println("sad");
        }

    }

}