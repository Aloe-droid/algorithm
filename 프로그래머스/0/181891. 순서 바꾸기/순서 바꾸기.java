class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] ans = new int[num_list.length];
        for(int i = n; i < num_list.length; i++) ans[i - n] = num_list[i];
        for(int i = 0; i < n; i++) ans[i + num_list.length - n] = num_list[i];
        return ans;
    }
}