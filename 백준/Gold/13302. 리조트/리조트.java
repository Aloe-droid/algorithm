import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] ints;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N + 1];
        dp = new int[N + 1][51];

        if(M > 0) {
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) ints[Integer.parseInt(st.nextToken())] = 1;
        }
        
        System.out.println(dfs(1, 0));
    }

    public static int dfs(int day, int coupon) {
        // 마지막 종료 조건
        if(day > N) return 0;
        // dp로 최적해를 구했으면 반환
        if(dp[day][coupon] != 0) return dp[day][coupon];
        // 리조트에 갈 수 없는날이면 넘김
        if(ints[day] == 1) return dfs(day + 1, coupon);

        int v1 = 10000 + dfs(day + 1, coupon);
        int v2 = 25000 + dfs(day + 3, coupon + 1);
        int v3 = 37000 + dfs(day + 5, coupon + 2);
        int v4 = coupon >= 3 ? dfs(day + 1, coupon - 3) : Integer.MAX_VALUE;

        return dp[day][coupon] = Math.min(Math.min(v1, v2), Math.min(v3, v4));
    }
}