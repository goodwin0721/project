package pers.goodwin.shopSystem.dao;


import pers.goodwin.shopSystem.model.User;

public interface UserDao {
	public User getUserByPassword(String username,String password);//获得用户实例
	public boolean addUser(String username,String password,String birthdate,int gender);//增加用户
	public boolean addManager(String username);//增加管理员
	public boolean isExist(String username);//判断用户是否存在
	public boolean isManager(String username);//判断用户是否是管理员   
}
