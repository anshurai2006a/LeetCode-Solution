class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zeroIdx = -1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        if (check(nums, zeroIdx, 1)) {
            ans = Math.min(ans, zeroIdx);
            ans = Math.min(ans, n - zeroIdx + 2);
        }
        
        if (check(nums, zeroIdx, -1)) {
            ans = Math.min(ans, zeroIdx + 2);
            ans = Math.min(ans, n - zeroIdx);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private boolean check(int[] nums, int zeroIdx, int step) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int prev = (zeroIdx + (i - 1) * step + n) % n;
            int curr = (zeroIdx + i * step + n) % n;
            if (nums[curr] != nums[prev] + 1) {
                return false;
            }
        }
        return true;
    }
}