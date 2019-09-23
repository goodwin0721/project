//定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
package hgy.java.arithmetic;
import java.util.Stack;

import org.junit.Test;
public class StackMinElement {
	Stack<Integer> elemStack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
	int min = Integer.MAX_VALUE;
    public void push(int node) {
        min = min<node?min:node;
        elemStack.push(node);
        minStack.push(min);
    }
    
    public void pop() {
    	int e =elemStack.pop();
    	minStack.pop();
    	if(!minStack.isEmpty()){
    		int ee = minStack.pop();//取出最小栈栈顶的第二个元素
    		minStack.push(ee);
    		min = e <= min ? ee : min;
    	}else
    		min = Integer.MAX_VALUE;
    }
    
    public int top() {
		return elemStack.peek();
        
    }
    
    public int min() {
    	return min;
    }
    @Test
	public void test() {
    	for(int i  = 0; i < 10;i++){
    		int num = (int)(Math.random()*10);
    		push(num);
    		System.out.print(num+" "+min()+"|");
    	}
    	System.out.println();
    	for(int i = 0;i < 10;i++){
    		System.out.print(min() + " ");
    		pop();
    	}
	}
}
