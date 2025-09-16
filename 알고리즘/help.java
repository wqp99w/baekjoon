import java.io.*;
import java.util.*;

public class Main{
    public static class pos{
        int x, y;
        public pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main (String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double qwe = Math.pow(2,1);
        int n = Math.abs(1);
        Stack<pos> st = new Stack<>();
        st.push(new pos(1,2));
        st.peek();
        st.pop();
        st.isEmpty();
        st.size();

        Queue<pos> que = new ArrayDeque<>();
        que.offer(new pos(1,2));
        que.peek();
        que.poll();
        que.isEmpty();
        que.size();

        PriorityQueue<pos> pq = new PriorityQueue<>((a,b) -> {
            // 같은 경우에는 y의 값이 큰 놈이 앞으로 -> b.y - a.y 일때, b가 크면 양수고 a가 크면 음수로 나올텐데
            // compare(a,b)가 여기서 실행되는데 여기서 음수면 a가 b보다 우선순위가 높음 -> 앞에옴
            // 양수면 b가 a보다 우선순위가 높음 -> 뒷놈이 앞에 옴
            // -> 1,2 여기서 작은놈이 우선순위가 높아질라면 1 - 2 가 되야 -1이 되서 음수가 되고 이러면 1이 앞에 가게된다
            // 1,2에서 2가 앞으로 가게 (내림차순이려면) 결과값이 양수가 되서 뒤에 있는 놈이 앞으로 가게 해야함 -> 2 - 1 이면 양수
            // 따라서 (a,b)가 있다면, 오름차순으로 할거면 a - b, 내림차순으로 할거면 b - a 로 계산을 진행해야 한다.
            // 2,1 이 들어오면 경우 오름차순이면 1, 내림차순이면 -1이 되는데, 이러면 오름차일때는 양수니까 1이 앞으로, 내림차는 음수니까 2가 앞으로 간다.
            if(a.x == b.x) return b.y - a.y;
            return b.x - b.y;
        });
        pq.offer(new pos(1,2));
        pq.peek();
        pq.poll();
        pq.isEmpty();
        pq.size();
        //오름차순 정렬
        TreeSet<pos> set = new TreeSet<>((a,b) -> {
            if(a.x == b.x) return a.y - b.y;
            return a.x - b.x;
        });
        set.add(new pos(1,2));
        set.first();
        set.last();
        set.pollFirst();
        set.pollLast();
        set.isEmpty();
        set.size();

        pos[] poss = new pos[6];
        Arrays.sort(poss, (a,b)->{
            if(a.x == b.x ) return a.y - b.y;
            return a.x - b.x;
        });
    }
}
