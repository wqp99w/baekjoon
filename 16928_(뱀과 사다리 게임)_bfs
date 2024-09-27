import java.io.*;
import java.util.*;

public class Main {
    public static int [] visited = new int [101];
    public static int [] board = new int [101];

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);


        for (int i=0;i<N;i++){
            String[] ladderinput = br.readLine().split(" ");
            int x = Integer.parseInt(ladderinput[0]);
            int y = Integer.parseInt(ladderinput[1]);
            board[x]=y;
        }
        for (int i=0; i<M;i++){
            String[] snakeinput = br.readLine().split(" ");
            int x = Integer.parseInt(snakeinput[0]);
            int y = Integer.parseInt(snakeinput[1]);
            board[x]=y;
        }
        System.out.println(bfs());
    }

    public static int bfs(){

        Queue<Integer> que = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();

        que.add(1);
        int count = 1;
        while(true) {
            while (!que.isEmpty()) {
                int now = que.peek();
                que.poll();

                for (int i = 1; i <= 6; i++) {
                    int nx = now + i;
                    if (nx == 100) return count;
                    if (nx <= 100 && visited[nx] == 0) { //보드판 범위 내에 방문하지 않았을 경우
                        visited[nx] = 1;
                        if (board[nx] != 0) { //사다리나 뱀이 있는 경우
                            nx = board[nx];
                            visited[nx] = 1;
                        }
                        que2.add(nx);
                    }
                }
            }

            while (!que2.isEmpty()){ //한 레벨 끝나면 다음 레벨로 진행
                que.add(que2.peek());
                que2.poll();
            }
            count++;
        }

    }
}
