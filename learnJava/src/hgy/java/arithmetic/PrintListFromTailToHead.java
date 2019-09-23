//输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
/*
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
package hgy.java.arithmetic;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

class ListNode {
	int val;
	ListNode next = null;
	ListNode(int val) {
		this.val = val;
	}
}

public class PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> arrayList = new ArrayList<Integer>();
		if (listNode != null) {
			arrayList.add(listNode.val);
			while (listNode.next != null) {
				listNode = listNode.next;
				arrayList.add(0, listNode.val);
			}
		}
		return arrayList;
    }
    @Test
    public void test(){
    	ListNode[]listNodes = new ListNode[10];
    	for(int i = 0 ; i < listNodes.length; i++){
    		listNodes[i] = new ListNode(i);
    		if(i>0&&i<listNodes.length)
    			listNodes[i-1].next = listNodes[i];
    	}
    	ListNode head = listNodes[0];
    	/*do{
    		System.out.print(head.val+" ");
    		head = head.next;
    	}while(head!=null);*/
    	List<Integer>list = new ArrayList<>();
    	list = printListFromTailToHead(head);
    	System.out.println(list.toString());
    	System.out.println(printListFromTailToHead(null));
    }
}











