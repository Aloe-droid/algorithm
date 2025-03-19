class Solution {
    public long solution(int[] sequence) {
        long v1 = find1(sequence, 1);
        long v2 = find2(sequence, -1);
        return Math.max(v1, v2);
    }
    
    public long find1(int[] prev, int op) {
        int[] ints = new int[prev.length];
        for(int i = 0; i < prev.length; i++) {
            ints[i] = op * prev[i];
            op *= -1;
        }
        
        long max = ints[0];
        long sum = ints[0];
        for(int i = 1; i < prev.length; i++) {
            sum = Math.max(sum + ints[i], ints[i]);
            max = Math.max(max, sum);
        }
        return max;
    }
    
    public long find2(int[] prev, int op) {
        int[] ints = new int[prev.length];
        for(int i = 0; i < prev.length; i++) {
            ints[i] = op * prev[i];
            op *= -1;
        }
        
        long max = ints[0];
        long sum = ints[0];
        for(int i = 1; i < prev.length; i++) {
            sum = Math.max(sum + ints[i], ints[i]);
            max = Math.max(max, sum);
        }
        return max;
    }
}