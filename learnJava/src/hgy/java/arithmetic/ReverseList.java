//输入一个链表，反转链表后，输出新链表的表头。
package hgy.java.arithmetic;

import org.junit.Test;

public class ReverseList {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ListNode reverse(ListNode head) {
		if (head == null)
			return null;
		ListNode newHead = new ListNode(head.val);
		newHead.next = null;
		ListNode temp = null;
		while (head.next != null) {
			temp = newHead;
			newHead = new ListNode(head.next.val);
			newHead.next = temp;

			head = head.next;
		}
		return newHead;
	}

	@Test
	public void test() {
		ListNode node = new ListNode(0);
		ListNode head = node;
		for (int i = 1; i < 10; i++) {
			ListNode newNode = new ListNode(i);
			node.next = newNode;
			node = newNode;
		}
		ListNode lhead = head;
		if (lhead != null) {
			do {
				System.out.print(lhead.val + " ");
				lhead = lhead.next;
			} while (lhead != null);
			System.out.println();
		}

		ListNode listNode = head;
		ListNode ansHead = reverse(listNode);
		if (ansHead != null) {
			do {
				System.out.print(ansHead.val + " ");
				ansHead = ansHead.next;
			} while (ansHead != null);
		}
	}
}
