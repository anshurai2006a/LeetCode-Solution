class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[] dp = new int[m+1];
        dp[0] = 1;

        for(int j = 1; j <= n; j++){
            for(int i = m; i >= 1; i--){
                if(s.charAt(j-1) == t.charAt(i-1)){
                    dp[i] = dp[i] + dp[i-1];
                }
            }
        }
        return dp[m];
        
    }
}