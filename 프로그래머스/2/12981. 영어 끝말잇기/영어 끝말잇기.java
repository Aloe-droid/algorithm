import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> hs = new HashSet<>();
        char c = words[0].charAt(words[0].length() - 1);
        hs.add(words[0]);
        
        for(int i = 1; i < words.length; i++){
            String word = words[i];
            if(word.charAt(0) != c || hs.contains(word)) return new int[] { i % n + 1 , i / n + 1};
            c = word.charAt(word.length() - 1);
            hs.add(word);
        }
        return new int[] {0, 0};
    }
}