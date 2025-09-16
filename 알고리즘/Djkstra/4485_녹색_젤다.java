import java.io.*;
import java.util.*;

public class Main{
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};

    public static class Edge{
        int n, w;
        public Edge(int n, int w){
            this.n = n;
            this.w = w;
        }
    }
    // 그래프 생성 함수
    public static void makeGraph(int[][] map, List<Edge>[] graph, int N){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int node = i * N + j;
                graph[node] = new ArrayList<>();
                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    // 노드의 간선 추가
                    graph[node].add(new Edge(nx * N + ny, map[nx][ny]));
                }
            }
        }
    }
    // 다익스트라 함수 구현
    public static int Djkstra(int[][] map, List<Edge>[] graph, int N) {
        // 방문용 배열
        boolean[] visited = new boolean[N*N];
        // 시작점에서 각 노드의 거리
        int[] dist = new int[N*N];
        // 최댓값으로 일단 채우고, 연결되면 최소값으로 변경
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작 지점 값 문제 보면 포함하기 때문에 0이 아닌 가중치로 넣어주기
        dist[0] = map[0][0];
        // 우선 순위 큐 사용해서 효율성 증가, 오름차순 -> 작은값 먼저 보는게 좋으니까
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> {
            return a.w - b.w;
        });
        pq.offer(new Edge(0, dist[0]));
        while(!pq.isEmpty()){
            Edge temp = pq.poll();
            if(visited[temp.n]) continue;
            visited[temp.n] = true;
            if(temp.n == N * N -1) break;
            // 현재 노드의 간선 확인
            for(Edge e : graph[temp.n]){
                // 현재 간선에 연결된 노드로 이동하는 값이 시작점에서 바로 가는거랑 현재 노드 거쳐서 가는거랑 뭐가 더 싼지 비교
                int n_weight = dist[temp.n] + e.w;
                if(dist[e.n] > n_weight){
                    dist[e.n] = n_weight;
                    // 개선된 경우만 큐에 추가 -> 어차피 비용 작은 놈이 뽑히니까 굳이 큰 놈을 넣을 필요는 없음
                    pq.offer(new Edge(e.n,dist[e.n]));
                }
            }
        }
        return dist[N*N-1];
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            int[][] map = new int[N][N];
            // map 입력
            for(int i = 0; i < N; i++){
                String[] input = br.readLine().split(" ");
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }
            // 그래프 생성
            List<Edge>[] graph = new ArrayList[N*N];
            makeGraph(map, graph, N);
            int out = Djkstra(map, graph, N);
            System.out.println("Problem " + num++ + ": " + out);
        }
    }
}