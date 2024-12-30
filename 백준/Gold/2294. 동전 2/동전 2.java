import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;

        for(int i = 0; i < M; i++){
            int k = Integer.parseInt(br.readLine());
            for(int j = k; j <= N; j++) {
                dp[j] = Math.min(dp[j], dp[j - k] + 1);
            }
        }
        System.out.println(dp[N] == 1_000_000_000 ? -1 : dp[N]);
    }
}