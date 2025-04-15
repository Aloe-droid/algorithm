import java.io.*;
import java.util.*;

class Main {
    static int N, M, max = 100_000_000;
    static List<List<Node>> list;
    static int[] dp, prev;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            prev = new int[N + 1];
            dp = new int[N + 1];

            for(int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
                prev[i] = -1;
                dp[i] = max;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                list.get(x).add(new Node(y, w));
                list.get(y).add(new Node(x, w));
            }

            pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
            pq.add(new Node(0, 0));
            dp[0] = 0;

            while(!pq.isEmpty()) {
                Node n = pq.poll();
                if(dp[n.n] < n.w) continue;

                for(Node nx : list.get(n.n)) {
                    if(dp[nx.n] <= dp[n.n] + nx.w) continue;
                    dp[nx.n] = dp[n.n] + nx.w;
                    pq.add(new Node(nx.n, dp[nx.n]));
                    prev[nx.n] = n.n;
                }
            }

            Stack<Integer> temp = new Stack<>();
            int p = prev[N - 1];
            while(p != -1) {
                temp.add(p);
                p = prev[p];
            }

            sb.append("Case #").append(tc).append(": ");
            if(temp.isEmpty()) sb.append("-1");
            else {
                while(!temp.isEmpty()) sb.append(temp.pop()).append(" ");
                sb.append(N - 1);
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}

class Node {
    int n, w;
    public Node(int n, int w) {
        this.n = n;
        this.w = w;
    }
}
