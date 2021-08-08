class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        char[] temp;
        for (int i = 0; i < strs.length; i++) {
            temp = strs[i].toCharArray();
            Arrays.sort(temp);
            map.computeIfAbsent(new String(temp), k->new ArrayList<String>()).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
}