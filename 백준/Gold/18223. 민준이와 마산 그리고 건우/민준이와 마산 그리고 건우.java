import java.io.*;
import java.util.*;

class Main {
    static int V, E, P, max = 100_000_000;
    static List<List<Line>> list;
    static int[] dp;
    static PriorityQueue<Line> pq;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        dp = new int[V + 1];
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.weight));
        list = new ArrayList<>();
        
        for(int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
            dp[i] = max;
        }
        
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Line(y, w));
            list.get(y).add(new Line(x, w));
        }

        // 민준이가 마산까지 가는 최소값과 건우한테 가는 최소값
        find(1);
        int minV = dp[V];
        int v1 = dp[P];

        // 건우에서 마산까지 가는 최소값
        Arrays.fill(dp, max);
        find(P);
        int v2 = dp[V];

        System.out.println(minV == v1 + v2 ? "SAVE HIM" : "GOOD BYE");
    }

    public static void find(int start) {
        dp[start] = 0;
        pq.add(new Line(start, 0));
        while(!pq.isEmpty()) {
            Line line = pq.poll();
            if(dp[line.node] < line.weight) continue;
            for(Line next : list.get(line.node)) {
                if(dp[next.node] <= dp[line.node] + next.weight) continue;
                dp[next.node] = dp[line.node] + next.weight;
                pq.add(new Line(next.node, dp[next.node]));
            }
        }
    }
}

class Line {
    int node, weight;
    public Line(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}