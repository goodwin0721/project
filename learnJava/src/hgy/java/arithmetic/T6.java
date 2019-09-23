package hgy.java.arithmetic;

import java.util.Scanner;

public class T6 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sum = in.nextInt();
		int []n = new int[sum];
		for(int i = 0 ;i < n.length;i++){
			n[i] = in.nextInt();
		}
		int min=n[0],max=n[0];
		int temp = n[0];
		int count = 1;
		boolean b = false;
		for(int i = 1 ; i < n.length;i++){
			if(count==1&&min>=n[i]){
				min = n[i];
				max = n[i];
				temp=n[i];
			}else{
				if(n[i]>temp){
					temp = n[i];
					count++;
					max = temp;
				}
				if(min<n[i]&&n[i]<max){
					temp = n[i];
					max = temp;
				}
			}
		}
		if(count>=3)
			b= true;
		System.out.println(count);
		System.out.println(b);
	}
}
