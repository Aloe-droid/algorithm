import java.util.*;
class Solution {
    static int N = 24 * 60 * 60 * 1000;
    
    public int solution(String[] lines) {
        int[][] ints = convertAll(lines);
        int max = 0;
        for(int i = 0; i < lines.length; i++) {
            int s1 = ints[i][0];
            int e1 = s1 + 999;
            int c1 = 0;
            
            int s2 = ints[i][1];
            int e2 = s2 + 999;
            int c2 = 0;
            
            for(int j = 0; j < lines.length; j++) {
                int t1 = ints[j][0];
                int t2 = ints[j][1];
                
                if(s1 <= t1 && e1 >= t1) c1++;
                else if(s1 <= t2 && e1 >= t2) c1++;
                else if(s1 >= t1 && e1 <= t2) c1++;
                
                if(s2 <= t1 && e2 >= t1) c2++;
                else if(s2 <= t2 && e2 >= t2) c2++;
                else if(s2 >= t2 && e2 <= t2) c2++;
            }
            int c = Math.max(c1, c2);
            max = Math.max(max, c);
        }
        return max;
    }
    
    public static int[][] convertAll(String[] lines) {
        int[][] ints = new int[lines.length][2];
        for(int i = 0; i < lines.length; i++) ints[i] = convert(lines[i]);
        return ints;
    }
    
    public static int[] convert(String str) {
        String[] ss = str.split(" ");
        String[] tt = ss[1].split(":");
        int h = Integer.parseInt(tt[0]) * 60 * 60 * 1000;
        int m = Integer.parseInt(tt[1]) * 60 * 1000;
        int s = (int) (Double.parseDouble(tt[2]) * 1000);
        int end = h + m + s;
        if(end > N) end = N;
        
        int t = (int) (Double.parseDouble(ss[2].split("s")[0]) * 1000) - 1;
        int start = end - t;
        if(start < 0) start = 0;
        return new int[] {start, end};
    }
}