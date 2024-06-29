using System;

public class Solution {
    public int[] solution(int[] num, int n) {
        int[] ans = new int[num.Length - n + 1];
        for(int i = n - 1; i < num.Length; i++) ans[i - n + 1] = num[i];
        return ans;
    }
}