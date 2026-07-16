class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int maxEl = -1;  // we take -1 because nums array will only contain positive so if we want max (-1 , nums[0]) we get nums[0]

        for(int i = 0; i < n; i++){

            maxEl = Math.max(maxEl, nums[i]);
            prefixGcd[i] = gcd(nums[i] , maxEl);
        }

        Arrays.sort(prefixGcd);

        //Two pointers to compare the smallest and largest
        long result = 0;
        int i = 0;
        int j = n-1;

        while(i < j ){
            result += gcd(prefixGcd[i] , prefixGcd[j]);
            i++;
            j--;
        }
        return result;
    }
    private int gcd(int a, int b){
        while(b != 0){
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }
}