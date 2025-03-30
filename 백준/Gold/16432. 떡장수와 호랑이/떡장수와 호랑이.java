import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints;
    static int[] ans;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = new int[N];
        dp = new boolean[N][10];
        ints = new int[N][10];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int J = Integer.parseInt(st.nextToken());
            for(int j = 0; j < J; j++) {
                ints[i][Integer.parseInt(st.nextToken())]++;
            }
        }

        boolean flag = dfs(0, 0);
        StringBuilder sb = new StringBuilder();
        for(int a : ans) sb.append(a).append("\n");
        System.out.println(flag ? sb.toString() : -1);
    }

    public static boolean dfs(int day, int prev) {
        if(day == N) return true;
        if(dp[day][prev]) return false;

        dp[day][prev] = true;
        for(int i = 1; i < 10; i++) {
            if(ints[day][i] != 0 && prev != i) {
                ans[day] = i;
                if(dfs(day + 1, i)) return true;
            }
        }
        return false;
    }
}
