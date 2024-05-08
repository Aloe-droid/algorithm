import java.util.*;

class Solution {
    static List<List<Integer>> list;
    static int[] dir, cnt, target, answer;
    static List<Integer> dirList;
    static boolean flag = false;
    static HashMap<Integer, Integer> cntMap;
    
    public int[] solution(int[][] edges, int[] target) {
        Solution.target = new int[target.length + 1];
        System.arraycopy(target, 0, Solution.target, 1, target.length);
        cnt = new int[target.length + 1];
        dir = new int[target.length + 1];
        list = new ArrayList<>();
        dirList = new ArrayList<>();
        cntMap = new HashMap<>();
        
        for (int i = 0; i < dir.length; i++) list.add(new ArrayList<>());
        for (int[] edge : edges) list.get(edge[0]).add(edge[1]);
        for (int i = 0; i < dir.length; i++) Collections.sort(list.get(i));

        find();
        return flag ? answer : new int[] {-1};
    }

    public static void find() {
        while (true) {
            int k = 1;
            while (!list.get(k).isEmpty()) {
                int n = list.get(k).get(dir[k]);
                dir[k]++;
                if (dir[k] >= list.get(k).size()) dir[k] = 0;
                k = n;
            }

            if (cnt[k] + 1 <= target[k]) {
                cnt[k]++;
                dirList.add(k);
                cntMap.put(k, cntMap.getOrDefault(k, 0) + 1);
                if(check()){
                    flag = true;
                    break;
                }
            } else {
                break;
            }
        }
        
        if(flag) sum();
    }
    
    public static void sum(){
        answer = new int[dirList.size()];
        
        for(int key : cntMap.keySet()){
            int[] temp = new int[cntMap.get(key)];
            for(int i = 0; i < temp.length; i++) temp[i]++;
            int ans = target[key];
            ans -= temp.length;
            
            int idx = temp.length - 1;
            while(ans > 0){
                if(idx < 0){
                    flag = false;
                    break;
                }
                
                if(temp[idx] >= 3) idx--;
                else {
                    temp[idx]++;
                    ans--;
                }
            }
            
            idx = 0;
            for(int i = 0; i < dirList.size(); i++){
                if(dirList.get(i) == key){
                    answer[i] = temp[idx];
                    idx++;
                }
            }
        }
    }
    
    public static boolean check() {
        for (int i = 1; i < cnt.length; i++) {
            if(cnt[i] <= target[i] && cnt[i] * 3 >= target[i]) continue;
            else return false;
        }
        return true;
    }
}