//一只青蛙一次可以跳上1级台阶，也可以跳上2级。
//求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

package hgy.java.arithmetic;

import org.junit.Test;

public class JumpFloor {
	/**
	 * 思路：f(1)=1,f(2)=2,第一步有两种跳法，假定第一次跳到第一阶，剩下有f(n-1)种跳法，
	 * 假定第一次跳到第二阶，剩下有f(n-2)种跳法，所以f(n) =f(n-1)f(n-2),即斐波那契数列。
	 * 
	 * @param target
	 * @return
	 */
	public int jumpFloor(int target) {
		int ways = 0;
		if (target == 1)
			ways = 1;
		else if (target == 2)
			ways = 2;
		else {
			int expre = 1;
			int pre = 2;
			for (int i = 3; i <= target; i++) {
				ways = expre + pre;
				expre = pre;
				pre = ways;
			}
		}
		return ways;
	}

	@Test
	public void test() {
		System.out.println(jumpFloor(3));
	}
}
