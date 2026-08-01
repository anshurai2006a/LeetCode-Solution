class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0)return;
        int n = matrix.length;

        int top = 0;
        int bottom = n-1;
        while(top < bottom){
            int[] temp = matrix[top];
            matrix[top] = matrix[bottom];
            matrix[bottom] = temp;
            top++;
            bottom--;
        }

        for(int i = 0; i < n; i++){
            for(int j = i +1; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
    }
}