class Solution {
    public String reverseWords(String s) {
        // java里面字符串不可变, 所以无论如何需要O(n)空间, 直接用API操作即可.
        return Arrays.stream(s.trim().split(" +")).sorted((a, b) -> -1).collect(Collectors.joining(" "));
    }
}