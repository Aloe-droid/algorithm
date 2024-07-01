import java.math.*;

class Solution {
    public int solution(int[] arr) {
        int ans = arr[0];
        for(int i = 1; i < arr.length; i++) ans = find(ans, arr[i]);
        return ans;
    }
    
    public static int find(int x, int y){
        BigInteger bx = BigInteger.valueOf(x);
        BigInteger by = BigInteger.valueOf(y);
        BigInteger g = bx.gcd(by);
        return x * y / g.intValue();
    }
}