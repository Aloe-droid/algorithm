import java.util.*;

class Solution {
    public int solution(int N, int[][] roads, int K) {
        List<List<Road>> list = new ArrayList<>();
        int[] dp = new int[N + 1];
        int max = 1_000_000_000;
        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
            if(i > 1) dp[i] = max; 
        }
        
        for(int[] ii : roads){
            int a = ii[0];
            int b = ii[1];
            int c = ii[2];
            
            list.get(a).add(new Road(b, c));
            list.get(b).add(new Road(a, c));
        }
        
        PriorityQueue<Road> q = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        q.add(new Road(1, 0));
        
        while(!q.isEmpty()){
            Road road = q.poll();
            for(Road next : list.get(road.node)){
                if(dp[next.node] <= dp[road.node] + next.weight) continue;
                dp[next.node] = dp[road.node] + next.weight;
                q.add(new Road(next.node, dp[next.node]));
            }
        }
        
        
        int answer = 0;
        for(int i = 1; i <= N; i++) if(dp[i] <= K) answer++;
        return answer;
    }
}

class Road{
    int node, weight;
    
    public Road(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}
