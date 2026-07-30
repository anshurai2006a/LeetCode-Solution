class Solution {
    public int maxProduct(int n) {
        int FL = 0;
        int SL = 0;

        while(n > 0){
            int currentDigit = n % 10;

            if(currentDigit > FL){
                SL = FL;
                FL = currentDigit;
            }else if (currentDigit > SL){
                SL = currentDigit;
            }
            n = n / 10;
        }
        return FL * SL;
        
    }
}