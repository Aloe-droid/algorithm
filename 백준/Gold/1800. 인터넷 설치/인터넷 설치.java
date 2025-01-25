import java.io.*;
import java.util.*;

class Main {
    static int N, P, K, free, max = 100_000_000;
    static int[][] dp;
    static boolean[][] visit;
    static List<List<Line>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];
        visit = new boolean[N + 1][K + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            if(i >= 2) Arrays.fill(dp[i], max);
        }

        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Line(y, w));
            list.get(y).add(new Line(x, w));
        }

        int left = 0;
        int right = 1_000_000;
        while(left <= right) {
            // 초기화
            for(int i = 0; i <= N; i++) {
                for(int j = 0; j <= K; j++) {
                    visit[i][j] = false;
                    if(i >= 2) dp[i][j] = max;
                }
            }

            free = (left + right) / 2;
            int value = dfs(N, K);
            if(value == max) left = free + 1;
            else right = free - 1;
        }

        System.out.println(left > 1_000_000 ? -1 : left);
    }

    public static int dfs(int n, int k) {
        if(visit[n][k]) return dp[n][k];
        if(dp[n][k] != max) return dp[n][k];

        visit[n][k] = true;
        for(Line next : list.get(n)) {
            if(k > 0) dp[n][k] = Math.min(dp[n][k], dfs(next.id, k - 1));
            if(next.p <= free) dp[n][k] = Math.min(dp[n][k], dfs(next.id, k) + next.p);
        }

        return dp[n][k];
    }
}

class Line {
    int id, p;
    public Line(int id, int p) {
        this.id = id;
        this.p = p;
    }
}