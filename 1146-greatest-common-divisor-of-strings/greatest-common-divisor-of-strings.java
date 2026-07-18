class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 +str1)){
            return "" ;
        }
        return gcd(str1, str2);
        
    }
    private String gcd(String s1, String s2){
        if(s1.equals(s2)){
            return s1;
        }
        if(s1.length() > s2.length()){
            return gcd(s1.substring(s2.length()), s2);
        }else {
            return gcd(s1 , s2.substring(s1.length()));
        }


    }
}