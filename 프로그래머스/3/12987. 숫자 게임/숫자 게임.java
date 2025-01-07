import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int N = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int cnt = 0;
        int idx = 0;
        for(int a : A) {
            for(int k = idx; k < N; k++) {
                if(B[k] > a) {
                    idx = k + 1;
                    cnt += 1;
                    break;
                }
            }
        }
        return cnt;
    }
}