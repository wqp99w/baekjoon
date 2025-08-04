import java.io.*;
import java.util.*;

public class Main{
    public static class Point {
        long x, y;
        Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] s = input.split(" ");
        int n = Integer.parseInt(s[0]);
        int l = Integer.parseInt(s[1]);
        Point[] p = new Point[n];
        // 시작 위치, 끝 위치 저장 -> 끝 위치에는 물이 없는 것 같음
        for(int i = 0; i < n; i++){
            s = br.readLine().split(" ");
            p[i] = new Point(Long.parseLong(s[0]),Long.parseLong(s[1]));
        }
        // 시작 위치 오름차순 정렬
        Arrays.sort(p, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b){
                return Long.compare(a.x,b.x);
            }
        });
        long end = -1;
        long out = 0;
        for(int i = 0; i < n; i++){
            // 마지막에 둔 널빤지의 위치가 현재 물웅덩이 시작 위치에 겹칠 때 -> 물웅덩이 시작 위치를 널빤지 위치 + 1로 해야 함
            if(p[i].x <= end) {
                p[i].x = end + 1;
            }
            // 이미 웅덩이가 덮인 경우는 넘어가기
            if(p[i].y<=end) continue;
            long nul = 0;
            // 웅덩이 크기가 널빤지 크기에 딱 맞는 경우
            if((p[i].y - p[i].x) % l == 0){
                nul = (p[i].y - p[i].x) / l;
            }
            // 딱 안맞으면 하나 더 넣어야 함
            else{
                nul = (p[i].y - p[i].x) / l + 1;
            }
            out += nul;
            end = (p[i].x + (l*nul)) - 1;
        }
        System.out.println(out);
    }
}