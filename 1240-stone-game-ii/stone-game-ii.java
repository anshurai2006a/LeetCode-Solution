class Solution {
    private int[] suffixSum;
    private int[][] memo;
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        suffixSum = new int[n];
        suffixSum[n-1] = piles[n-1];
        for(int i = n-2; i >= 0; i--){
            suffixSum[i] = suffixSum[i +1] + piles[i];
        }
        memo = new int[n][n + 1];
        return dp(0, 1);
    }
    private int dp(int i , int m){
        if(i + 2*m >= suffixSum.length){
            return suffixSum[i];
        }
        if(memo[i][m] != 0){
            return memo[i][m];
        }
        int minOpponentStones = Integer.MAX_VALUE;
        for(int x = 1; x <= 2*m; x++){
            minOpponentStones = Math.min(minOpponentStones, dp(i + x, Math.max(m, x)));
        }
        memo[i][m] = suffixSum[i] - minOpponentStones;
        return memo[i][m];

    }
}