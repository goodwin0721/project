package pers.goodwin.shopSystem.service;

import pers.goodwin.shopSystem.pojo.User;

public interface UserService {
	public boolean register(String username,String password,String birthdate,String gender);
	public User login(String username,String password);
	public boolean isManager(User user);
	public boolean isExist(String username);
}
