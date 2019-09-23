//一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
//求该青蛙跳上一个n级的台阶总共有多少种跳法。
package hgy.java.arithmetic;

import org.junit.Test;

public class JumpFloorII {
	/**
	 * 思路：第一次跳到第i级，剩下的有f(n-i)种跳法，故f(n)=f(n-1)+......+f(n-n),f(0)=0,f(1)=1,f(2)=2
	 * 又f(n-1) = f(n-1-1)+......+f(n-1-(n-1)),所以f(n) = 2*f(n-1)
	 * @param target
	 * @return
	 */
	public int jumpFloor(int target) {
		if (target <= 2)
			return target;
		else
			return 2 * jumpFloor(target - 1);
	}

	// 根据上述思路优化
	public int jumpFloor1(int target) {
		return target > 0 ? 1 << (target - 1) : 0;
	}

	/**
	 * 思路：第一次跳到第i级，剩下的有f(n-i)种跳法，故f(n)=f(n-1)+......+f(n-n),f(0)=0,f(1)=1,f(2)=2
	 * @param target
	 * @return
	 */
	// 迭代
	public int jumpFloor2(int target) {
		if (target <= 2)
			return target;
		int[] ways = new int[target + 1];
		ways[1] = 1;
		ways[2] = 2;
		for (int i = 3; i <= target; i++) {
			for (int j = 1; j < i; j++)
				ways[i] = ways[i] + ways[j];
			ways[i]++;// 加上一步跳到终点
		}
		return ways[target];
	}

	@Test
	public void test() {
		System.out.println(jumpFloor(6));
		System.out.println(jumpFloor1(0));
		System.out.println(jumpFloor2(6));
	}

}
