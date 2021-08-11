class Solution {
    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*{2,}", "*");
        s = " " + s;
        p = " " + p;
        int n = s.length();
        int m = p.length();
        boolean[][] f = new boolean[n][m];
        f[0][0] = true;
        if (p.length() > 1 && p.charAt(1) == '*') {
            f[0][1] = true;

        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (p.charAt(j) == '*') {
                    f[i][j] = f[i][j - 1] || f[i - 1][j];
                } else {
                    f[i][j] = f[i - 1][j - 1] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
                }
            }
        }
        return f[n - 1][m - 1];
    }
}