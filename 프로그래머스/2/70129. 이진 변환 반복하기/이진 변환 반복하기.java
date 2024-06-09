class Solution {
    public int[] solution(String s) {
        int[] ans = new int[2];
        
        while(!s.equals("1")){
            int k = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1') k++;
            }
            
            ans[1] += s.length() - k;
            ans[0]++;
            s = Integer.toString(k, 2);
        }
        
        return ans;
    }
}