import java.io.*;
import java.util.*;

public class Main{
    public static int N, M, MAX = 1_000_000_000;
    public static List<List<Road>> list;
    public static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        dp = new int[N + 1];
        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
            dp[i] = MAX;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Road(b, c));
            list.get(b).add(new Road(a, c));
        }

        find();
        System.out.println(dp[N]);
    }

    public static void find(){
        PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.add(new Road(1, 0));
        dp[1] = 0;

        while(!pq.isEmpty()){
            Road road = pq.poll();
            if(road.weight > dp[road.node]) continue;
            if(road.node == N) return;

            for(Road next : list.get(road.node)){
                if(dp[next.node] <= dp[road.node] + next.weight) continue;
                dp[next.node] = dp[road.node] + next.weight;
                pq.add(new Road(next.node, dp[next.node]));
            }
        }
    }
}

class Road{
    int node, weight;

    public Road(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}