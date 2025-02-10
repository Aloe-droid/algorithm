import java.util.*;

class Solution {
    static int N, cnt;
    static List<int[]> queens;
    public int solution(int n) {
        N = n;
        cnt = 0;
        queens = new ArrayList<>();
        dfs(0);
        return cnt;
    }
    
    public static void dfs(int x) {
        if(x == N) {
            cnt++;
            return;
        }
        
        for(int y = 0; y < N; y++) {
            boolean flag = true;
            for(int[] queen : queens) {
                // 1. 같은 y축에 있는 경우
                if(queen[1] == y) {
                    flag = false; 
                    break;
                }
                
                // 2. 오른쪽 아래로 가는 대각선에 겹치는 경우
                if(x - y == queen[0] - queen[1]) {
                    flag = false;
                    break;
                }
                
                // 3. 왼쪽 아래로 가는 대각선에 겹치는 경우
                if(x - queen[0] == -1 * (y - queen[1])) {
                    flag = false;
                    break;
                }
            }
            
            if(flag) {
                int[] point = new int[] {x, y};
                queens.add(point);
                dfs(x + 1);
                queens.remove(point);
            }
        }
    }
}