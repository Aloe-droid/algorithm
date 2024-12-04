import java.util.*;

class Solution {
    static int[] picks, mins;
    static int min = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        Solution.picks = picks;
        mins = changeMinerals(minerals);
        
        for(int i = 0; i < 3; i++){
            if(picks[i] == 0) continue;
            picks[i]--;
            dfs(0, 0, i, 0);
            picks[i]++;
        }
        
        return min;
    }
    
    // 미네랄 인덱스, 곡괭이 쓴 횟수, 지금 곡괭이, 피로도
    public static void dfs(int mineralIndex, int pickCount, int pick, int value){
        if(mineralIndex == mins.length){
            min = Math.min(min, value);
            return;
        }
        
        // 곡괭이 5번 이하로 쓴 경우 
        if(pickCount < 5){
            int mine = mins[mineralIndex];
            int plusValue = getValue(pick, mine);
            dfs(mineralIndex + 1, pickCount + 1, pick, value + plusValue);
            return;
        }
        
        boolean flag = true;
        for(int i = 0; i < 3; i++){
            if(picks[i] == 0) continue;
            flag = false;
            
            picks[i]--;
            dfs(mineralIndex, 0, i, value);
            picks[i]++;
        }
        
        // 남은 곡괭이가 없는 경우
        if(flag){
            min = Math.min(min, value);
            return;
        }
    }
    
    // 내 곡괭이, 선택된 광물
    public static int getValue(int pick, int mine){
        if(pick <= mine) return 1;
        else if(pick == 2 && mine == 0) return 25;
        else return 5;
    }
    
    public static int[] changeMinerals(String[] minerals){
        int[] ints = new int[minerals.length];
        for(int i = 0; i < minerals.length; i++){
            String s = minerals[i];
            if(s.equals("diamond")) ints[i] = 0;
            else if(s.equals("iron")) ints[i] = 1;
            else ints[i] = 2;
        }
        return ints;
    }
}