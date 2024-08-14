import java.util.*;

class Solution {
    static List<List<Integer>> list;
    static Set<String> dup;
    static int[] info;
    static int[] heads;
    static int max = 0;
    
    public int solution(int[] info, int[][] edges) {
        Solution.info = info;
        heads = new int[info.length];
        list = new ArrayList<>();
        dup = new HashSet<>();
        
        for(int i = 0; i < info.length; i++) list.add(new ArrayList<>());
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            list.get(x).add(y);
            heads[y] = x;
        }
        
        Set<Integer> sheep = new HashSet<>();
        Set<Integer> wolf = new HashSet<>();
        sheep.add(0);
        
        SW sw = new SW(sheep, wolf);
        String s = makeString(0, sw);
        dup.add(s);
        dfs(0, sw);
        return max;
    }
    
    public static void dfs(int idx, SW sw){
       if(idx == 0 && sw.sheep.size() > max) max = sw.sheep.size();
       
        Set<Integer> sheep = sw.sheep;
        Set<Integer> wolf = sw.wolf;
        
        for(int next : list.get(idx)){
            Set<Integer> nextSheep = new HashSet<>(sheep);
            Set<Integer> nextWolf = new HashSet<>(wolf);
            
            if(info[next] == 0) nextSheep.add(next);
            else nextWolf.add(next);
            if(nextWolf.size() >= nextSheep.size()) continue;
            
            SW nextSw = new SW(nextSheep, nextWolf);
            String s = makeString(next, nextSw);
            if(dup.contains(s)) continue;
            
            dup.add(s);
            dfs(next, nextSw);
        }
        
        if(idx != 0) dfs(heads[idx], sw);
    }
    
    public static String makeString(int idx, SW sw){
        return idx + "," + sw.sheep.toString() + "," + sw.wolf.toString();
    }
}

class SW {
    Set<Integer> sheep;
    Set<Integer> wolf;
    
    public SW(Set<Integer> sheep, Set<Integer> wolf){
        this.sheep = sheep;
        this.wolf = wolf;
    }
}