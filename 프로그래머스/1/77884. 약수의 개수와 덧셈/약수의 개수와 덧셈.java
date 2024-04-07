import java.util.*;
class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i = left; i <= right; i++){
            int cnt = count(i);
            System.out.println(cnt);
            if(cnt % 2 == 0) answer += i;
            else answer -= i;
        }
        return answer;
    }
    
    public int count(int x){
        int cnt = 0;
        for(int i = (int) Math.sqrt(x); i >= 1; i--){
            if(x % i == 0){
                if(i * i == x) cnt++;
                else cnt += 2;
            }
        }
        return cnt;
    }
}