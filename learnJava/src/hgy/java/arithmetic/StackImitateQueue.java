//用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型
package hgy.java.arithmetic;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

public class StackImitateQueue {
	Stack<Integer> stack1 = new Stack<Integer>();//存数
	Stack<Integer> stack2 = new Stack<Integer>();//辅助
	public void push(int node) {
		stack2.clear();
		while(!stack1.isEmpty())
			stack2.push(stack1.pop());
		stack2.push(node);
		while(!stack2.isEmpty())
			stack1.push(stack2.pop());
	}
	public int pop() {
		return stack1.pop();
	}
	@Test
	public void test(){
		for(int i = 1;i < 5;i++)
			push(i);
		System.out.println(pop());
		
	}

}
