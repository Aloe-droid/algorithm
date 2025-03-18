class Solution {
    public int solution(int[] cookie) {
        int max = 0, N = cookie.length;
        for(int mid = 0; mid < N - 1; mid++) {
            int i1 = mid, i2 = mid + 1;
            int l = cookie[i1], r = cookie[i2];
            while(i1 >= 0 && i2 < N) {
                if(l == r) max = Math.max(max, l);
                
                if(l > r && i2 + 1 < N) r += cookie[++i2];
                else if(i1 - 1 >= 0) l += cookie[--i1];
                else break;
            }
        }
        return max;
    }
}