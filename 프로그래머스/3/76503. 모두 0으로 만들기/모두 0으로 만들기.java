import java.util.*;

class Solution {
    public long solution(int[] A, int[][] edges){
        long sum = 0L;
        for(int i : A) sum += i;
        if(sum != 0) return -1;
        
        long[] a = new long[A.length];
        for(int i = 0; i < A.length; i++) a[i] = (long) A[i];
        
        List<List<Integer>> list = new ArrayList<>();
        boolean[] check = new boolean[a.length];
        int[] lines = new int[a.length];
        
        for(int i = 0; i < a.length; i++) list.add(new ArrayList<>());
        for(int[] edge : edges){
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
            lines[edge[0]]++;
            lines[edge[1]]++;
        }
        long cnt = 0L;
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < a.length; i++) if(list.get(i).size() == 1) q.add(i);
        
        while(!q.isEmpty()){
            int x = q.poll();
            check[x] = true;
            long need = a[x];
            
            for(int nx : list.get(x)){
                if(check[nx]) continue;
                
                a[nx] += need;
                a[x] -= need;
                cnt += Math.abs(need);
                
                lines[nx]--;
                if(lines[nx] == 1) q.add(nx);
            }
        }
        return cnt;
    }
}