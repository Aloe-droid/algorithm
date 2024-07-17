class Solution {
    public int solution(int[] num_list) {
        if(num_list.length >= 11){
            int ans = 0;
            for(int num : num_list) ans += num;
            return ans;
        }else{
            int ans = 1;
            for(int num : num_list) ans *= num;
            return ans;
        }
    }
}