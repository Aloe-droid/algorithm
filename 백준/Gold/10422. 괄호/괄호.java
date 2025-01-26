import java.io.*;
import java.util.*;
import java.math.*;

class Main {
    static int L, dv = 1_000_000_007;
    static long[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            L = Integer.parseInt(br.readLine());
            if(L % 2 == 1) {
                sb.append("0\n");
                continue;
            }

            dp = new long[L + 1];
            dp[0] = 1;
            for(int i = 2; i <= L; i+= 2) {
                for(int j = 0; j <= i - 2; j++){
                    dp[i] += (dp[j] * dp[i - j - 2]) % dv;
                    dp[i] %= dv;
                }
            }
            sb.append(dp[L]).append("\n"); 
        }
        System.out.println(sb);
    }
}