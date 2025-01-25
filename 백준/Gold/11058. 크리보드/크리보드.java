import java.io.*;
import java.util.*;

class Main {
    static int N;
    static long[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        Arrays.fill(dp, -1);
        for(int i = 0; i <= Math.min(6, N); i++) dp[i] = i;
        System.out.println(dfs(N));
    }

    public static long dfs(int n) {
        if(n < 0) return 0;
        if(dp[n] != -1) return dp[n];

        dp[n] = 0;
        long v = 1 + dfs(n - 1);
        for(int i = 3; i < 6; i++) {
            v = Math.max(v, dfs(n - i) * (i - 1));
        }
        return dp[n] = v;
    }
}