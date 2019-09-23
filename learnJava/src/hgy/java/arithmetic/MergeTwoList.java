//输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
package hgy.java.arithmetic;

import org.junit.Test;

public class MergeTwoList {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ListNode merge(ListNode list1, ListNode list2) {

		if (list1 == null && list2 == null)
			return null;
		else if (list1 == null && list2 != null)
			return list2;
		else if (list1 != null && list2 == null)
			return list1;
		else {
			ListNode flag = null;
			ListNode node = null;
			//设置第一个结点，另一种方式可以直接构造第一个结点作为链表头部，返回时以第二个结点为头结点返回
			if (list1.val <= list2.val) {
				node = new ListNode(list1.val);
				list1 = list1.next;
			} else {
				node = new ListNode(list2.val);
				list2 = list2.next;
			}
			flag = node;
			ListNode ansList = flag;

			while (list1 != null && list2 != null) {
				node = null;
				if (list1.val <= list2.val) {
					node = new ListNode(list1.val);
					list1 = list1.next;
				} else {
					node = new ListNode(list2.val);
					list2 = list2.next;
				}
				flag.next = node;
				flag = flag.next;
			}
			if (list1 != null)
				flag.next = list1;
			else
				flag.next = list2;
			return ansList;
		}
	}

	@Test
	public void test() {
		ListNode node1 = new ListNode(0);
		ListNode head1 = node1;
		ListNode node2 = new ListNode(1);
		ListNode head2 = node2;
		for (int i = 1; i < 10; i++) {
			ListNode newNode1 = new ListNode(2 * i);
			node1.next = newNode1;
			node1 = newNode1;

			ListNode newNode2 = new ListNode(2 * i + 1);
			node2.next = newNode2;
			node2 = newNode2;
		}
		printList(head1);
		printList(head2);
		printList(merge(head1, head2));
	}

	// 打印链表
	public void printList(final ListNode head) {
		ListNode h = head;
		while (h != null) {
			System.out.print(h.val);
			h = h.next;
			if (h != null)
				System.out.print(" ");
		}
		System.out.println();
	}
}
