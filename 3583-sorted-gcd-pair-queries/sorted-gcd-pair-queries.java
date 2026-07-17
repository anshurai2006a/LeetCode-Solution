class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxNum = 0;
        for(int i =0; i<nums.length; i++){
            int num = nums[i];
            maxNum = Math.max(maxNum, num);
        }
        int[] freq = new int[maxNum + 1];
        for(int num : nums) freq[num]++;

        int[] countDivisor = new int[maxNum +1];
        for(int i =1; i <= maxNum; i++){
            for(int multiple = i; multiple <= maxNum; multiple += i){
                countDivisor[i] += freq[multiple];
            }
        }
        
        long[] countGcdPair = new long[maxNum + 1];
        for(int gcd = maxNum; gcd >=1; gcd--){
            long c = countDivisor[gcd];
            countGcdPair[gcd] = (long) c*(c-1)/2;
            for(int multiple = 2*gcd; multiple <= maxNum; multiple += gcd){
                countGcdPair[gcd] -= countGcdPair[multiple];
            }
        }
        long[] prefixSum = new long[maxNum + 1];
        for(int i =1; i <= maxNum; i++) prefixSum[i] = prefixSum[i-1] + countGcdPair[i];

        int[] ans = new int[queries.length];
        for(int i =0; i <queries.length; i++ ){
            long k = queries[i];
            int l =1; 
            int r = maxNum;
            while(l < r){
                int m =(l+r)/2;
                if(prefixSum[m] < k+1) l = m+1;
                else r =m;
            }
            ans[i] = l;
        }
        return ans;

        

        
    }
    
}