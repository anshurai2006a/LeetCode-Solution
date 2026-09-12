class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for(int i = 0; i < n; i++){
            arr[i] = new int[]{intervals.get(i).get(0), intervals.get(i).get(1), intervals.get(i).get(2), i};
        }  
        Arrays.sort(arr,(a,b) -> Integer.compare(a[0], b[0]));
        long[][] dpW = new long[n+1][5];
        List<Integer>[][]dpI = new ArrayList[n+1][5];   
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) dpI[i][j] = new ArrayList<>();
        }   
        for (int i = n - 1; i >= 0; i--) {
            int next = n, low = i + 1, high = n - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (arr[mid][0] > arr[i][1]) { next = mid; high = mid - 1; }
                else low = mid + 1;
            }

            for (int j = 1; j <= 4; j++) {
                long skipW = dpW[i + 1][j], takeW = arr[i][2] + dpW[next][j - 1];
                List<Integer> takeList = new ArrayList<>(dpI[next][j - 1]);
                takeList.add(arr[i][3]);
                Collections.sort(takeList);

                if (takeW > skipW) {
                    dpW[i][j] = takeW; dpI[i][j] = takeList;
                } else if (skipW > takeW) {
                    dpW[i][j] = skipW; dpI[i][j] = dpI[i + 1][j];
                } else {
                    dpW[i][j] = skipW;
                    dpI[i][j] = compareLists(dpI[i + 1][j], takeList) <= 0 ? dpI[i + 1][j] : takeList;
                }
            }
        }
        return dpI[0][4].stream().mapToInt(i -> i).toArray();    
    }
    private int compareLists(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) return Integer.compare(a.get(i), b.get(i));
        }
        return Integer.compare(a.size(), b.size());
    }

}