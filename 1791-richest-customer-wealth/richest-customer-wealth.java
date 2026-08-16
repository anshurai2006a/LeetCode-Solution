class Solution {
    public int maximumWealth(int[][] accounts) {

        int m = accounts.length;
        int n = accounts[0].length;
        
        int maxWealth = 0;
        for(int i =0; i < m; i++){
            int currentWealth = 0;
            for(int j = 0; j < n; j++){
                currentWealth += accounts[i][j];
            }
            if(currentWealth > maxWealth){
                maxWealth = currentWealth;
            }
        }
        return maxWealth;
    }
}