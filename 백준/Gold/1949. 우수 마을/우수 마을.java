import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<List<Integer>> list;
    static int[] ints;
    static int[][] dp;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) ints[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        dp = new int[N + 1][2];
        visit = new boolean[N + 1];

        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int x) {
        visit[x] = true;
        dp[x][0] = 0;
        dp[x][1] = ints[x];

        for(int nx : list.get(x)) {
            if(visit[nx]) continue;

            dfs(nx);
            dp[x][0] += Math.max(dp[nx][0], dp[nx][1]);
            dp[x][1] += dp[nx][0];
        }
    }
}