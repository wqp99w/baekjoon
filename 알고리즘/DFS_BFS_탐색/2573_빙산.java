import java.io.*;
import java.util.*;

public class Main{
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};
    public static class Pos{
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // 빙산이 분리되었는지 확인
    public static boolean isSeparated(int[][] maps, Queue<Pos> que) {
        int[][] visited = new int[maps.length][maps[0].length];
        int size = que.size();
        int num = 0;
        for(int i = 0; i < size; i++){
            Pos temp = que.poll();
            if(visited[temp.x][temp.y] == 0){
                num++;
                Queue<Pos> que2 = new ArrayDeque<>();
                que2.offer(temp);
                // 아직 방문 안한 빙산에서 구역 확인
                while(!que2.isEmpty()){
                    Pos pos = que2.poll();
                    for(int j = 0; j < 4; j++){
                        int nx = pos.x + dx[j];
                        int ny = pos.y + dy[j];
                        if(nx>=0 && nx < maps.length && ny >= 0 && ny < maps[0].length &&
                                visited[nx][ny] == 0 && maps[nx][ny] != 0){
                            visited[nx][ny] = num;
                            que2.offer(new Pos(nx,ny));
                        }
                    }
                }
            }
            que.offer(temp);
        }
        return num > 1;

    }

    // 빙산 없어지는 로직 구현
    public static int bfs(int[][] maps, Queue<Pos> que){
        int day = 0;
        while(!que.isEmpty()){
            int size = que.size();
            // 2개 이상으로 분리 되었는지 확인
            if( isSeparated(maps, que) ) return day;
            // 기존 큐 사이즈 만큼만 시행 -> 하루 기준으로 큐 삭제
            boolean[][] visited = new boolean[maps.length][maps[0].length];
            for(int i = 0; i < size; i++){
                Pos temp = que.poll();
                int count = 0;
                // 상하좌우 탐색
                for(int j = 0; j < 4; j++){
                    int nx = temp.x + dx[j];
                    int ny = temp.y + dy[j];
                    // 상하좌우에 바다(0)이 있으면 줄어드는 크기 1씩 증가, 같은 날에 바다가 된 구역은 제외 -> 한방에 녹아야 하기 때문에
                    if(nx>=0 && nx < maps.length && ny >= 0 && ny < maps[0].length && !visited[nx][ny] && maps[nx][ny] == 0)
                        count++;
                }
                // 해당 빙산 얼마나 녹아야 하는지 계산 -> 현재 빙산 - 인접한 바다 수
                // 빙산이 다 안녹는 경우는 다음 날에 동일하게 계산해야하므로 큐에 추가
                if(maps[temp.x][temp.y] - count > 0){
                    maps[temp.x][temp.y] = maps[temp.x][temp.y] - count;
                    que.offer(temp);
                }
                // 빙산이 다 녹았으면 큐에 추가 안함
                else {
                    maps[temp.x][temp.y] = 0;
                    visited[temp.x][temp.y] = true;
                }
            }
            day++;
        }
        return 0;
    }


    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int[][] maps = new int[n][m];
        Queue<Pos> que = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                maps[i][j] = Integer.parseInt(input[j]);
                if(maps[i][j] > 0) que.offer(new Pos(i,j));
            }
        }
        System.out.println(bfs(maps, que));
    }
}