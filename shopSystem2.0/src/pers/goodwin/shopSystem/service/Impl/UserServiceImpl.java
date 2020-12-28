package pers.goodwin.shopSystem.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import pers.goodwin.shopSystem.mapper.UserMapper;
import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.UserService;

public class UserServiceImpl implements UserService {
	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public boolean register(String username, String password, String birthdate, String gender) {
		if(username == null || username.equals("") || password == null || password.equals(""))
			return false;
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userMapper.getUserByPassword(user);
		if(userMapper.getUserByPassword(user) != null)
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
		user.setBirthdate(date);
		user.setGender(genderNumb);
		userMapper.addUser(user);
		return true;
	}
	
	@Override
	public User login(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userMapper.getUserByPassword(user);
		return userMapper.getUserByPassword(user);
	}

	@Override
	public boolean isManager(User user) {
		if(user == null || userMapper.isManager(user) == null)
			return false;
		if(userMapper.isManager(user) > 0)
			return true;
		return false;
	}

}
