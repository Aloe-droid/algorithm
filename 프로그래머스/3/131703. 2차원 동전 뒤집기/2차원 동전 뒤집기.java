import java.util.*;

class Solution {
    int N, M, min = Integer.MAX_VALUE;
    int[][] ints, target;
    
    public int solution(int[][] beginning, int[][] target) {
        ints = beginning;
        this.target = target;
        N = target.length;
        M = target[0].length;

        rowFlap(0, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    public void rowFlap(int start, int idx, int count) {
        if(count > min) return;
        colFlap(0, 0, count);
        
        for(int i = start; i < N; i++) {
            // i 행 뒤집기
            for(int k = 0; k < M; k++) ints[i][k] = ints[i][k] == 0 ? 1 : 0;
            rowFlap(i + 1, idx + 1, count + 1);
            // i 행 원복 
            for(int k = 0; k < M; k++) ints[i][k] = ints[i][k] == 0 ? 1 : 0;
        }
    }
    
    public void colFlap(int start, int idx, int count) {
        if(count > min) return;
        check(count);
        
        for(int i = start; i < M; i++) {
            // i 열 뒤집기
            for(int k = 0; k < N; k++) ints[k][i] = ints[k][i] == 0 ? 1 : 0;
            colFlap(i + 1, idx + 1, count + 1);
            // i 열 원복
            for(int k = 0; k < N; k++) ints[k][i] = ints[k][i] == 0 ? 1 : 0;
        }
    }
    
    public void check(int count) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(ints[i][j] != target[i][j]) return;
            }
        }
        min = Math.min(min, count);
    }
}