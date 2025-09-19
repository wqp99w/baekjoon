import java.io.*;
import java.util.*;

public class Main{
    // 루트 번호 찾는 함수
    public static int find(int[] parent, int x){
        // 자기자신이 루트면 그냥 루트 반환
        if(parent[x] == x) return x;

        // 압축
        return parent[x] = find(parent, parent[x]);
    }

    public static void union(int[] parent, int a, int b){
        // a랑 b 부모 찾기
        a = find(parent, a);
        b = find(parent, b);
        if (a == b) return;
        // a랑 b의 루트가 다른 경우 병합해서 하나로 만들기
        // 큰 놈을 대표로 잡아서
        if(a < b) parent[a] = b;
        else parent[b] = a;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]), m = Integer.parseInt(input[1]), k = Integer.parseInt(input[2]);
        int[] friend = new int[n+1];
        int[] money = new int[n+1];
        input = br.readLine().split(" ");
        // 친구비용
        for(int i = 1; i <= n; i++){
            money[i] = Integer.parseInt(input[i-1]);
            // 부모관계 초기는 다 자기자신이 부모
            friend[i] = i;
        }
        for(int i = 0; i < m; i++){
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]), b = Integer.parseInt(input[1]);
            // 친구 관계 들어왔으니까 부모 자식 관계로 묶기 -> 큰 값이 루트가 되게 설정함
            union(friend, a,b);
        }
        // 친구비 계산
        int[] minCost = new int[n+1];
        Arrays.fill(minCost,Integer.MAX_VALUE);
        for(int i = 1; i <= n; i++){
            // 자기 자신 비용이랑 루트 비용이랑 비교해서 더 싼 값으로 설정
            int root = find(friend, i);
            // 루트 비용이 지금 루트 최소값이랑 자식 값중 더 최솟값으로 설정
            minCost[root] = Math.min(money[i], minCost[root]);
        }
        long output = 0;
        for(int i = 1; i <= n; i++){
            if(minCost[i] != Integer.MAX_VALUE) output += minCost[i];
        }
        if(output <= k) System.out.println(output);
        else System.out.println("Oh no");
    }
}