import java.io.*;
import java.util.*;

public class Main{
    public static int[] dx = {0,0,-1,1,0,0};
    public static int[] dy = {0,0,0,0,-1,1};
    public static int[] dz = {-1,1,0,0,0,0};
    public static class Pos{
        int x,y,z;
        Pos(int z, int x, int y){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    // bfs 종료 후, 안익은 토마토(0)가 있으면, 실패한거임
    public static boolean check(int[][][] maps){
        for(int k = 0; k < maps.length; k++){
            for(int i = 0; i < maps[0].length ; i++){
                for(int j = 0; j < maps[0][0].length; j++){
                    if(maps[k][i][j] == 0) return false;
                }
            }
        }
        return true;
    }

    public static int bfs(int[][][] maps, Queue<Pos> que){
        int day = 0;
        while(!que.isEmpty()){
            int size = que.size();
            boolean change = false;
            // 날 별로 큐 삭제
            for(int j = 0; j < size; j++){
                Pos pos = que.poll();
                for(int i = 0; i < 6; i++){
                    int nx = pos.x + dx[i];
                    int ny = pos.y + dy[i];
                    int nz = pos.z + dz[i];
                    if(nz>=0 && nz<maps.length && nx>=0 && nx<maps[0].length &&
                            ny>=0 && ny<maps[0][0].length && maps[nz][nx][ny] == 0){
                        maps[nz][nx][ny] = 1;
                        que.offer(new Pos(nz,nx,ny));
                        change = true;
                    }
                }
            }
            // 더 이상 익은 토마토가 안생성될 때, 모두 익었으면 걸린 날, 안익은거 있으면 안됨
            if(!change && check(maps)) return day;
            day++;
        }
        if(!check(maps)) return -1;
        return day;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int m = Integer.parseInt(size[0]);
        int n = Integer.parseInt(size[1]);
        int h = Integer.parseInt(size[2]);

        // 입력 받고, bfs니까 큐 사용
        int[][][] maps = new int[h][n][m];
        Queue<Pos> que = new ArrayDeque<>();
        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                String[] input = br.readLine().split(" ");
                for(int k = 0; k < m; k++){
                    maps[i][j][k] = Integer.parseInt(input[k]);
                    if(maps[i][j][k] == 1) que.offer(new Pos(i,j,k));
                }
            }
        }
        if(que.isEmpty()) System.out.println(-1);
        else System.out.println(bfs(maps, que));
    }
}