//我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
//请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
package hgy.java.arithmetic;

import org.junit.Test;

public class RectCover {
	/**
	 * 假设大矩形横向延伸====>日日···日日
	 * 覆盖2*1时，第1块只有纵向f(1)=1种；覆盖2*2时，第2块可横、纵铺，有f(2)=2种；
	 * 覆盖2*3时，第3块纵向铺时，剩两块有f(2)=2种；第3块横向铺时，剩一块有f(1)=1种；
	 * 覆盖2*4时，第4块纵向铺时，剩三块有f(3)=3种；第4块横向铺时，剩两块有f(2)=2种；
	 * 以此类推，f(n) = f(n-1)+f(n-2)
	 * @param target
	 * @return
	 */
	public int rectCover(int target) {
		if (target <= 2)
			return target;
		else {
			return rectCover(target - 1) + rectCover(target - 2);
		}
	}

	@Test
	public void test() {
		System.out.println(rectCover(3));
	}
}
