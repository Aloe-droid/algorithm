class Solution {
    static int[] ans;
    static int[][] arr;
    
    public int[] solution(int[][] arr) {
        ans = new int[2];
        Solution.arr = arr;
        dfs(0, 0, arr.length);
        return ans;
    }
    
    public static void dfs(int x, int y, int len){
        boolean flag = true;
        
        int prev = arr[x][y];        
        for(int i = x; i < x + len; i++){
            for(int j = y; j < y + len; j++){
                if(arr[i][j] != prev) flag = false;
            }
        }
        
        if(flag){
            ans[prev]++;
            return;
        }else{
            len /= 2;
            dfs(x, y, len);
            dfs(x + len, y, len);
            dfs(x, y + len, len);
            dfs(x + len, y + len, len);
        }
    }
}