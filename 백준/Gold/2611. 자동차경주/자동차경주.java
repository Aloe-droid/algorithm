import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Road>> list;
    static int[] dp, next;

    public static void main(String[] args) throws Exception {
        // https://www.acmicpc.net/problem/2611
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        dp = new int[N + 1];
        next = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            dp[i] = -1;
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Road(y, w));
        }

        sb.append(dfs(new Road(1, 0))).append("\n").append("1 ");
        int n = next[1];
        while(n != 1 && n != -1) {
            sb.append(n).append(" ");
            n = next[n];
        }
        if(n == 1) sb.append(1);
        System.out.println(sb);
    }

    public static int dfs(Road road) {
        if(dp[road.n] != -1) return dp[road.n];

        dp[road.n] = 0;
        int max = 0, id = -1;
        for(Road next : list.get(road.n)) {
            int value = dfs(next) + next.w;
            if(value > max) {
                max = value;
                id = next.n;
            }
        }

        next[road.n] = id;
        return dp[road.n] += max;
    }
}

class Road {
    int n, w;
    public Road(int n, int w) {
        this.n = n;
        this.w = w;
    }
}
