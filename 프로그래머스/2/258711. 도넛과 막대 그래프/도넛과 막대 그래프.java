import java.util.*;

class Solution {
    static int[] inDegrees;
    static int[] outDegrees;
    static boolean[] check;
    static List<List<Integer>> list;
    static Queue<Integer> q;
    static HashSet<Integer> hs;
    static int[] ans;
    
    public int[] solution(int[][] edges) {
        ans = new int[4];
        list = new ArrayList<>();
        int max = 1;
        for(int[] edge: edges) for(int i : edge) max = Math.max(max, i);
        inDegrees = new int[max + 1];
        outDegrees = new int[max + 1];
        check = new boolean[max + 1];
        for(int i = 0; i <= max; i++) list.add(new ArrayList<>());
        int start = -1;
        for(int i = 0; i < edges.length; i++){
            int x = edges[i][0];
            int y = edges[i][1];
            list.get(x).add(y);
            list.get(y).add(x);
            inDegrees[y]++;
            outDegrees[x]++;
        }
        
        for(int i = 1; i <= max; i++){
            if(outDegrees[i] - inDegrees[i] > 1){
                start = i;
                break;
            }
        }
        
        ans[0] = start;
        check[start] = true;
        for(int nx : list.get(start)){
            inDegrees[nx]--;
            bfs(nx);
        }
  
        return ans;
    }
    
    public static void bfs(int num){
        q = new LinkedList<>();
        hs = new HashSet<>();
        q.add(num);
        check[num] = true;

        while(!q.isEmpty()){
            int x = q.poll();
            check[x] = true;
            hs.add(x);
            
            for(int nx : list.get(x)){
                if(check[nx]) continue;
                
                q.add(nx);
                hs.add(nx);
                check[nx] = true;
            }
        }
        
        for(int k : hs){
            int i1 = inDegrees[k];
            int i2 = outDegrees[k];
            
            if(i1 >= 2 || i2 >= 2){
                ans[3]++;
                return;
            }
            
            if(i1 == 0 || i2 == 0){
                ans[2]++;
                return;
            }
        }
        
        ans[1]++;
    }
}