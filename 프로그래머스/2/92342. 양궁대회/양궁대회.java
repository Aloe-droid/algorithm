import java.util.*;

class Solution {
    static int maxDiff, N;
    static int[] ints, infs;
    
    public int[] solution(int n, int[] info) {
        ints = new int[info.length];
        infs = info;
        maxDiff = 0;
        N = n;
        
        dfs(0, n, new int[info.length]);
        return maxDiff == 0 ? new int[] {-1} : ints;
    }
    
    public static void dfs(int idx, int re, int[] points){
        if(idx == points.length){
            // 만약 어피치의 합이 총합이 안되면 차만큼 0점짜리 추가
            int sum = 0;
            for(int point : points) sum += point;
            boolean flag = false;
            if(sum < N) flag  = true;

            if(flag) points[points.length - 1] += N - sum;
            check(points);
            if(flag) points[points.length - 1] -= N - sum;
            
            return;
        }
        
        int p = infs[idx];
        
        // 어피치 보다 높은 점수를 얻고 다음으로 이동 
        if(re >= p + 1){
            re -= p + 1;
            points[idx] += p + 1;
            dfs(idx + 1, re, points);
            points[idx] -= p + 1;
            re += p + 1;
        }
        
        // 이번 점수를 어피치에게 주고 다음으로 이동
        dfs(idx + 1, re, points);
    }
    
    public static void check(int[] points){
        int p1 = 0;
        int p2 = 0;
        
        for(int i = 0; i < points.length; i++){
            if(points[i] == infs[i] && points[i] == 0) continue;
            else if(points[i] > infs[i]) p1 += 10 - i;
            else p2 += 10 - i;
        }
        
        int diff = p1 - p2;
        if(diff < maxDiff) return;
        
        if(diff > maxDiff){
            maxDiff = diff;
            System.arraycopy(points, 0, ints, 0, ints.length);
        }else{
            boolean flag = false;
            for(int i = points.length - 1; i >= 0; i--){
                if(points[i] == ints[i]) continue;
                else{
                    if(points[i] > ints[i]) flag = true;
                    break;
                }
            }
            
            if(flag) System.arraycopy(points, 0, ints, 0, ints.length);
        }
    }
}