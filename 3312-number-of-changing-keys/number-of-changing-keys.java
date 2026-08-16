class Solution {
    public int countKeyChanges(String s) {
        String lowerS = s.toLowerCase();
        int count = 0; 
        
        for(int i = 1; i < lowerS.length(); i++){
            if(lowerS.charAt(i) != lowerS.charAt(i-1)){
                count++;
            }

        }
        return count;
    }
}