class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        int maxBalance = (m + n - 1) / 2;
        Boolean[][][] memo = new Boolean[m][n][maxBalance + 1];
        
        return dfs(grid, 0, 0, 0, m, n, memo);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, int m, int n, Boolean[][][] memo) {
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        if (balance < 0 || balance >= memo[0][0].length) {
            return false;
        }
        
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        
        boolean validPathExists = false;
        
        if (r + 1 < m) {
            validPathExists = validPathExists || dfs(grid, r + 1, c, balance, m, n, memo);
        }
        
        if (c + 1 < n) {
            validPathExists = validPathExists || dfs(grid, r, c + 1, balance, m, n, memo);
        }
        
        return memo[r][c][balance] = validPathExists;
    }
}
