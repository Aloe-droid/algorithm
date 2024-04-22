import java.util.*;

class Solution {
    static boolean flag;
    static List<String> list;
    
    public int[] solution(long[] numbers) {
        int[] ans = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            list = new ArrayList<>();
            flag = true;
            
            String num = Long.toBinaryString(numbers[i]);
            // if(num.length() % 2 == 0) list.add("0");
            int add = 0;
            int size = 1;
            int plus = 2;
            while(true){
                if(size == num.length()) break;
                if(size > num.length()){
                    add = size - num.length();
                    break;
                }
                
                size += plus;
                plus *= 2;
            }
            
            for(int j = 0; j < add; j++) list.add("0");
            for(int j = 0; j < num.length(); j++) list.add(num.substring(j, j + 1));
            int mid = list.size() / 2;
            boolean bool = list.get(mid).equals("1")? true : false;
            
            dfs(mid, (mid + 1) / 2, bool);
            ans[i] = flag? 1 : 0;
        }
        return ans;
    }
    
    public static void dfs(int idx, int diff, boolean isZero){
        if(idx >= list.size() || diff == 0 || !flag) return;
        
        int left = idx - diff;
        int right = idx + diff;
        boolean bool = list.get(idx).equals("1")? true : false;
        if(!bool && (list.get(left).equals("1") || list.get(right).equals("1"))){
            flag = false;
            return;
        }
        
        dfs(left, diff/2, bool);
        dfs(right, diff/2, bool);
    }
}