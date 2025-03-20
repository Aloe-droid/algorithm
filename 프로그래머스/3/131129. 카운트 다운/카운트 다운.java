import java.util.*;

class Solution {
    int N = 100_000;
    int[] visit, cnt;
    
    public int[] solution(int target) {
        init();
        return new int[] { visit[target], cnt[target] };
    }
    
    public void init() {
        visit = new int[N + 1];
        cnt = new int[N + 1];
        Arrays.fill(visit, 100_000_000);
        visit[0] = 0;
        
        for(int i = 1; i <= N; i++) {
            // 1. 더블로 추가되는 경우
            for(int j = 1; j <= 20; j++) {
                int p = i - j * 2;
                if(p < 0) break;
                if(visit[p] + 1 > visit[i]) continue;
                if(visit[p] + 1 == visit[i] && cnt[p] < cnt[i]) continue;
                visit[i] = visit[p] + 1;
                cnt[i] = cnt[p];
            }
            
            // 2. 트리플로 추가되는 경우 
            for(int j = 1; j <= 20; j++) {
                int p = i - j * 3;
                if(p < 0) break;
                if(visit[p] + 1 > visit[i]) continue;
                if(visit[p] + 1 == visit[i] && cnt[p] < cnt[i]) continue;
                visit[i] = visit[p] + 1;
                cnt[i] = cnt[p];
            }         
            
            // 3. 싱글로 추가되는 경우
            for(int j = 1; j <= 20; j++) {
                int p = i - j;
                if(p < 0) break;
                if(visit[p] + 1 > visit[i]) continue;
                if(visit[p] + 1 == visit[i] && cnt[p] + 1 < cnt[i]) continue;
                visit[i] = visit[p] + 1;
                cnt[i] = cnt[p] + 1;
            }
            
            // 4. 불로 추가되는 경우
            int p = i - 50;
            if(p < 0) continue;
            if(visit[p] + 1 > visit[i]) continue;
            if(visit[p] + 1 == visit[i] && cnt[p] + 1 < cnt[i]) continue;
            visit[i] = visit[p] + 1;
            cnt[i] = cnt[p] + 1;
            
        }
    }
}