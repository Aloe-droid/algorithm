import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0L;
        for(int i : queue1) sum += i;
        for(int i : queue2) sum += i;
        
        if(sum % 2 == 1) return -1;        
        
        sum /= 2;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        Queue<Integer> s1 = new LinkedList<>();
        Queue<Integer> s2 = new LinkedList<>();
        
        for(int i = 0; i < queue1.length; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        
        long sum1 = 0L;
        long sum2 = 0L;
        int count = 0;
        boolean flag = true;
        int maxCnt = 3 * q1.size() - 3;
        
        while(true){
            if(!q1.isEmpty()){
                int k1 = q1.poll();
                s1.add(k1);
                sum1 += k1;
            }
            
            if(!q2.isEmpty()){
                int k2 = q2.poll();
                s2.add(k2);
                sum2 += k2;
            }
            
            if(sum1 > sum){
                count++;
                int k1 = s1.poll();
                q2.add(k1);
                sum1 -= k1;
            }else if(sum2 > sum){
                count++;
                int k2 = s2.poll();
                q1.add(k2);
                sum2 -= k2;
            }
            
            if(sum1 == sum && sum2 == sum) break;
            if(count > maxCnt){
                flag = false;
                break;
            }
        }
        
        return flag ? count : -1;
    }
}