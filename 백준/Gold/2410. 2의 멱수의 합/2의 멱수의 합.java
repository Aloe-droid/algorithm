import java.io.*;
import java.util.*;

class Main {
    static int N, dv = 1_000_000_000;
    static int[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[Math.max(N + 1, 3)];

        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= N; i++) {
            if(i % 2 == 1) dp[i] = dp[i - 1];
            else dp[i] = (dp[i - 1] + dp[i / 2]) % dv;
        }
        System.out.println(dp[N]);
    }
}