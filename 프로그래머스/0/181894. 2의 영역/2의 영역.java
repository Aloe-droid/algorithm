class Solution {
    public int[] solution(int[] arr) {
        int first = -1;
        int last = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 2) continue;
            if(first == -1) first = i;
            last = i;
        }
        
        if(first == -1) return new int[] { -1 };
        
        int[] ans = new int[last - first + 1];
        for(int i = first; i <= last; i++) ans[i - first] = arr[i];
        return ans;
    }
}