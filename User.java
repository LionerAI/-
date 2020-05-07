package com.cloud.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.post.util.SystemTime;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int STATUS_BAN = 0;
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_NEW = 2;
	
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp registerTime;
	private Integer status;
	
	public User() 
	{
		this.registerTime = SystemTime.getTime();
		this.status = STATUS_NEW;
	}
	
	public User(String username, String password, String email) 
	{
		this();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
}
