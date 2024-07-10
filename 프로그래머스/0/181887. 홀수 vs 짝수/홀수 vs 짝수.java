class Solution {
    public int solution(int[] num) {
        int i1 = 0;
        int i2 = 0;
        for(int i = 0; i < num.length; i++){
            if(i % 2 == 1) i1 += num[i];
            else i2 += num[i];
        }
        
        return i1 > i2 ? i1 : i2;
    }
}