class Solution {
    public int numberOfSets(int n, int k) {
        long mod = 1_000_000_007;
        
        int N = n + k - 1;
        int R = 2 * k;
        
        return (int) combination(N, R, mod);
    }
    
    private long combination(int n, int r, long mod) {
        if (r > n) return 0;
        if (r > n - r) r = n - r;
        
        long num = 1;
        long den = 1;
        
        for (int i = 0; i < r; i++) {
            num = (num * (n - i)) % mod;
            den = (den * (i + 1)) % mod;
        }
        
        return (num * power(den, mod - 2, mod)) % mod;
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}