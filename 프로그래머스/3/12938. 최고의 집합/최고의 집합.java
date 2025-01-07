import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[] { -1 };
        
        int[] ints = new int[n];
        Arrays.fill(ints, s / n);
        int k = s % n; 
        for(int i = ints.length - 1; i >= 0; i--) {
            if(k > 0) {
                ints[i] += 1;
                k -= 1;
            }
            else break;
        }
        
        return ints;
    }
}