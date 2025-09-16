import java.io.*;
import java.util.*;
public class Main {

    public static class edge implements Comparable<edge>{
        int e;
        int w;

        public edge(int e, int w){
            this.e=e;
            this.w=w;
        }

        @Override
        public int compareTo(edge o){
            return this.w-o.w;
        }
    }
    public static List<edge> []graph;

    public static void djkstra(int start, int n){
        boolean[] visited =  new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.add(new edge(start,0));
        while(!pq.isEmpty()){
            edge temp = pq.poll();
            int node = temp.e, weight = temp.w;
            if(visited[node]) continue;
            visited[node] = true;
            for(edge next : graph[node]){
                if(next.w+dist[node]<dist[next.e]){
                    dist[next.e] = next.w+dist[node];
                    pq.add(new edge(next.e,next.w));
                }
            }
        }
        for(int i=1;i<=n;i++){
            if(dist[i]==Integer.MAX_VALUE) System.out.print("0 ");
            System.out.print(dist[i]+" ");
        }
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String []input = s.split(" ");
        int n=Integer.parseInt(input[0]), m = Integer.parseInt(input[1]);
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++) graph[i] = new ArrayList<>();

        for(int i=0;i<m;i++){
            s=br.readLine();
            input = s.split(" ");
            graph[Integer.parseInt(input[0])].add(new edge(Integer.parseInt(input[1]),Integer.parseInt(input[2])));
            //graph[Integer.parseInt(input[1])].add(new edge(Integer.parseInt(input[0]),Integer.parseInt(input[2])));
        }

        djkstra(1,n);
    }

}
