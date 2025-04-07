import java.io.*;
import java.util.*;

class Main {
    static int T, N, D, C, max = 100_000_000;
    static List<List<Line>> list;
    static int[] dp;
    static PriorityQueue<Line> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            dp = new int[N + 1];
            for(int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
                dp[i] = max;
            }

            for(int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                list.get(y).add(new Line(x, w));
            }

            bfs(sb);
        }
        System.out.println(sb);
    }

    public static void bfs(StringBuilder sb) {
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.w));
        dp[C] = 0;
        pq.add(new Line(C, 0));

        while(!pq.isEmpty()) {
            Line line = pq.poll();
            if(dp[line.n] < line.w) continue;

            for(Line next : list.get(line.n)) {
                if(dp[next.n] <= dp[line.n] + next.w) continue;
                dp[next.n] = dp[line.n] + next.w;
                pq.add(new Line(next.n, dp[next.n]));
            }
        }

        int cnt = 0;
        int m = 0;
        for(int i = 1; i <= N; i++) {
            if(dp[i] == max) continue;
            cnt++;
            m = Math.max(m, dp[i]);
        }
        sb.append(cnt).append(" ").append(m).append("\n");
    }
}

class Line {
    int n, w;
    public Line(int n, int w) {
        this.n = n;
        this.w = w;
    }
}
