using System;

public class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[right - left + 1];
        for(long i = left; i <= right; i++){
            long x = i / n;
            long y = i % n;
            answer[i - left] = (int) Math.Max(x, y) + 1;
        }
        return answer;
    }
}