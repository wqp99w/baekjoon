import java.util.*;
import java.io.*;

public class Main{
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};

    public static int dfs (char[][] map, int[] visited, int x, int y, int con){
        int out = con;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || visited[map[nx][ny] - 'A'] == 1) continue;
            // 방문한 적 없는 알파벳이면 이동
            visited[map[nx][ny] - 'A'] = 1;
            out = Math.max(out,dfs(map, visited, nx, ny, con + 1));
            // 다음 반복에서는 여기 방문 안한것 처럼 되어어 하기 때문
            visited[map[nx][ny] - 'A'] = 0;
        }
        return out;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int r = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        char[][] map = new char[r][c];
        for(int i = 0; i < r; i++){
            String temp = br.readLine();
            for ( int j = 0; j < c; j++){
                map[i][j] = temp.charAt(j);
            }
        }
        // 상하좌우 탐색 -> 움직일 다음 칸이 방문한 적 없는 알파벳인 경우만 이동
        // dfs 기반으로 시작하고, visited 배열은 26칸으로 알파벳이 나온 경우 1 표시를 해서 확인 [(다음 칸 알파벳)-(A)] 이런식으로 0인 경우만 이동
        // dfs 구현 -> 포문 한번에 방향 4번 이동 -> 한칸 이동 후 dfs -> dfs 종료 후엔 이번에 움직인 칸 안간걸로 하고 다음 칸 진행

        int[] visited = new int[26];
        // 시작 부분도 방문 처리
        visited[map[0][0] - 'A'] = 1;
        int out = dfs(map, visited, 0, 0, 1);
        System.out.println(out);

    }
}