import java.io.*;
import java.util.*;

public class Main{
    public static int calc(int[] line){
        // 현재 칸 양쪽에 벽이 존재해야함 -> 현재칸보다 양 쪽이 더 커야함
        // 각 칸별로 고인물 계산
        // 맨 왼쪽 맨 오른쪽은 빗물이 고일 수 없음 -> 제외
        int out = 0;
        for(int i = 1; i < line.length-1; i++){
            // 다른칸 생각말고 현재 칸만 생각하면 현재 칸 양쪽의 벽의 크기만 계산하면 됨
            int left = 0, right = 0;
            // 왼쪽 벽
            for(int j = 0; j < i; j++) left = Math.max(left,line[j]);
            // 오른쪽 벽
            for(int j = i+1; j < line.length; j++) right = Math.max(right,line[j]);
            // 양쪽 벽 비교해서 작은 벽 찾기 -> 만약 3 1 4 이런식으로 벽이 존재한다고 하면 빗물은 2칸까지만 고이지 3칸 고이면 넘쳐서 안됨
            int wall = Math.min(left, right);
            // 만약 찾은 벽이 현재 칸보다 작거나 같으면 빗물이 안고임 -> 현재칸보다 큰지 확인
            if(wall - line[i] > 0) out += wall - line[i];
        }
        return out;
    }

    public static void main(String[] arg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int w = Integer.parseInt(input[1]);
        int[] line = new int[w];
        input = br.readLine().split(" ");
        for(int i = 0; i < w; i++){
            line[i] = Integer.parseInt(input[i]);
        }
        System.out.println(calc(line));
    }

}