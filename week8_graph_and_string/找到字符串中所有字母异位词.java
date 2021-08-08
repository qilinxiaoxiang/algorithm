class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int wordLength = p.length();
        if (s.length() < wordLength) {
            return result;
        }
        int[] targetHash = getHash(p);
        int[] cursorHash = null;
        for (int i = 0; i < s.length(); i++) {
            int end = i + wordLength;
            if (end > s.length()) {
                break;
            }
            if (cursorHash == null) {
                cursorHash = getHash(s.substring(i, end));
            } else {
                modifyHash(s.charAt(i-1), s.charAt(end - 1), cursorHash);
            }
            if (Arrays.equals(targetHash, cursorHash)) result.add(i);
        }
        return result;
    }

    private int[] getHash(String word) {
        int[] count = new int[26];
        Arrays.fill(count, 0);
        for (int i = 0; i< word.length(); i++) {
            count[word.charAt(i) - 'a']++;
        }
        return count;
    }

    private void modifyHash(char oldChar, char newChar, int[] hash) {
        hash[oldChar - 'a'] --;
        hash[newChar - 'a'] ++;
    }
}