class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int completeGroups = n / 8;
        int remainingLetters = n % 8;

        return (completeGroups +1) * (4 * completeGroups + remainingLetters);
        
    }
}