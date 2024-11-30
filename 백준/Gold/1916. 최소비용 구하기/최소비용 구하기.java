import java.io.*;
import java.util.*;

public class Main {
    public static int N, S, E;
    public static List<List<Road>> list = new ArrayList<>();
    public static int[] dp;
    public static boolean[] booleans;
    public static int max = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        booleans = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            dp[i] = max;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Road(b, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dp[S] = 0;
        find();
        System.out.println(dp[E]);
    }

    public static void find() {
        PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.add(new Road(S, 0));

        while (!pq.isEmpty()) {
            Road road = pq.poll();
            if(booleans[road.node]) continue;
            booleans[road.node] = true;

            for (Road next : list.get(road.node)) {
                if (dp[next.node] <= dp[road.node] + next.weight) continue;
                dp[next.node] = dp[road.node] + next.weight;
                pq.add(new Road(next.node, dp[next.node]));
            }
        }
    }
}

class Road {
    int node, weight;

    public Road(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}