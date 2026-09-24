class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int current = nums[i];
            int digitSum = 0;
            
            while (current > 0) {
                digitSum += current % 10;
                current /= 10;
            }
            
            if (digitSum == i) {
                return i;
            }
        }
        return -1; // Return -1 if no such index exists
    }
}
