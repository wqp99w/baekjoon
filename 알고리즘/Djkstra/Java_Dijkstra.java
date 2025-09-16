import java.io.*;
import java.util.*;
public class Main{
    public static class Edge{
        int n, w;
        public Edge(int n, int w){
            this.n = n;
            this.w = w;
        }
    }
    public static void Djkstra(List<Edge>[] graph, int[] dist, int n){
        // 방문 확인용
        boolean[] visited = new boolean[n+1];
        // 우선순위큐 사용해서 효율 증가
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> {
            return a.w - b.w;
        });
        // 시작 지점 큐에 삽입
        pq.offer(new Edge(1, dist[1]));
        // 반복 시작 -> 마지막 도착하면 종료
        while(!pq.isEmpty()){
            Edge temp = pq.poll();
            // 이미 방문한 노드면 스킵 -> 우선순위큐로 사용해서 한번 방문하면 제일 작은 값으로 방문한거라 종료 가능
            if(visited[temp.n]) continue;
            visited[temp.n] = true;
            if(temp.n == n) break;
            for(Edge e : graph[temp.n]){
                // 새로 방문한 노드까지 가는 비용이 시작점에서 바로 가는거랑 거쳐서 가는것 중에 뭐가 더 싼지 비교
                int n_weight = dist[temp.n] + e.w;
                // 거쳐서 가는 경우가 더 싸면 거리 갱신하고 큐에 추가
                if(dist[e.n] > n_weight){
                    // 굳이 개선되지 않아서 비용이 큰 상태인데, pq에 넣어봤자 크기만 차지하니까 개선된 경우만 삽입
                    dist[e.n] = Math.min(dist[e.n], n_weight);
                    pq.offer(new Edge(e.n, dist[e.n]));
                }
            }
        }
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // n = 정점의 개수 (노드 개수) 정점 번호는 1~n , m = 간선의 개수
        int n = Integer.parseInt(input[0]), m = Integer.parseInt(input[1]);
        List<Edge>[] graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        // 간선 개수만큼 연결
        for(int i = 0; i < m; i++){
            input = br.readLine().split(" ");
            int v = Integer.parseInt(input[0]), e = Integer.parseInt(input[1]), w = Integer.parseInt(input[2]);
            // 연결 리스트 생성 -> 정점 v의 간선 e, 가중치 w
            graph[v].add(new Edge(e,w));
        }
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        Djkstra(graph, dist, n);
        for(int i = 1; i <= n; i++) System.out.print(dist[i] + " ");
    }
}