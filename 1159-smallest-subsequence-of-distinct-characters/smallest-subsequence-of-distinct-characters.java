class Solution {
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        boolean[] inStack = new boolean[26];
        char[] stack = new char[26];
        int top = -1;

        for(char c : s.toCharArray()){
            count[c - 'a']++;
        }
        for(char c : s.toCharArray()){
            count[c - 'a']--;

            if(inStack[c - 'a']){
                continue;
            }
            while(top >=0 && stack[top] > c && count[stack[top] - 'a'] > 0){
                inStack[stack[top--] - 'a'] = false; 

            }
            stack[++top] = c;
            inStack[c - 'a'] = true;
        }
        return new String(stack, 0, top+1);
        
    }
}