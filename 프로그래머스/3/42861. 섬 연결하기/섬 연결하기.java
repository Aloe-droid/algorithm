import java.util.*;

class Solution {
    int N;
    boolean[] visit;
    List<List<Line>> list;
    PriorityQueue<Line> pq;
    
    public int solution(int n, int[][] costs) {
        init(n, costs);
        return bfs();
    }
    
    public int bfs() {
        int cnt = 0;
        pq.add(new Line(0, 0));
        
        while(!pq.isEmpty()) {
            Line line = pq.poll();
            if(visit[line.node]) continue;
            visit[line.node] = true;
            cnt += line.weight;
            for(Line next : list.get(line.node)) {
                if(visit[next.node]) continue;
                pq.add(next);
            }
        }
        return cnt;
    }
    
    public void init(int n, int[][] costs) {
        N = n;
        visit = new boolean[N];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(new ArrayList<>());
        for(int[] cost : costs) {
            list.get(cost[0]).add(new Line(cost[1], cost[2]));
            list.get(cost[1]).add(new Line(cost[0], cost[2]));
        }
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.weight));
    }
}

class Line {
    int node, weight;
    public Line(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
