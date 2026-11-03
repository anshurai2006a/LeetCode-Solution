class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = nums[n-1];
        int moves = 0;

        for(int i = 0; i < n; i++){
            moves += max - nums[i];
        }
        return moves;
        
        
        
    }
}