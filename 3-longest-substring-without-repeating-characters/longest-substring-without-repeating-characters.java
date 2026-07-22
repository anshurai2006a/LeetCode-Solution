class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null && s.length() == 0){
            return 0;
        }
        int maxLength = 0;
        Map < Character , Integer > charMap = new HashMap<>();

        for( int right = 0, left = 0; right < s.length(); right++){
            char currentChar = s.charAt(right);

            if(charMap.containsKey(currentChar)){
                left = Math.max(left, charMap.get(currentChar)+1);
            }

            charMap.put(currentChar, right);

            maxLength = Math.max(maxLength, right-left + 1);
        }
        return maxLength;
        
    }
}