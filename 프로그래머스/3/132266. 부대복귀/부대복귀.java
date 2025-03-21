import java.util.*;

class Solution {
    List<List<Integer>> list;
    Queue<Integer> q;
    int[] visit;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        init(n, roads);
        bfs(destination);
        return getD(sources);
    }
    
    public void init(int n, int[][] roads) {
        list = new ArrayList<>();
        q = new LinkedList<>();
        visit = new int[n + 1];
        
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            visit[i] = -1;
        }
        
        for(int[] road : roads) {
            list.get(road[0]).add(road[1]);
            list.get(road[1]).add(road[0]);
        }
    }
    
    public void bfs(int init) {
        q.add(init);
        visit[init] = 0;
        
        while(!q.isEmpty()) {
            int x = q.poll();
            
            for(int nx : list.get(x)) {
                if(visit[nx] != -1) continue;
                q.add(nx);
                visit[nx] = visit[x] + 1;
            }
        }
    }
    
    public int[] getD(int[] source) {
        int[] distance = new int[source.length];
        for(int i = 0; i < source.length; i++) distance[i] = visit[source[i]];
        return distance;
    }
}