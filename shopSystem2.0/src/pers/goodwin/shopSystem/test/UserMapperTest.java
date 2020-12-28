package pers.goodwin.shopSystem.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.mapper.UserMapper;
import pers.goodwin.shopSystem.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserMapperTest {
	@Resource(name = "userMapper")
	UserMapper userMapper;
	@Test
	public void test_addUser() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userMapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = new User();
		user.setUsername("jeck");
		user.setPassword("123");
		user.setBirthdate("2012-12-12");
		user.setGender(0);
		userMapper.addUser(user);
	}
	
	@Test
	public void test_addManager() {
		userMapper.addManager(11);
	}
	
	@Test
	public void test_getUserByPassword() {
		User user = new User();
		user.setUsername("jeck");
		user.setPassword("12311");
		user.setBirthdate("2012-12-12");
		user.setGender(0);
		User u = userMapper.getUserByPassword(user);
		System.out.println(u);
	}

	@Test
	public void test_isManager() {
		User user = new User();
		user.setUsername("马云");
		user.setPassword("123456");
		User u = userMapper.getUserByPassword(user);
		Integer flag= userMapper.isManager(u);
		System.out.println(flag);
	}
}
