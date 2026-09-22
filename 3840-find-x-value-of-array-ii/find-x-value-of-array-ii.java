
class Solution {
    int k, n;
    int[][] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        this.tree = new int[4 * n][k + 1];

        build(nums, 0, 0, n - 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            update(0, 0, n - 1, q[0], q[1] % k);
            int[] res = query(0, 0, n - 1, q[2], n - 1);
            ans[i] = res[q[3]];
        }
        return ans;
    }

    private int[] merge(int[] target, int[] left, int[] right) {
        Arrays.fill(target, 0);
        target[k] = (left[k] * right[k]) % k;
        System.arraycopy(left, 0, target, 0, k);
        for (int i = 0; i < k; i++) {
            target[(left[k] * i) % k] += right[i];
        }
        return target;
    }

    private int[] build(int[] nums, int cur, int l, int r) {
        if (l == r) {
            tree[cur][k] = nums[l] % k;
            tree[cur][tree[cur][k]] = 1;
            return tree[cur];
        }
        int mid = (l + r) >> 1, leftNode = (cur << 1) + 1, rightNode = leftNode + 1;
        int[] leftRes = build(nums, leftNode, l, mid);
        int[] rightRes = build(nums, rightNode, mid + 1, r);
        return merge(tree[cur], leftRes, rightRes);
    }

    private int[] update(int cur, int l, int r, int idx, int val) {
        if (l == r) {
            Arrays.fill(tree[cur], 0);
            tree[cur][k] = val;
            tree[cur][val] = 1;
            return tree[cur];
        }
        int mid = (l + r) >> 1, leftNode = (cur << 1) + 1, rightNode = leftNode + 1;
        int[] leftRes, rightRes;
        if (idx <= mid) {
            leftRes = update(leftNode, l, mid, idx, val);
            rightRes = tree[rightNode];
        } else {
            leftRes = tree[leftNode];
            rightRes = update(rightNode, mid + 1, r, idx, val);
        }
        return merge(tree[cur], leftRes, rightRes);
    }

    private int[] query(int cur, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) return tree[cur];
        int mid = (l + r) >> 1, leftNode = (cur << 1) + 1, rightNode = leftNode + 1;
        if (qr <= mid) return query(leftNode, l, mid, ql, qr);
        if (ql > mid) return query(rightNode, mid + 1, r, ql, qr);

        int[] leftRes = query(leftNode, l, mid, ql, mid);
        int[] rightRes = query(rightNode, mid + 1, r, mid + 1, qr);
        int[] merged = new int[k + 1];
        return merge(merged, leftRes, rightRes);
    }
}
