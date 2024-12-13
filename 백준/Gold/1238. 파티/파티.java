import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M, X, max = 1_000_000_000;
    static List<List<Road>> forward, backward;
    static PriorityQueue<Road> pq;
    static int[] dpF, dpB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        dpB = new int[N + 1];
        dpF = new int[N + 1];
        backward = new ArrayList<>();
        forward = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            forward.add(new ArrayList<>());
            backward.add(new ArrayList<>());
            dpF[i] = max;
            dpB[i] = max;
        }
        dpF[X] = 0;
        dpB[X] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            forward.get(x).add(new Road(y, t));
            backward.get(y).add(new Road(x, t));
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(road -> road.weight));

        pq.add(new Road(X, 0));
        while(!pq.isEmpty()){
            Road road = pq.poll();
            if(dpF[road.node] < road.weight) continue;
            for(Road next : forward.get(road.node)){
                if(dpF[next.node] <= dpF[road.node] + next.weight) continue;
                dpF[next.node] = dpF[road.node] + next.weight;
                pq.add(new Road(next.node, dpF[next.node]));
            }
        }

        pq.add(new Road(X, 0));
        while(!pq.isEmpty()){
            Road road = pq.poll();
            if(dpB[road.node] < road.weight) continue;
            for(Road next : backward.get(road.node)){
                if(dpB[next.node] <= dpB[road.node] + next.weight) continue;
                dpB[next.node] = dpB[road.node] + next.weight;
                pq.add(new Road(next.node, dpB[next.node]));
            }
        }

        int max = -1;
        for(int i = 1; i <= N; i++) max = Math.max(max, dpF[i] + dpB[i]);
        System.out.println(max);
    }
}

class Road {
    int node, weight;

    public Road(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}