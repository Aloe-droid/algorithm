import java.io.*;
import java.util.*;

class Main {
    static int N, max = 100_000_000;
    static int[] ints, sum;
    static int[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            ints = new int[N];
            sum = new int[N];
            dp = new int[N][N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                ints[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i], max);
            }
            for(int i = 0; i < N; i++) {
                dp[i][i] = 0;
                sum[i] += (i > 0 ? sum[i - 1] : 0) + ints[i];
            } 
            
            for(int i = 0; i < N - 1; i++){
                dp[i][i + 1] = ints[i] + ints[i + 1];
            }
            
            // 간격
            for(int i = 2; i <= N; i++) {
                // 시작 지점
                for(int s = 0; s <= N - i; s++) {
                    int e = s + i - 1;
                    // 중간 지점
                    for(int m = s + 1; m <= e; m++) {
                        int sumA = s != 0 ? sum[m - 1] - sum[s - 1] : sum[m - 1];
                        int sumB = sum[e] - sum[m - 1];

                        dp[s][e] = Math.min(dp[s][e], dp[s][m - 1] + dp[m][e] + sumA + sumB);
                    }
                }
            }

            sb.append(dp[0][N - 1]).append("\n");
        }
        System.out.println(sb);
    }
}