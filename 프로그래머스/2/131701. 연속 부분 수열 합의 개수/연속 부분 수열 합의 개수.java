class Solution {
    public int solution(int[] elements) {
        boolean[] bools = new boolean[1000 * 1000 + 1];
        
        for(int i = 0; i < elements.length; i++){
            for(int j = 1; j <= elements.length; j++){
                int sum = 0;
                for(int k = i; k < i + j; k++) sum += elements[k % elements.length];
                bools[sum] = true;
            }
        }
        
        int answer = 0;
        for(boolean bool : bools) if(bool) answer++;
        return answer;
    }
}