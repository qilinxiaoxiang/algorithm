class Solution {
    private final int[] dx = new int[]{-1, 0, 0, 1};
    private final int[] dy = new int[]{0, -1, 1, 0};
    private int rows;
    private int columns;
    private final int WATER = -1;

    private int getIndex(int x, int y) {
        return x * columns + y;
    }

    public int numIslands(char[][] grid) {
        rows = grid.length;
        columns =  grid[0].length;
        DisjointSet s = new DisjointSet(rows * columns);
        int[] array = s.getArray();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // 水不关心
                if (grid[i][j] == '0') {
                    array[getIndex(i, j)] = WATER;
                    continue;
                }
                // 是陆地, 则上下左右看看
                for (int d = 0; d < 4; d++) {
                    int x = i + dx[d];
                    int y = j + dy[d];
                    // 越界不关心, 是陆地则合并
                    if (x < 0 || x >= rows || y < 0 || y >= columns) {
                        continue;
                    } else if (grid[x][y] == '1') {
                        s.unionSet(getIndex(i, j), getIndex(x, y));
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == WATER) continue;
            set.add(s.find(array[i]));
        }
        return set.size();
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

    public int[] getArray() {
        return fa;
    }

    int[] fa;
}