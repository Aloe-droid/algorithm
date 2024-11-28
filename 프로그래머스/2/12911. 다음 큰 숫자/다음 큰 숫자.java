class Solution {
    public int solution(int n) {
        String s = Integer.toString(n, 2);
        int cnt = 0;
        int k = 1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1') cnt++;
        }
        
        while(true){
            String temp = Integer.toString(n + k, 2);
            int count = 0;
            for(int i = 0; i < temp.length(); i++){
                if(temp.charAt(i) == '1') count++;
            }
            
            if(count == cnt){
                return n + k;
            }
            
            k++;
        }
    }
}