package LinkedList;

public class multiply2LL {
    public class Main {

        public static class ListNode {
            int val;
            ListNode next;
    
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
    
        private static ListNode reverse(ListNode head) {
            if (head == null || head.next == null) return head;
    
            ListNode curr = head;
            ListNode prev = null;
            ListNode forw;
    
            while (curr != null) {
                forw = curr.next;
                curr.next = prev;
                prev = curr;
                curr = forw;
            }
            return prev;
        }
    
        private static ListNode sum(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
    
            ListNode c1 = l1, c2 = l2;
            ListNode head = new ListNode(-1);
            ListNode itr = head;
            int carry = 0;
    
            while (c1 != null || c2 != null || carry != 0) {
                int s = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
                int ld = s % 10;
                carry = s / 10;
    
                itr.next = new ListNode(ld);
                itr = itr.next;
    
                if (c1 != null) c1 = c1.next;
                if (c2 != null) c2 = c2.next;
            }
            return head.next;
        }
    
        private static ListNode addFirst(ListNode head) {
            ListNode temp = new ListNode(0);
            temp.next = head;
            return temp;
        }
    
        private static ListNode multiply(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) return null;
    
            l1 = reverse(l1);
            l2 = reverse(l2);
    
            ListNode c2 = l2;
            ListNode cSum = null;
            int idx = 0;
    
            while (c2 != null) {
                int carry = 0;
                ListNode head = new ListNode(-1);
                ListNode itr = head;
    
                // Add zeroes for the position
                for (int i = 0; i < idx; i++) {
                    itr.next = new ListNode(0);
                    itr = itr.next;
                }
    
                ListNode c1 = l1;
    
                while (c1 != null) {
                    int mul = (c1.val * c2.val) + carry;
                    carry = mul / 10;
                    itr.next = new ListNode(mul % 10);
                    itr = itr.next;
                    c1 = c1.next;
                }
    
                if (carry > 0) {
                    itr.next = new ListNode(carry);
                }
    
                cSum = sum(cSum, head.next);
                c2 = c2.next;
                idx++;
            }
    
            return reverse(cSum);
        }
    
        // Helper function to create a linked list from array
        private static ListNode createList(int[] digits) {
            ListNode dummy = new ListNode(-1);
            ListNode current = dummy;
            for (int digit : digits) {
                current.next = new ListNode(digit);
                current = current.next;
            }
            return dummy.next;
        }
    
        // Helper function to print linked list
        private static void printList(ListNode head) {
            while (head != null) {
                System.out.print(head.val);
                if (head.next != null) System.out.print(" -> ");
                head = head.next;
            }
            System.out.println();
        }
    
        public static void main(String[] args) {
            // Create two numbers: l1 = 9 -> 3 -> 2 (i.e. 932), l2 = 8 -> 7 (i.e. 87)
            ListNode l1 = createList(new int[]{9, 3, 2});
            ListNode l2 = createList(new int[]{8, 7});
    
            System.out.print("L1: ");
            printList(l1);
    
            System.out.print("L2: ");
            printList(l2);
    
            ListNode result = multiply(l1, l2);
            System.out.print("Product: ");
            printList(result);  // Expected: 8 -> 1 -> 0 -> 8 -> 4 (i.e. 81184)
        }
    }
    
}
