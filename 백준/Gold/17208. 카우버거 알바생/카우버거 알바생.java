import java.io.*;
import java.util.*;

class Main {
    static int N, M, K, max = 0;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[M + 1][K + 1];
        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for(int x = M; x >= a; x--){
                for(int y = K; y >= b; y--) {
                    dp[x][y] = Math.max(dp[x][y], dp[x - a][y - b] + 1);
                }
            }
        }
        System.out.println(dp[M][K]);
    }

}