import java.util.*;

class Solution {
    static int N;
    static List<List<Integer>> list;
    static int[][] dp;
    static boolean[] visit;
    
    public int solution(int n, int[][] lighthouse) {
        init(n, lighthouse);
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public static void init(int n, int[][] lighthouse) {
        N = n;
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for(int[] light : lighthouse) {
            int x = light[0];
            int y = light[1];
            list.get(x).add(y);
            list.get(y).add(x);
        }
        
        dp = new int[N + 1][2];
        visit = new boolean[N + 1];
    }
    
    public static void dfs(int x) {
        visit[x] = true;
        dp[x][0] = 0;
        dp[x][1] = 1;

        for(int nx : list.get(x)) {
            if(visit[nx]) continue;
            
            dfs(nx);
            dp[x][0] += dp[nx][1];
            dp[x][1] += Math.min(dp[nx][1], dp[nx][0]);
        }
    }
}