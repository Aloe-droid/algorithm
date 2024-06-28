using System;

public class Solution {
    public int solution(int n) {
        n--;
        for(int i = 2; i <= (int) Math.Sqrt(n); i++){
            if(n % i == 0) return i;
        }
        return n;
    }
}