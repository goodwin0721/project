package pers.goodwin.shopSystem.service;

import pers.goodwin.shopSystem.model.User;

public interface UserService {
	public boolean register(String username,String password,String birthdate,String gender);
	public User login(String username,String password);
	public boolean isManager(String username);
}
