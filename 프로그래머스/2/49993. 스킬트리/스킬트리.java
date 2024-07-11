import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] ints = new int[26];
        List<Integer> skList = new ArrayList<>();
        for(int i = 0; i < skill.length(); i++){
            int k = skill.charAt(i) - 'A';
            ints[k]++;
            skList.add(k);
        }

        for(String s : skill_trees){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < s.length(); i++){
                int k = s.charAt(i) - 'A';
                if(ints[k] == 0 || list.contains(k)) continue;
                list.add(k);
            }
            
            boolean flag = true;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) != skList.get(i)) flag = false;
            }
            
            if(flag) answer++;
        }
        
        return answer;
    }
}