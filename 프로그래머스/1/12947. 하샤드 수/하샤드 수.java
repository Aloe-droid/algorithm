class Solution {
    public boolean solution(int x) {
        int div = 0;
        char[] chars = Integer.toString(x).toCharArray();
        for(char c : chars) div += c - '0';
        if(x % div == 0) return true;
        else return false;
    }
}