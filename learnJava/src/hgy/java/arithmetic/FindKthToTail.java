//输入一个链表，输出该链表中倒数第k个结点
package hgy.java.arithmetic;

import org.junit.Test;

public class FindKthToTail {
	public class ListNode {
	    int val;
	    ListNode next = null;
	    ListNode(int val) {
	        this.val = val;
	    }
	}
	public ListNode find(ListNode head,int k) {
		if(head == null||k==0)
			return null;
		ListNode fast = head;
		ListNode slow = null;
		if(k==1){
			while(fast.next!=null){
				fast =fast.next;
			}
			return new ListNode(fast.val);
		}
		int count = k-2;
		while(fast.next!=null&& count>0){//走到第k-1个结点
			fast = fast.next;
			count--;
		}
		if(fast.next == null)//结点不足k个
			return null;
		fast =fast.next;
		slow = head;
		while(fast.next!=null){
			fast =fast.next;
			slow = slow.next;
		}
		
		return slow;

    }
	@Test
	public void test() {
		ListNode node = new ListNode(0);
		ListNode head = node;
		for(int i = 1; i < 10; i++){
			ListNode newNode = new ListNode(i);
			node.next = newNode;
			node = newNode;
		}
		ListNode result = null;
		if((result=find(head,1)) != null)
			System.out.println(result.val);
		else System.out.println("null");
	}

}
