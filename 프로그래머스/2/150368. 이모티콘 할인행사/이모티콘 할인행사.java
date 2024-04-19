import java.util.*;

class Solution {
    static int N;
    static int[][] users;
    static int[] emoticons, ans;
    
    public int[] solution(int[][] users, int[] emoticons) {
        Solution.N = emoticons.length;
        Solution.users = users;
        Solution.emoticons = emoticons;
        ans = new int[2];
        dfs(0, new int[N]);
        return ans;
    }
    
    public static void dfs(int idx, int[] discount){
        if(idx == N){
            check(discount);
            return;
        }
        
        for(int i = 10; i <= 40; i += 10){
            discount[idx] = i;
            dfs(idx + 1, discount);
        }
    }
    
    public static void check(int[] discount){
        int plus = 0;
        int money = 0;
        
        for(int i = 0; i < users.length; i++){
            int temp = 0;
            for(int j = 0; j < emoticons.length; j++){
                if(discount[j] >= users[i][0]){
                    temp += emoticons[j] / 100 * (100 - discount[j]);
                }
            }
            if(temp >= users[i][1]) plus++;
            else money += temp;
        }
        
        if(plus > ans[0]){
            ans = new int[] {plus, money};
        }else if(plus == ans[0] && money > ans[1]){
            ans[1] = money;
        }
    }
}