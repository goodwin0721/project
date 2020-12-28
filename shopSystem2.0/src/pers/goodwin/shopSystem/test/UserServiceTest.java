package pers.goodwin.shopSystem.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {
	@Resource(name = "userService")
	private UserService userService;
	
	@Test
	public void test_register() {
		System.out.println(userService.register("rose", "666", "2010-10-1", "女"));
	}
	
	@Test
	public void test_login() {
		System.out.println(userService.login("rose", "111"));
	}
	
	@Test
	public void test_isManager() {
		User user = new User();
		user.setId(1);
		System.out.println(userService.isManager(user));
	}
}
