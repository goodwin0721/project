package pers.goodwin.shopSystem.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import pers.goodwin.shopSystem.dao.UserDao;
import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.UserService;

public class UserServiceImpl implements UserService {
	private ApplicationContext context;
	
	@Override
	public boolean register(String username, String password, String birthdate, String gender) {
		boolean isSuccess = false;
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		if(username == null || username.equals("") || password == null || password.equals(""))
			return false;
		if(userDao.isExist(username))
			return false;
		String date = birthdate;
		if(birthdate == null || birthdate.equals("")) {
			Date today = new Date();
			//格式化出生日期
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = dateFormat.format(today);		
		}
		int genderNumb = 0;//默认男
		genderNumb = gender.equals("女") ? 1 : 0;//不是女人，默认男人
//		System.out.println("username : " + username);
//		System.out.println("password : " + password);
//		System.out.println("birthdate : " + date);
//		System.out.println("genderNumb : " + genderNumb);
		isSuccess = userDao.addUser(username, password, date, genderNumb);
		return isSuccess;
	}
	
	@Override
	public User login(String username, String password) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		User user;
		try {
			user = userDao.getUserByPassword(username, password);
			
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	@Override
	public boolean isManager(String username) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		return userDao.isManager(username);
	}

}
