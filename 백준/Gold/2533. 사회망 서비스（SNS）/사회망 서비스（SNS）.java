import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<List<Integer>> list;
    static int[][] dp;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        check = new boolean[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int x) {
        check[x] = true;
        dp[x][0] = 0; // k가 얼리어답터가 아닌 경우
        dp[x][1] = 1; // k가 얼리어답터인 경우

        for(int nx : list.get(x)) {
            if(check[nx]) continue;

            dfs(nx);
            dp[x][0] += dp[nx][1];
            dp[x][1] += Math.min(dp[nx][0], dp[nx][1]);
        }
    }
}