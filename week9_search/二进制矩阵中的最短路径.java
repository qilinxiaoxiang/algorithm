// 常规BFS
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) return -1;
        int res = 0, n = grid.length;
        // 存放当前元素的下标
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];
        // 枚举8个方向
        int[][] dir = {{-1,0}, {-1,1}, {0,1},{1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        while (!queue.isEmpty()) {
            res++;
            int l = queue.size();
            for(int i = 0; i < l; i++) {
                int[] t = queue.pollFirst();
                if (t[0] == n - 1 && t[1] == n - 1) return res;
                for (int[] d : dir) {
                    int x = t[0] + d[0], y = t[1] + d[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 && !vis[x][y]) {
                        vis[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return -1;
    }
}

// 尝试使用启发式, 不过超时了
public class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        // 大方向是启发式
        // 参数校验
        if (grid == null) {
            return -1;
        }
        int m = grid.length;
        if (m == 0) {
            return -1;
        }
        int n = grid[0].length;
        if (n == 0) {
            return -1;
        }

        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) {
            return -1;
        }

        int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};

        PriorityQueue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(a -> getEstimation(a.step, a.x, a.y, m, n)));
        nodes.add(new Node(0, 0, 1));
        Node node;
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            grid[node.x][node.y] = 1;
            if ((node.x == m - 1 && node.y == n - 1)) {
                return node.step;
            }
            for (int i = 0; i < 8; i++) {
                int nextX = node.x + dx[i];
                int nextY = node.y + dy[i];
                if ((nextX < 0 || nextX >= m || nextY < 0 || nextY >= n)) {
                    continue;
                }
                if (grid[nextX][nextY] == 1) {
                    continue;
                }
                Node nextNode = new Node(nextX, nextY, node.step + 1);
                nodes.add(nextNode);

            }
        }
        return -1;

    }

    // 估计函数
    private int getEstimation(int step, int x, int y, int m, int n) {
        return step + Math.max(m-1-x, n-1-y);
    }

    public static void main(String[] args) {

    }
}

class Node {
    int x;
    int y;
    int step;

    public Node(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }
}


// 还可以双向BFS, 下次补充