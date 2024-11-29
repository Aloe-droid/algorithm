import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (arr1, arr2) -> {
            int a1 = arr1[col-1];
            int a2 = arr2[col-1];
            int a3 = arr1[0];
            int a4 = arr2[0];
            
            if(a1 < a2) return -1;
            else if(a1 > a2) return 1;
            else return a4 - a3;
        });
        
        int[] ints = new int[data.length];
        for(int i = 0; i < data.length; i++){
            int s_i = 0;
            for(int j = 0; j < data[i].length; j++){
                s_i += data[i][j] % (i + 1);
            }
            ints[i] = s_i;
        }
        
        int k = ints[row_begin - 1];
        for(int i = row_begin + 1; i <= row_end; i++){
            k ^= ints[i - 1];
        }
        
        return k;
    }
}