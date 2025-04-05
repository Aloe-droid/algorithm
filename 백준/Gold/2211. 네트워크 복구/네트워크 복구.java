import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Line>> list;
    static PriorityQueue<Line> pq;
    static int[] dp, prev;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        dp = new int[N + 1];
        prev = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            dp[i] = 100_000_000;
            prev[i] = -1;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Line(y, w));
            list.get(y).add(new Line(x, w));
        }

        bfs();

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if(prev[i] == -1) continue;
            cnt++;
            sb.append(prev[i]).append(" ").append(i).append("\n");
        }
        sb.insert(0, cnt + "\n");
        System.out.println(sb);
    }

    public static void bfs() {
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.w));
        dp[1] = 0;
        pq.add(new Line(1, 0));

        while(!pq.isEmpty()) {
            Line line = pq.poll();
            if(line.w > dp[line.n]) continue;

            for(Line next : list.get(line.n)) {
                if(dp[next.n] <= dp[line.n] + next.w) continue;
                dp[next.n] = dp[line.n] + next.w;
                prev[next.n] = line.n;
                pq.add(new Line(next.n, dp[next.n]));
            }
        }
    }
}

class Line {
    int n, w;
    public Line(int n, int w) {
        this.n = n;
        this.w = w;
    }
}
