class Solution {
    public int solution(int[] num_list) {
        int ans = 0;
        for(int k : num_list){
            while(k != 1){
                if(k % 2 == 0) k /= 2;
                else k = (k - 1) / 2;
                ans++;
            }
        }
        return ans;
    }
}