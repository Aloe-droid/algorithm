class Solution {
    public int solution(int[] elements) {
        boolean[] bools = new boolean[1000 * 1000 + 1];
        
        for(int i = 0; i < elements.length; i++){
            int sum = 0;
            for(int j = i; j < i + elements.length; j++){
                sum += elements[j % elements.length];
                bools[sum] = true;
            }
        }
        
        int answer = 0;
        for(boolean bool : bools) if(bool) answer++;
        return answer;
    }
}