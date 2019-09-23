//定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
package hgy.java.arithmetic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

/*原理：“异或的可逆性”：A⊕A=1，如果A⊕B=C，那么C⊕A=B
 		把当前元素值进行拆分：当前元素top = 当前最小min + 二者差值（非负）
 		如果当前元素为当前最小，那么：上个最小preMin = 当前最小min + 二者差值（非负）
 
 *利用两个加数作为异或的操作数：栈存储值 = 当前最小min ⊕ 二者差值（此处正负需要进行刻意修改）
 *那么我们使用拆分的两个加数作为异或的操作数，
 *出栈时就可以根据已记录的当前最小值min来复原差值，
 *再根据差值和当前最小值计算出上个元素值或者是上个最小值。
 *为了区分复原的结果到底是上个元素还是上个最小，
 *我们需要做一个小标记，我们发现它们和当前最小值的差值都是一个非负数，
 *那么我们只要刻意使其中一个为负数（添加负号），就可以达到区分的目的。
 *这里置上个最小值和当前最小值的差值为负数，那么：
 *异或复原操作：
	将已记录的当前最小值min和栈顶存储元素异或，得到一个差值
	利用差值和最小值复原某个元素值
 *入栈时：
	如果入栈元素比当前最小值更小，那么将新元素（新的最小）和二者差值取负再异或的结果入栈；
	如果入栈的元素不能更新最小值，那么将当前最小值和二者差值异或后入栈；
 *出栈时：
	判断最小值min是否需要更新：如果当前栈顶复原的差值为负，那么说明当前最小值要被出栈了，
	利用差值复原上一个最小值，否则最小值不变；
	出栈必须复原上一个元素值top：将栈顶出栈，根据新栈顶计算上一个元素值，如果二次复原的差值为负，
	说明上一个元素值等于最小值，否则，上一个元素值为最小值加差值；
	如果出栈后栈为空，需要置最小值为无穷大
 */
/**
 * 方法2：抑或压缩法
 */

public class StackMinElementII {
	public Stack<Integer> stack = new Stack<>();
	public int min = Integer.MAX_VALUE;
	public int top = -1;

	public void push(int elem) {
		if (elem < min) {
			// 最小值被更新：当前最小=最新最小(elem)+差值，差值取负作标记，再使用异或合并
			stack.push(elem ^ (elem - min));
			min = elem;
		} else {
			// 最小值没有更新：栈顶=最小值+差值，使用异或合并
			stack.push(min ^ (elem - min));
		}
		top = elem;
	}

	public void pop() {
		if (!stack.isEmpty()) {
			int temp = min ^ stack.peek();
			if (temp < 0)// 当前栈顶元素是最小元素（差值为负），出栈需要更新最小值
				min = min - temp;
			stack.pop();
			// 利用已有信息复原栈顶元素
			if (!stack.isEmpty()) {
				temp = min ^ stack.peek();
				if (temp < 0)
					top = min; // 当前栈顶元素为最小值
				else
					top = temp + min;// 当前栈顶元素=最小值+差值
			} else
				min = Integer.MAX_VALUE;// 栈为空，重置最小值

		} else {
			min = Integer.MAX_VALUE;
		}
	}

	public int min() {
		return min;
	}

	public int top() {
		return top;
	}
	
	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			int num = (int) (Math.random() * 10);
			push(num);
			System.out.print(num + " " + min() + "|");
		}
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print(top() + " " + min() + "|");
			pop();
		}
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
	}
}


