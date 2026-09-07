class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int[] last = new int[26];
        int totalDistinct = 0;

        for(int i = 0; i < s.length(); i++){
            int charIndex = s.charAt(i) - 'a';
            int newSubsequences = (totalDistinct + 1 - last[charIndex] + MOD) % MOD;

            totalDistinct = (totalDistinct + newSubsequences) % MOD;
            last[charIndex] = (last[charIndex] + newSubsequences) % MOD;
        }
        return totalDistinct;
        
    }
}