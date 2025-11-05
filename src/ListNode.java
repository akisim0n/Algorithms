import java.util.HashSet;
import java.util.Set;

public class ListNode {

    public static void main(String[] args) {
    }

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = l1;
        while (l2 != null) {
            l1.val += l2.val;
            if (l1.val >= 10) {
                if (l1.next != null) {
                    l1.next.val += l1.val / 10;
                } else {
                    l1.next = new ListNode(l1.val / 10, null);
                }
                l1.val = l1.val % 10;
            }
            if (l1.next == null && l2.next != null) {
                l1.next = new ListNode(0, null);
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            if (l1.val >= 10) {
                if (l1.next != null) {
                    l1.next.val += l1.val / 10;
                } else {
                    l1.next = new ListNode(l1.val / 10, null);
                }
                l1.val = l1.val % 10;
            }
            l1 = l1.next;
        }
        return head;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode result = head;
        while (list1.next != null && list2.next != null) {
            if (list1.val > list2.val) {
                result.next = new ListNode(list2.val);
                list2 = list2.next;
            } else {
                result.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            result = result.next;
        }
        while (list2.next != null) {
            result.next = new ListNode(list2.val,list2.next);
            list2 = list2.next;
        }
        while (list1.next != null) {
            result.next = new ListNode(list1.val,list1.next);
            list1 = list1.next;
        }

        return head.next;
    }

    public boolean hasCycle(ListNode head) {
        boolean hasCycle = false;

        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) {
                hasCycle = true;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return hasCycle;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = l1;

        while (l1 != null && l2 != null) {
            int tempVal = l1.val + l2.val;
            if (tempVal < 10) {
                l1.val = tempVal;
                if (l1.next == null && l2.next != null) {
                    l1.next = l2.next;
                }
            } else {
                l1.val = tempVal - 10;
                if (l1.next == null) {
                    l1.next = new ListNode(1);
                } else {
                    l1.next.val += 1;
                }
            }
            l2 = l2.next;
            l1 = l1.next;
        }

        while (l1 != null) {
            if (l1.val >= 10) {
                l1.val -= 10;
                if (l1.next != null) {
                    l1.next.val += 1;
                } else {
                    l1.next = new ListNode(1);
                }
            }
            l1 = l1.next;
        }

        return dummyHead;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode answer = new ListNode(head.val);
        head = head.next;

        while (head != null) {
            answer.next = new ListNode(head.val, answer);
            head = head.next;
        }

        return answer;
    }


    public ListNode modifiedList(int[] nums, ListNode head) {
        if (nums.length == 0) return head;

        ListNode result = head;
        ListNode prev = null;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        while (head != null) {
            if (set.contains(head.val)) {
                if (head.next != null) {
                    head.val = head.next.val;
                    head.next = head.next.next;
                } else {
                    prev.next = null;
                }
            } else {
                prev = head;
                head = head.next;
            }
        }

        return result;
    }



}