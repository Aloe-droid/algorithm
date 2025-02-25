import java.io.*;
import java.util.*;

class Main {
    static int N, M, max = 1_000_000_000;
    static int[][] dp, next;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        next = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                dp[i][j] = max;
            }
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[a][b] = Math.min(dp[a][b], c);
            next[a][b] = a;
        }
        
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i == j) continue;
                    int a = dp[i][k] + dp[k][j];
                    int b = dp[i][j];
                    dp[i][j] = Math.min(a, b);
                    if(a < b) {
                        next[i][j] = next[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int v = dp[i][j] == max ? 0 : dp[i][j];
                sb.append(v).append(" ");
            }
            sb.append("\n");
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(dp[i][j] == max) {
                    sb.append(0).append("\n");
                    continue;
                }

                int cnt = 1;
                int k = j;
                Stack<Integer> st = new Stack<>();
                while(k != i) {
                    st.add(k);
                    cnt++;
                    k = next[i][k];
                }
                
                sb.append(cnt).append(" ").append(i).append(" ");
                while(!st.isEmpty()) sb.append(st.pop()).append(" ");
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}