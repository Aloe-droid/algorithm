import java.io.*;
import java.util.*;

class Main {
    static int max = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TC; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            List<List<Integer>> nl = new ArrayList<>();
            for(int i = 0; i <= N; i++) nl.add(new ArrayList<>());

            List<Road> list = new ArrayList<>();
            int[] dp = new int[N + 1];

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                list.add(new Road(s, e, t));
                list.add(new Road(e, s, t));
                nl.get(s).add(e);
                nl.get(e).add(s);
            }

            for(int i = 0; i < W; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken()) * -1;
                list.add(new Road(s, e, t));
                nl.get(s).add(e);
            }

            Set<Integer> hs = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            boolean[] check = new boolean[N + 1];
            for(int i = 1; i <= N; i++){
                if(check[i]) continue;

                hs.add(i);
                q.add(i);
                check[i] = true;
                while(!q.isEmpty()){
                    int x = q.poll();
                    for(int nx : nl.get(x)){
                        if(check[nx]) continue;

                        q.add(nx);
                        check[nx] = true;
                    }
                }
            }

            boolean flag = false;
            outer:
            for(int k : hs){
                Arrays.fill(dp, max);
                dp[k] = 0;
                for(int i = 0; i < N - 1; i++){
                    for(Road road : list){
                        if(dp[road.src] == max) continue;
                        dp[road.dst] = Math.min(dp[road.dst], dp[road.src] + road.weight);
                    }
                }

                for(Road road : list){
                    if(dp[road.src] == max) continue;
                    if(dp[road.dst] > dp[road.src] + road.weight){
                        flag = true;
                        break outer;
                    }
                }
            }

            sb.append(flag ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }
}

class Road{
    int src, dst, weight;
    public Road(int src, int dst, int weight){
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }
}