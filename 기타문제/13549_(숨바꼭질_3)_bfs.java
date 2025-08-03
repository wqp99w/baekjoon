import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static int []dx = {-1,1,2};
    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        System.out.println(bfs(N,K));
    }
    public static Integer bfs(int N, int K) {
        int map[] = new int[100001];
        boolean visited[] = new boolean[100001];
        visited[N] = true;
        map[N] = 0;
        Queue<Integer> que = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        que.add(N);
        while (!que.isEmpty()) {
            int now = que.peek();
            if (now == K) return map[now];
            que.poll();
            for(int i=0; i<3; i++){
                if(i==2){
                    if ((now * 2 <= 100000)) {
                        if (!visited[now * 2]) {
                            visited[now * 2] = true;
                            que2.add(now * 2);
                            map[now * 2] = map[now];
                        } else { //이미 방문 했는데 비용이 더 작을 경우
                            if (map[now * 2] > map[now]) {
                                map[now * 2] = map[now];
                                que2.add(now * 2);
                            }
                        }
                    }
                }
                else {
                    int nx = now + dx[i];
                    if(0<=nx && nx<=100000){
                        if(!visited[nx]) {
                            visited[nx] = true;
                            que2.add(nx);
                            map[nx] = map[now] + 1;
                        }
                        else{
                            if(map[nx]>map[now]+1){
                                map[nx] = map[now]+1;
                                que2.add(nx);
                            }
                        }
                    }
                }
            }
            while (!que2.isEmpty()) {
                 que.add(que2.peek());
                que2.poll();
            }
        }
        return 1;
    }
}
