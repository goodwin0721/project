package pers.goodwin.shopSystem.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {
	int i;
	@Test
	public void Demo1(){
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = dateFormat.format(new Date());
		System.out.println(date2);
	}
	
}
