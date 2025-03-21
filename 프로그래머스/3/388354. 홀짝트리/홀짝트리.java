import java.util.*;

class Solution {
    int c1, c2;
    boolean[] v1, v2;
    List<List<Integer>> list;
    Queue<Integer> q;
    
    public int[] solution(int[] nodes, int[][] edges) {
        init(edges);
        
        // 1. 홀짝 트리의 가능성
        for(int n : nodes) {
            if(v1[n]) continue;
            if(n % 2 == 0 && list.get(n).size() % 2 == 0) bfs(n, v1, false);
            else if(n % 2 == 1 && list.get(n).size() % 2 == 1) bfs(n, v1, false);
        }
        
        // 2. 역홀짝 트리의 가능성 
        for(int n : nodes) {
            if(v2[n]) continue;
            if(n % 2 == 0 && list.get(n).size() % 2 == 1) bfs(n, v2, true);
            else if(n % 2 == 1 && list.get(n).size() % 2 == 0) bfs(n, v2, true);
        }
        
        return new int[] { c1, c2};
    }
    
    public void init(int[][] edges) {
        list = new ArrayList<>();
        for(int i = 0; i <= 1_000_000; i++) list.add(new ArrayList<>());
        for(int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        c1 = 0; c2 = 0;
        v1 = new boolean[1_000_001]; v2 = new boolean[1_000_001];
    }
    
    public void bfs(int init, boolean[] visit, boolean isReverse) {
        boolean flag = true;
        
        q = new LinkedList<>();
        q.add(init);
        visit[init] = true;
        
        while(!q.isEmpty()) {
            int x = q.poll();
            int size = list.get(x).size() - 1;
            
            // 검사
            if(x != init && flag) {
                if(isReverse) {
                    if(x % 2 == 0 && size % 2 == 0) flag = false;
                    else if(x % 2 == 1 && size % 2 == 1) flag = false;
                }else{
                    if(x % 2 == 0 && size % 2 == 1) flag = false;
                    else if(x % 2 == 1 && size % 2 == 0) flag = false;
                }
            }
            
            for(int nx : list.get(x)) {
                if(visit[nx]) continue;
                
                q.add(nx);
                visit[nx] = true;
            }
        }
        
        if(flag) {
            if(isReverse) c2++;
            else c1++;
        }
    }
}