package hgy.java.arithmetic;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String str = in.nextLine();
			String[]strings = str.split(" ");
			for(int i = strings.length-1 ; i >= 0;i--){
				if(i < strings.length-1)
					System.out.print(" ");
				System.out.print(strings[i]);
			}
		}
	}
}
