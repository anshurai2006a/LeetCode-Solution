class Solution {
    class Window {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int lCount = 0, rCount = 0;
        long lSum = 0, rSum = 0;

        void add(int v) {
            if (left.isEmpty() || v <= left.lastKey()) {
                left.put(v, left.getOrDefault(v, 0) + 1);
                lSum += v;
                lCount++;
            } else {
                right.put(v, right.getOrDefault(v, 0) + 1);
                rSum += v;
                rCount++;
            }
            balance();
        }

        void remove(int v) {
            if (left.containsKey(v)) {
                lSum -= v;
                lCount--;
                if (left.put(v, left.get(v) - 1) == 1) left.remove(v);
            } else {
                rSum -= v;
                rCount--;
                if (right.put(v, right.get(v) - 1) == 1) right.remove(v);
            }
            balance();
        }

        void balance() {
            while (lCount > rCount + 1) {
                int k = left.lastKey();
                if (left.put(k, left.get(k) - 1) == 1) left.remove(k);
                lSum -= k; lCount--;
                right.put(k, right.getOrDefault(k, 0) + 1);
                rSum += k; rCount++;
            }
            while (rCount > lCount) {
                int k = right.firstKey();
                if (right.put(k, right.get(k) - 1) == 1) right.remove(k);
                rSum -= k; rCount--;
                left.put(k, left.getOrDefault(k, 0) + 1);
                lSum += k; lCount++;
            }
        }

        long getCost() {
            if (lCount == 0) return 0;
            long med = left.lastKey();
            return (rSum - med * rCount) + (med * lCount - lSum);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n];
        Arrays.fill(cost, Long.MAX_VALUE / 2);
        Window w = new Window();

        for (int i = 0; i < n; i++) {
            w.add(nums[i]);
            if (i >= x) w.remove(nums[i - x]);
            if (i >= x - 1) cost[i - x + 1] = w.getCost();
        }

        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
            dp[i][0] = 0;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 0; i < n; i++) {
                dp[i + 1][j] = dp[i][j];
                if (i - x + 1 >= 0 && cost[i - x + 1] != Long.MAX_VALUE / 2) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i - x + 1][j - 1] + cost[i - x + 1]);
                }
            }
        }

        return dp[n][k] >= Long.MAX_VALUE / 2 ? -1 : dp[n][k];
    }
}
