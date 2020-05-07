package com.cloud.entity;

public interface UserMapper 
{
	//新建用户
	public void addUser(User user);
	
	//通过用户名查找用户
	public User selectUserByUsername(String username);
	
	//更改用户状态
	public void changeUserStatus(Integer userId);
}
