import java.io.*;
import java.util.*;

class Main {
    static int N, Q;
    static int[][] dist;
    static List<List<Node>> list;
    static Queue<Node> q;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            Arrays.fill(dist[i], - 1);
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y, w));
            list.get(y).add(new Node(x, w));
        }

        for(int i = 1; i <= N; i++) bfs(i);
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int cnt = 0;
            for(int j = 1; j <= N; j++) {
                if(dist[v][j] >= k) cnt++;
            }
            sb.append(cnt - 1).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int init) {
        q = new LinkedList<>();
        q.add(new Node(init, Integer.MAX_VALUE));
        dist[init][init] = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            Node node = q.poll();

            for(Node next : list.get(node.edge)) {
                if(dist[init][next.edge] != -1) continue;

                dist[init][next.edge] = Math.min(next.weight, node.weight);
                q.add(new Node(next.edge, dist[init][next.edge]));
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