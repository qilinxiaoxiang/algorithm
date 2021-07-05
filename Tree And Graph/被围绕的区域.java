class Solution {
public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        visit = new boolean[m][n];
        notChange = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visit[i][j] = false;
                notChange[i][j] = false;
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visit[0][j]) {
                bfs(board, 0, j);
            }
        }

        for (int i = 1; i < m; i++) {
            if (board[i][0] == 'O' && !visit[i][0]) {
                bfs(board, i, 0);
            }
        }

        for (int i = 1; i < m; i++) {
            if (board[i][n-1] == 'O' && !visit[i][n-1]) {
                bfs(board, i, n-1);
            }
        }

        for (int j = 1; j < n - 1; j++) {
            if (board[m-1][j] == 'O' && !visit[m-1][j]) {
                bfs(board, m-1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !notChange[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 从(sx,sy)出发bfs
    private void bfs(char[][] grid, int sx, int sy) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        Queue<Pair<Integer,Integer>> q = new LinkedList<Pair<Integer,Integer>>();
        // 第一步：push起点
        q.offer(new Pair<Integer,Integer>(sx,sy));
        visit[sx][sy] = true;
        while (!q.isEmpty()) {
            int x = q.peek().getKey();
            int y = q.poll().getValue();
            notChange[x][y] = true;
            // 扩展所有出边（四个方向）
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 任何时候访问数组前，判断合法性
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (grid[nx][ny] == 'O' && !visit[nx][ny]) {
                    q.offer(new Pair<Integer,Integer>(nx, ny));
                    // BFS：入队时标记visit
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private int m;
    private int n;
    private boolean[][] visit;
    private boolean[][] notChange;
}