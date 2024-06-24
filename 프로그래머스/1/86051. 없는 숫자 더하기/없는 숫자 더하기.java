class Solution {
    public int solution(int[] numbers) {
        boolean[] check = new boolean[10];
        for(int i : numbers) check[i] = true;
        
        int ans = 0;
        for(int i = 0; i < 10; i++) if(!check[i]) ans += i;
        return ans;
    }
}