import java.util.*;

class Solution {
    static HashSet<String> hs;
    static int[][] dice;
    static int[] ints;
    static int N;
    static int R;
    static int[] temp;
    static int[] team1;
    static int[] team2;
    static Map<Integer, Integer> map1;
    static Map<Integer, Integer> map2; 
    
    static int maxConf;
    static int tempScore1;
    static int tempScore2;
    static int[] ans;
    
    public int[] solution(int[][] dice) {
        hs = new HashSet<>();
        this.dice = dice;
        N = dice.length;
        R = N / 2;
        maxConf = 0;
        ints = new int[R];
        ans = new int[R];
        combination(0, 0);
        for(int i = 0; i < R; i++) ans[i]++;
        return ans;
    }
    
    public static void combination(int idx, int start){
        if(idx == R){
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            
            for(int i = 0; i < N; i++){
                boolean flag = false;
                for(int j = 0; j < ints.length; j++){
                    if(i == ints[j]){
                        flag = true;
                        break;
                    }
                }
                
                if(flag) sb1.append(i);
                else sb2.append(i);
            }
            String s1 = sb1.toString();
            String s2 = sb2.toString();
            
            if(hs.contains(s1) || hs.contains(s2)) return;
            hs.add(s1);
            hs.add(s2);
            divideTeam(s1, s2);
            return;
        }
        
        for(int i = start; i < N; i++){
            ints[idx] = i;
            combination(idx + 1, i + 1);
        }
    }
    
    public static void divideTeam(String s1, String s2){
        team1 = new int[R];
        team2 = new int[R];
        
        for(int i = 0; i < R; i++){
            team1[i] = s1.charAt(i) - '0';
            team2[i] = s2.charAt(i) - '0';
        }
        
        temp = new int[R];
        tempScore1 = 0;
        tempScore2 = 0;
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        
        dfs(0);
        check();
    }
    
    public static void dfs(int idx){
        if(idx == R){
            addValue();
            return;
        }
        
        for(int i = 0; i < 6; i++){
            temp[idx] = i;
            dfs(idx + 1);
        }
    }
    
    public static void addValue(){
        int s1 = 0;
        int s2 = 0;
        for(int i = 0 ; i < R; i++){
            s1 += dice[team1[i]][temp[i]];
            s2 += dice[team2[i]][temp[i]];
        }
        
        map1.put(s1, map1.getOrDefault(s1, 0) + 1);
        map2.put(s2, map2.getOrDefault(s2, 0) + 1);
    }
    
    public static void check(){
        int cnt1 = 0;
        int cnt2 = 0;
        
        for(int key1 : map1.keySet()){
            for(int key2: map2.keySet()){
                if(key1 > key2) cnt1 += map1.get(key1) * map2.get(key2);
                if(key1 < key2) cnt2 += map1.get(key1) * map2.get(key2);
            }
        }
        
        if(cnt1 > maxConf){
            maxConf = cnt1;
            ans = team1;
        }
        
        if(cnt2 > maxConf){
            maxConf = cnt2;
            ans = team2;
        }
    }
}