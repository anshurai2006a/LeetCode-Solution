class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // compare the timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        //store the x and quesries with index
        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; ++i) {
            qs[i][0] = queries[i];
            qs[i][1] = i; 
        }
         Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));
        Map<Integer, Integer> serverCounts = new HashMap<>();
        int[] ans = new int[m];
        int j = 0, k = 0;
        
        for (int[] q : qs) {
            int r = q[0];
            int originalIdx = q[1];
            int l = r - x;
            
            while (k < logs.length && logs[k][1] <= r) {
                serverCounts.merge(logs[k][0], 1, Integer::sum);
                k++;
            }
            
            while (j < logs.length && logs[j][1] < l) {
                if (serverCounts.merge(logs[j][0], -1, Integer::sum) == 0) {
                    serverCounts.remove(logs[j][0]);
                }
                j++;
            }
            
            ans[originalIdx] = n - serverCounts.size();
        }
        
        return ans; 
        
    }
}