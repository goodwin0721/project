//大家都知道斐波那契数列，现在要求输入一个整数n，
//请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
package hgy.java.arithmetic;

import org.junit.Test;

public class Fibonacci {
	//递归
	public int fibonacci(int n){
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else
			return fibonacci(n-1)+fibonacci(n-2);
	}
	//迭代
	public int fibonacci2(int n){
		int expre = 0;
		int pre = 1;
		int ans = 0;
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else{
			for(int i = 2;i<=n;i++){
				ans = expre + pre;
				expre = pre;
				pre = ans;
			}
			return ans;
		}
	}
	
	@Test
	public void test(){
		System.out.println(fibonacci(39));
		System.out.println(fibonacci2(39));
	}
}
