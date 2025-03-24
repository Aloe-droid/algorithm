class Solution {
    public int solution(int n, int w, int num) {
        int[][] box = new int[n / w + 1][w];
        
        boolean flag = true;
        int v = 0;
        int col = -1;
        for(int i = box.length - 1; i >= 0; i--) {
            if(flag) {
                for(int j = 0; j < w; j++) {
                    box[i][j] = ++v;
                    if(box[i][j] == num) col = j;
                }
            }else{
                for(int j = w - 1; j >= 0; j--) {
                    box[i][j] = ++v;
                    if(box[i][j] == num) col = j;
                }
            }
            
            flag = !flag;
        }
        
        int cnt = 0;
        for(int i = 0; i < box.length; i++) {
            if(box[i][col] >= num && box[i][col] <= n) cnt++;
        }
        return cnt;        
    }
}