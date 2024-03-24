import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<List<Bridge>> list = new ArrayList<>();
        int start = Integer.parseInt(br.readLine());
        int[] dp = new int[V + 1];
        int INF = 100_000_000;
        Arrays.fill(dp, INF);
        for (int i = 0; i <= V; i++) list.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Bridge(y, w));
        }
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        pq.add(new Bridge(start, 0));
        dp[start] = 0;

        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();
            for (Bridge next : list.get(bridge.node)) {
                if (dp[bridge.node] + next.weight < dp[next.node]) {
                    dp[next.node] = dp[bridge.node] + next.weight;
                    pq.add(new Bridge(next.node, dp[next.node]));
                }
            }
        }

        for (int i = 1; i <= V; i++) sb.append(dp[i] != INF ? dp[i] : "INF").append("\n");
        System.out.println(sb);
    }
}

class Bridge implements Comparable<Bridge> {
    int node;
    int weight;

    Bridge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Bridge o) {
        return weight - o.weight;
    }
}