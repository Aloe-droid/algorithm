class Solution {
    public int solution(int[] ii) {
        for(int i = 0; i < ii.length; i++) if(ii[i] < 0) return i;
        return -1;
    }
}