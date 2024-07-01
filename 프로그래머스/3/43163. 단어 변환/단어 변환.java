class Solution {
    static boolean[] check;
    static String[] words;
    static String target;
    static int ans = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        boolean flag = false;
        for(String word : words) if(word.equals(target)) flag = true;
        if(!flag) return 0;
        
        check = new boolean[words.length];
        Solution.words = words;
        Solution.target = target;
        dfs(begin, 0);
        return ans;
    }
    
    public static void dfs(String s, int cnt){
        if(cnt >= ans) return;
        if(s.equals(target)){
            ans = cnt;
            return;
        }
        
        for(int i = 0; i < words.length; i++){
            if(check[i]) continue;
            
            int c = count(s, words[i]);
            if(c == 1){
                check[i] = true;
                dfs(words[i], cnt + 1);
                check[i] = false;
            }
        }
    }
    
    public static int count(String s1, String s2){
        int cnt = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) cnt++;
        }
        return cnt;
    }
}