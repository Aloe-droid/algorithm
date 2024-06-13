import java.util.*;

class Solution {
    static List<List<Integer>> list;
    static int v1, v2, v3, n;
    static Queue<Integer> q;
    static int[] check;
    
    public int solution(int n, int[][] edges) {
        v1 = -1; v2 = - 1; v3 = -1;
        Solution.n = n;
        list = new ArrayList<>();
        for(int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for(int[] edge : edges){
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        int[] ans = new int[3];
        // 임의의 한 점에서 가장 먼 거리에 있는 노드 
        bfs(1);
        v1 = find(1);
        
        bfs(v1);
        v2 = find(v1);
        ans[0] = check[v2] - 1;
        
        bfs(v2);
        v3 = find(v1);
        ans[1] = check[v3] - 1;
        
        bfs(v3);
        ans[2] = check[v1] - 1;
        
        Arrays.sort(ans);
        return ans[1];
    }
    
    public static void bfs(int start){
        q = new LinkedList<>();
        check = new int[n + 1];
        q.add(start);
        check[start] = 1;
        
        while(!q.isEmpty()){
            int x = q.poll();
            for(int nx : list.get(x)){
                if(check[nx] != 0) continue;
                q.add(nx);
                check[nx] = check[x] + 1;
            }
        }
    }
    
    public static int find(int k){
        int idx = -1; int max = -1;
        for(int i = 0; i < check.length; i++){
            if(i == k) continue;
            
            if(max < check[i]){
                idx = i;
                max = check[i];
            }
        }
        return idx;
    }
}