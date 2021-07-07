import java.util.Optional;
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0);
        ListNode cursor = sentinel;
        while (l1 != null || l2 != null) {
            if (l1 != null && l1.val <= Optional.ofNullable(l2).map(l -> l.val).orElse(Integer.MAX_VALUE)) {
                cursor.next = l1;
                l1 = l1.next;
            } else {
                cursor.next = l2;
                l2 = l2.next;
            }
            cursor = cursor.next;
        }
        return sentinel.next;
    }
}