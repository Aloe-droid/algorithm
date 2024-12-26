import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] a = new long[N + 1];
        int[] c = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) a[i] = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) c[i] = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N + 1][10_001];
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j <= 10_000; j++){
                long l = j - c[i] >= 0 ? dp[i - 1][j - c[i]] + a[i] : 0L;
                dp[i][j] = Math.max(dp[i - 1][j], l);
                if(dp[i][j] >= M) min = Math.min(min, j);
            }
        }
        System.out.println(min);
    }
}