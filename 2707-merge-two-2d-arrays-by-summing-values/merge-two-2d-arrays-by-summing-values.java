class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> resultList = new ArrayList<>();
        int i = 0;
        int j = 0;

        while(i < nums1.length && j < nums2.length){
            int id1 = nums1[i][0];
            int val1 = nums1[i][1];
            int id2 = nums2[j][0];
            int val2 = nums2[j][1];

            if(id1 == id2){
                resultList.add(new int[]{id1, val1+val2});
                i++;
                j++;
            }else if(id1 < id2){
                resultList.add(new int[]{id1, val1});
                i++;
            }else {
            resultList.add(new int[]{id2, val2});
            j++;
            }
        }
        
    
         while(i < nums1.length){
            resultList.add(nums1[i]);
            i++;
        }
        while(j < nums2.length){
            resultList.add(nums2[j]);
            j++;
        }
        return resultList.toArray(new int[resultList.size()][]);
    }

}