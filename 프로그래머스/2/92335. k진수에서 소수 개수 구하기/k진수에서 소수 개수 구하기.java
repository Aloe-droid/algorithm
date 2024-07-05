import java.util.*;
import java.math.*;

class Solution {
    public int solution(int n, int k)throws Exception {
        String[] strings = Integer.toString(n, k).split("[0]+");
        int ans = 0;
        
        for(String s : strings){
            BigInteger bi = new BigInteger(s);
            if(bi.isProbablePrime(10)) ans++;
        }
        
        return ans;
    }
}