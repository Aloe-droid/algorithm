import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Road>> list;
    static boolean[] visited;
    static PriorityQueue<Road> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.get(x).add(new Road(y, z));
            list.get(y).add(new Road(x, z));
        }
        System.out.println(find());
    }

    public static int find() {
        pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.weight));
        visited = new boolean[N + 1];
        pq.add(new Road(1, 0));

        int sum = 0;
        int max = 0;

        while (!pq.isEmpty()) {
            Road road = pq.poll();
            if (visited[road.node]) continue;
            visited[road.node] = true;
            sum += road.weight;
            max = Math.max(max, road.weight);

            for (Road next : list.get(road.node)) {
                if (!visited[next.node]) pq.add(next);
            }
        }
        return sum - max;
    }
}

class Road {
    int node, weight;

    public Road(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
