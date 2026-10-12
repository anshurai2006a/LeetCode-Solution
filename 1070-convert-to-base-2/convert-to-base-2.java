class Solution {
    public String baseNeg2(int n) {
        if (n == 0) return "0";
        
        String ans = "";
        while (n != 0) { 
            int remainder = Math.abs(n % -2);
            ans = remainder + ans;
            n = (n - remainder) / -2;
        }
        
        return ans;
        
    }
}