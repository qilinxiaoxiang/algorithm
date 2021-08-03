class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet s = new DisjointSet(edges.length + 1);
        for (int i = 0; i < edges.length; i++) {
            // 每条线都要将一个新节点并入原集合, 如果新节点已经在原集合里面了, 说明是冗余的
            if (s.find(edges[i][0]) == s.find(edges[i][1])) return edges[i];
            s.unionSet(edges[i][0], edges[i][1]);
        }
        return null;
    }
}

class DisjointSet {
    public DisjointSet(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
    }

    public int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }

    public void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) fa[x] = y;
    }

    int[] fa;
}