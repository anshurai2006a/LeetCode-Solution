class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);

        
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }

        List<String> res = new ArrayList<>();
        int lastRight = -1; 

        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
    
            if (i != left[charIdx]) continue;

            int newRight = right[charIdx];
            boolean valid = true;

            
            for (int j = i; j <= newRight; j++) {
                int innerIdx = s.charAt(j) - 'a';
                if (left[innerIdx] < i) {
                    valid = false;
                    break;
                }
                newRight = Math.max(newRight, right[innerIdx]);
            }

            if (valid) {
                if (i > lastRight) {
                    res.add(s.substring(i, newRight + 1));
                } else {
                    res.set(res.size() - 1, s.substring(i, newRight + 1));
                }
                lastRight = newRight;
            }
        }

        return res;
        
    }
}