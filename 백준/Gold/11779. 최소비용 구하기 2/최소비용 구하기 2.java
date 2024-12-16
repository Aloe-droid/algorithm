import java.io.*;
import java.util.*;

class Main {
    static List<List<Road>> list;
    static List<List<Integer>> ans;
    static int[] dp;
    static int s, e;
    public static void main(String[] args) throws Exception {
        init();
        find();
        print();
    }

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m =Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        ans = new ArrayList<>();
        dp = new int[n + 1];
        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
            ans.add(new ArrayList<>());
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.get(x).add(new Road(y, z));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
    }

    public static void find() {
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.weight));
        pq.add(new Road(s, 0));
        dp[s] = 0;
        ans.get(s).add(s);

        while(!pq.isEmpty()){
            Road road = pq.poll();
            if(dp[road.node] < road.weight) continue;

            for(Road next : list.get(road.node)){
                if(dp[next.node] <= dp[road.node] + next.weight) continue;
                dp[next.node] = dp[road.node] + next.weight;
                List<Integer> newList = new ArrayList<>(ans.get(road.node));
                newList.add(next.node);
                ans.set(next.node, newList);
                pq.add(new Road(next.node, dp[next.node]));
            }
        }
    }

    public static void print(){
        System.out.println(dp[e]);
        System.out.println(ans.get(e).size());
        for(int k : ans.get(e)) System.out.print(k + " ");
    }
}

class Road {
    int node, weight;
    public Road(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}