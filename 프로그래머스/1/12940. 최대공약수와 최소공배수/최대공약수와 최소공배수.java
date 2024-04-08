import java.math.*;

class Solution {
    public int[] solution(int n, int m) {
        BigInteger b1 = BigInteger.valueOf(n);
        BigInteger b2 = BigInteger.valueOf(m);
        int i1 = b1.gcd(b2).intValue();
        int i2 = n * m / i1;
        return new int[] {i1, i2};
    }
}