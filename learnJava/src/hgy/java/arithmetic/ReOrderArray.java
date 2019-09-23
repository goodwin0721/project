//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
//使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
//并保证奇数和奇数，偶数和偶数之间的相对位置不变。
package hgy.java.arithmetic;

import java.util.Arrays;

import org.junit.Test;

public class ReOrderArray {
	public void reOrderArray(int[] array) {
		boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			flag = false;
			for (int j = 1; j < array.length-i; j++) {
				if (array[j-1] % 2 == 0 && array[j] % 2 == 1) {
					flag= true;
					int temp = array[j-1];
					array[j-1]=array[j];
					array[j]=temp;
				}
			}
			if(flag == false)
				break;
		}
		System.out.println(Arrays.toString(array));
	}
	
	@Test
	public void test() {
		int []arr = {1,2,3,4,5,6};
		reOrderArray(arr);
	}

}
