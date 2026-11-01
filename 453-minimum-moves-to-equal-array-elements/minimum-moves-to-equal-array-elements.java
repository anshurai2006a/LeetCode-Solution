class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0];
         
        int count = 0;
        for(int i = 0; i < n; i++){
            count += nums[i] - min;

        }
        return count;
        
    }
}