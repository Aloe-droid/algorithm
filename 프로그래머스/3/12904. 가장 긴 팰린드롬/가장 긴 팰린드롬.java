class Solution{
    public int solution(String s) {
        int N = s.length();
        int max = 1;
        
        // 홀수 팰린드롬
        for(int mid = 0; mid < N; mid++) {
            int left = mid, right = mid;
            while(left >= 0 && right < N && s.charAt(left) == s.charAt(right)) {
                left -= 1;
                right += 1;
            }
            max = Math.max(max, right - left - 1);
        }
        
        // 짝수 팰린드롬
        for(int mid = 0; mid < N; mid++) {
            int left = mid, right = mid + 1;
            while(left >= 0 && right < N && s.charAt(left) == s.charAt(right)) {
                left -= 1;
                right += 1;
            }
            max = Math.max(max, right - left - 1);
        }
        
        return max;
    }
}
