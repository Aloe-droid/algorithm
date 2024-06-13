class Solution {
    public int[] solution(String s) {
        int[] ans = new int[2];
        
        while(!s.equals("1")){
            int k = 0; int len = s.length();
            for(int i = 0; i < len; i++) if(s.charAt(i) == '1') k++;
            
            ans[1] += len - k;
            ans[0]++;
            s = Integer.toString(k, 2);
        }
        return ans;
    }
}