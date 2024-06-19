class Solution {
    public long[] solution(long[] numbers) {
        long[] ans = new long[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            String s = Long.toString(numbers[i], 2);
            s = "0" + s;
            StringBuilder sb = new StringBuilder(s);
            int idx = -1;
            for(int j = 0; j < s.length(); j++) if(s.charAt(j) - '0' == 0) idx = j;
            sb.replace(idx, idx + 1, "1");
            if(idx + 1 < sb.length()) sb.replace(idx + 1, idx + 2, "0");
            if(idx + 2 < sb.length()) sb.replace(idx + 2, sb.length(), s.substring(idx + 2));
            ans[i] = Long.parseLong(sb.toString(), 2);
        }
        return ans;
    }
}