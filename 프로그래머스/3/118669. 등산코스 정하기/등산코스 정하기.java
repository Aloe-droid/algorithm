import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Arrays.sort(summits);
        List<List<Bridge>> list = new ArrayList<>();
        Set<Integer> gs = new HashSet<>();
        Set<Integer> ss = new HashSet<>();
        for(int i : gates) gs.add(i);
        for(int i : summits) ss.add(i);
        
        for(int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for(int[] path : paths){
            list.get(path[0]).add(new Bridge(path[1], path[2]));
            list.get(path[1]).add(new Bridge(path[0], path[2]));
        }
        
        int[] ans = new int[2];
        ans[1] = Integer.MAX_VALUE;
        
        // 최소 비용 트리 생성
        for(int summit : summits){
            PriorityQueue<Bridge> pq = new PriorityQueue();
            Set<Integer> check = new HashSet<>();
        
            boolean flag = false;
            int w = 0;
            int cnt = 0;
                
            check.add(summit);
            for(Bridge bridge : list.get(summit)){
                if(bridge.weight < ans[1]){
                    if(ss.contains(bridge.node) && bridge.node != summit) continue;
                    pq.add(bridge);
                }
            }
                
            while(!pq.isEmpty() && cnt < n){
                Bridge bridge = pq.poll();
                check.add(bridge.node);
                cnt++;
                    
                w = w > bridge.weight ? w : bridge.weight;
                if(gs.contains(bridge.node)){
                    flag = true;
                    break;
                }
                    
                for(Bridge next : list.get(bridge.node)){
                    if(check.contains(next.node)) continue;
                    if(ss.contains(next.node)) continue;
                    if(next.weight >= ans[1]) continue;
                    
                    pq.add(next);
                }
            }
                
            if(flag){
                ans[0] = summit;
                ans[1] = w;
            }
        }
        
        return ans;
    }
}

class Bridge implements Comparable<Bridge>{
    int node;
    int weight;
    
    Bridge(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Bridge b){
        return weight - b.weight;
    }
    
    @Override
    public String toString(){
        return "node: " + node + ", weight: " + weight;
    }
}