class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if(N < 0){
            N = -N;
        }
        double ans = fastPow(x, N);
        return n < 0 ? 1.0 / ans : ans;
        
    }
    private double fastPow(double x, long n){
        if(n == 0){
            return 1.0;
        }
        double half = fastPow(x, n/2);
         if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }

    }

}