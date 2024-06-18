import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> ts = new TreeSet<>();
        for(String oper : operations){
            StringTokenizer st = new StringTokenizer(oper);
            String op = st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            if(op.equals("I")) ts.add(k);
            else if(k == 1 && !ts.isEmpty()) ts.pollLast();
            else if(k == -1 && !ts.isEmpty()) ts.pollFirst();
        }
        
        return ts.isEmpty() ? new int[] {0, 0} : new int[] {ts.last(), ts.first()};
    }
}