import java.io.*;
import java.util.*;

class Main {
    static int N, M, T, S, G, H, D, max = 1_000_000_000;
    static int[] realDp, dp1, dp2, end;
    static List<List<Node>> list;
    static PriorityQueue<Node> pq;
    static Set<Integer> ans;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            realDp = new int[N + 1];
            dp1 = new int[N + 1];
            dp2 = new int[N + 1];
            for(int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
                realDp[i] = max;
                dp1[i] = max;
                dp2[i] = max;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                list.get(x).add(new Node(y, w));
                list.get(y).add(new Node(x, w));
                if((x == G && y == H) || (x == H && y == G)) D = w;
            }

            end = new int[T];
            for(int i = 0; i < T; i++) end[i] = Integer.parseInt(br.readLine());
            
            find(S, realDp);
            find(G, dp1);
            find(H, dp2);
            check();

            for(int e : ans) sb.append(e).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void check() {
        ans = new TreeSet<>();
        
        // 1. S -> G, G -> H, H -> E 값 vs S -> E 
        for(int E : end) {
            int w1 = realDp[G] + D + dp2[E];
            int w2 = realDp[E];

            if(w1 != max && w1 == w2) ans.add(E);
        }

        // 2. S -> H, H -> G, G -> E 값 vs S -> E
        for(int E : end) {
            int w1 = realDp[H] + D + dp1[E];
            int w2 = realDp[E];

            if(w1 != max && w1 == w2) ans.add(E);
        }
    }

    public static void find(int start, int[] dp) {
        pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        dp[start] = 0;
        pq.add(new Node(start, dp[start]));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(dp[node.edge] < node.weight) continue;

            for(Node next : list.get(node.edge)) {
                if(dp[next.edge] <= dp[node.edge] + next.weight) continue;

                dp[next.edge] = dp[node.edge] + next.weight;
                pq.add(new Node(next.edge, dp[next.edge]));
            }
        }
    }
}

class Node {
    int edge, weight;

    public Node(int edge, int weight) {
        this.edge = edge;
        this.weight = weight;
    }
}