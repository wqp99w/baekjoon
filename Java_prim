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
    public static int []check;
    public static void prim(int start, int n){
        boolean[] visited = new boolean[n+1];
        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.add(new edge(start,0));
        int total = 0;
        while(!pq.isEmpty()){
            edge temp = pq.poll();
            int node = temp.e, weigh = temp.w;
            if(visited[node]) continue;
            visited[node] = true;
            total+=weigh;

            for(edge e:graph[node]) {
                if (!visited[e.e])
                    pq.add(e);
            }
        }
        System.out.println(total);
    }


    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String []input = s.split(" ");
        int n=Integer.parseInt(input[0]), m = Integer.parseInt(input[1]);
        graph = new ArrayList[n+1];
        check = new int[m+1];
        for(int i=1;i<=n;i++) graph[i] = new ArrayList<>();

        for(int i=0;i<m;i++){
            s=br.readLine();
            input = s.split(" ");
            graph[Integer.parseInt(input[0])].add(new edge(Integer.parseInt(input[1]),Integer.parseInt(input[2])));
            graph[Integer.parseInt(input[1])].add(new edge(Integer.parseInt(input[0]),Integer.parseInt(input[2])));
        }

        prim(1,n);
    }

}
