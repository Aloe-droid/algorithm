import java.util.*;

class Solution {
    public int solution(String binomial) {
        StringTokenizer st = new StringTokenizer(binomial);
        int i1 = Integer.parseInt(st.nextToken());
        char op = st.nextToken().charAt(0);
        int i2 = Integer.parseInt(st.nextToken());
        
        if(op == '+') return i1 + i2;
        else if(op == '-') return i1 - i2;
        else return i1 * i2;
    }
}