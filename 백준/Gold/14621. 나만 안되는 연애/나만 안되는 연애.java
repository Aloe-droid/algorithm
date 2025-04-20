import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Node>> list;
    static boolean[] visit, sex;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sex = new boolean[N + 1];
        visit = new boolean[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            char c = st.nextToken().charAt(0);
            if(c == 'M') sex[i] = true;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y, w));
            list.get(y).add(new Node(x, w));
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        pq.add(new Node(1, 0));

        int cnt = 0;
        int sum = 0;
        while(!pq.isEmpty()) {
            Node n = pq.poll();
            if(visit[n.n]) continue;

            visit[n.n] = true;
            cnt++;
            sum += n.w;
            if(cnt == N) break;
            for(Node nx : list.get(n.n)) {
                if(visit[nx.n] || sex[n.n] == sex[nx.n]) continue;
                pq.add(nx);
            }
        }

        return cnt == N ? sum : -1;
    }
}

class Node {
    int n, w;
    public Node(int n, int w) {
        this.n = n;
        this.w = w;
    }
}
