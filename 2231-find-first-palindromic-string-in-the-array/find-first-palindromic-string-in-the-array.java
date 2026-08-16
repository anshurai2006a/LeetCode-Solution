class Solution {
    public String firstPalindrome(String[] words) {
        for(int i = 0; i < words.length; i++){
            StringBuilder w = new StringBuilder(words[i]);
            StringBuilder r = new StringBuilder(words[i]);
            r.reverse();

            if(w.compareTo(r) == 0){
                return words[i];
            }
        }
        return "";

        
    }
}