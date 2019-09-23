//输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
package hgy.java.arithmetic;

import org.junit.Test;

public class NumberOf1 {
	public int numberOf1(int n) {
		String num = Integer.toBinaryString(n);
		System.out.println(num);
		int count = 0;
		for(int i = 0;i < num.length();i++)
			if(num.charAt(i)=='1')
				count++;
		return count;
    }
	@Test
	public void test(){
		System.out.println(numberOf1(15));
	}
}
