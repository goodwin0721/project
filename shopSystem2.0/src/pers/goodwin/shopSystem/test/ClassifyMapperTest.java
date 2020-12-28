package pers.goodwin.shopSystem.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.mapper.ClassifyMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ClassifyMapperTest {
	@Resource(name = "classifyMapper")
	ClassifyMapper classifyMapper;
	@Test
	public void test_getClassifyId() {
		System.out.println(classifyMapper.getClassifyId("水果"));
	}
	
	@Test
	public void test_getSumByClassify() {
		System.out.println(classifyMapper.getSumByClassify("水果"));
	}
	
	@Test
	public void test_increaseSumById() {
		classifyMapper.increaseSumById(70, 10);
	}
}
