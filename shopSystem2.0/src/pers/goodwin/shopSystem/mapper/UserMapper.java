package pers.goodwin.shopSystem.mapper;


import pers.goodwin.shopSystem.model.User;

public interface UserMapper {
	public User getUserByPassword(User user);//获得用户实例
	public void addUser(User user);//增加用户
	public void addManager(Integer userId);//增加管理员
	public Integer isManager(User user);//判断用户是否是管理员   
}
