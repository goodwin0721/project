package pers.goodwin.shopSystem.test;

import org.junit.Test;

import pers.goodwin.shopSystem.dao.ClassifyDao;
import pers.goodwin.shopSystem.dao.Impl.ClassifyDaoImpl;

public class ClassifyDaoImplTest {
	@Test
	public void increaseSumTest() {
		ClassifyDao cls = new ClassifyDaoImpl();
		//cls.increaseSum("水果", 10);
		cls.increaseSum("水果", -5);
	}
	
	@Test
	public void getClassifyIdTest() {
		ClassifyDao cls = new ClassifyDaoImpl();
		System.out.println(cls.getClassifyId("水果"));
	}
}
