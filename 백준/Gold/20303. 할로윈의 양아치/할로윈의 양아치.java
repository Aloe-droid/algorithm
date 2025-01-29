import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] p, candy, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        p = new int[N + 1];
        candy = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            p[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        int[] numbers = new int[N + 1];
        int[] candies = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            int pI = find(i);
            candies[pI] += candy[i];
            numbers[pI] += 1;
        }

        dp = new int[K];
        for(int i = 0; i <= N; i++) {
            if(numbers[i] == 0) continue;

            for(int k = K - 1; k >= numbers[i]; k--) {
                dp[k] = Math.max(dp[k], dp[k - numbers[i]] + candies[i]);
            }
        }
        System.out.println(dp[K - 1]);
    }

    public static void union(int x, int y){
        int pK = find(x);
        int pY = find(y);
        p[pK] = pY;
    }

    public static int find(int k) {
        if(p[k] == k) return k;
        return p[k] = find(p[k]);
    }
}