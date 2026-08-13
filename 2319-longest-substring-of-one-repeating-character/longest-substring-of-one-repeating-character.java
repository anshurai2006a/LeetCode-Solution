class Solution {
    class Node{
        int l, r;
        char lc, rc;
        int lmx, rmx, mx;

        Node(int l, int r){
            this.l = l;
            this.r = r;
        }
    }
    Node[] tree;
    char[] chars;
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4* n];

        build(1, 0 , n-1);

        int k = queryIndices.length;
        int[] result = new int[k];

        for(int i = 0; i < k; i++){
            update(1, queryIndices[i], queryCharacters.charAt(i));

            result[i] = tree[1].mx;
        }
        return result;
        
    }
    private void build(int node, int l, int r){
        tree[node] = new Node(l, r);
        if(l == r){
            tree[node].lc = chars[l];
            tree[node].rc = chars[l];
            tree[node].lmx = 1;
            tree[node].rmx = 1;
            tree[node].mx = 1;
            return;
        }
        int mid = (l + r)>> 1;
        build(node* 2, l, mid);
        build(node* 2+1, mid + 1, r);
        pushUp(node);
    }
    private void update(int node, int idx, char val){
        if(tree[node].l == tree[node].r){
            chars[idx] = val;
            tree[node].lc = val;
            tree[node].rc = val;
            return;
        }
        int mid = (tree[node].l + tree[node].r) >> 1;
        if(idx <= mid){
            update(node * 2, idx, val);
        }else{
            update(node*2 + 1, idx, val);
        }
        pushUp(node);
    }
    private void pushUp(int node){
        Node parent = tree[node];
        Node left = tree[node* 2];
        Node right = tree[node* 2 + 1];

        parent.lc = left.lc;
        parent.rc = right.rc;
        parent.lmx = left.lmx;
        parent.rmx = right.rmx;

        parent.mx = Math.max(left.mx, right.mx);
        if(left.rc == right.lc){
            int bridgeLength = left.rmx + right.lmx;
            parent.mx = Math.max(parent.mx, bridgeLength);

            if(left.lmx == left.r - left.l + 1){
                parent.lmx = left.lmx + right.lmx;
            }
            if(right.rmx == right.r - right.l + 1){
                parent.rmx = right.rmx + left.rmx;
            }
        }
    }
}