class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        int n = row*col;

        k = k % n;
        if(k==0){
            return convertToList(grid);
        }
        reverse(grid, col, 0, n-1);
        reverse(grid, col, 0, k-1);
        reverse(grid, col, k , n-1);

        return convertToList(grid);
    }
    private void reverse(int[][] grid, int col, int i, int j){
        while(i <j){
            int r1 = i/ col, c1 = i%col;
            int r2 = j/ col, c2 = j%col;


            int temp = grid[r1][c1];
            grid[r1][c1] = grid[r2][c2];
            grid[r2][c2] = temp;

            i++;
            j--;
            
        }
    }
    private List<List<Integer>> convertToList(int[][] grid){
        List<List<Integer>> result = new ArrayList<>();
        for(int[] row : grid){
            List<List<Integer>> rowList = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            for(int val: row){
                list.add(val);
            }
            result.add(list);

        }
        return result;
    }

        
    
}