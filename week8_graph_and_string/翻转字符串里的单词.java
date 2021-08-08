class Solution {
    public String reverseWords(String s) {
        // java里面字符串不可变, 所以无论如何需要O(n)空间, 直接用API操作即可.
        List<String> list = Arrays.asList(s.trim().split(" +"));
        Collections.reverse(list);
        return String.join(" ", list);
    }
}