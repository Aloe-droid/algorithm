import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] has;
    static List<List<Node>> list;
    static PriorityQueue<Node> pq;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        has = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) has[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y, w));
            list.get(y).add(new Node(x, w));
        }

        System.out.println(find());
    }

    public static int find () {
        visit = new boolean[N + 1];
        pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        for(int h : has) pq.add(new Node(h, 0));
        
        int cnt = 0;
        int sum = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.e]) continue;

            visit[node.e] = true;
            sum += node.w;
            if(++cnt == N) break;

            for(Node next : list.get(node.e)) {
                if(visit[next.e]) continue;
                pq.add(next);
            }
        }
        return sum;
    }
}

class Node {
    int e, w;
    
    public Node(int e, int w) {
        this.e = e;
        this.w = w;
    }
}
