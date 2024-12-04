import java.util.*;

class Solution {
    static int[] weak, dist, sum, position;
    static boolean[] check;
    static int n, min = Integer.MAX_VALUE;
    
    public int solution(int n, int[] weak, int[] dist) {
        for(int k : dist) if(k >= n) return 1;
        
        Solution.n = n;
        Solution.weak = weak;
        Solution.dist = dist;
        sum = new int[n];
        position = new int[201];
        check = new boolean[dist.length];
        for(int i : weak){
            sum[i] = 1;
            position[i] = 1;
        }
        
        for(int i = 1; i < n; i++) sum[i] += sum[i - 1];
        
        for(int i : weak) dfs(i, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    public static void dfs(int start, int person, int cnt){
        if(min <= person) return;
        
        if(cnt == weak.length){
            min = Math.min(min, person);
            return;
        }
        
        for(int i = 0; i < dist.length; i++){
            if(check[i]) continue;
            
            check[i] = true;
            
            int end = (start + dist[i]) % n;
            int count = walk(start, end);
            
            int next = -1;
            for(int k = 1; k <= n; k++){
                next = (end + k) % n;
                if(position[next] == 1) break;
            }
            dfs(next, person + 1, cnt + count);

            check[i] = false;
        }
    }
    
    public static int walk(int start, int end){
         // 0을 넘어서는 경우
        if(start > end){
            int temp = sum[n - 1] - sum[(start - 1) % n];
            return temp + sum[end]; 
        }
        
        if(start == 0) return sum[end];
        else return sum[end] - sum[(start - 1) % n];
    }
}