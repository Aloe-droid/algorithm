class Solution {
    public int solution(int[] cookie) {
        int max = 0, N = cookie.length;
        for(int mid = 0; mid < N - 1; mid++) {
            boolean[] visit = new boolean[N];
            int i1 = mid;
            int i2 = mid + 1;
            
            int l = 0, r = 0;
            while(i1 >= 0 && i2 < N) {
                l += visit[i1] ? 0 : cookie[i1];
                r += visit[i2] ? 0 : cookie[i2];
                visit[i1] = true; visit[i2] = true;
                if(l == r) max = Math.max(max, l);
                
                if(l > r) i2++;
                else i1--;
            }
        }
        return max;
    }
}