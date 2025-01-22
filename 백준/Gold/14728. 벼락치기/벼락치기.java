import java.io.*;
import java.util.*;

class Main {
    static int N, T;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         T = Integer.parseInt(st.nextToken());
         dp = new int[T + 1];

         for(int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             int k = Integer.parseInt(st.nextToken());
             int v = Integer.parseInt(st.nextToken());

             for(int id = T; id >= k; id--) {
                 dp[id] = Math.max(dp[id], v + dp[id - k]);
             }
         }
         System.out.println(dp[T]);
    }
}