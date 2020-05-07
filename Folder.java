package com.cloud.entity;

import java.io.Serializable;

public class Folder implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer parentFolder;
	private String path;
	
	private User createUser;

	public Folder() {}
	
	public Folder(User createUser)
	{
		this.createUser = createUser;
	}
	
	public Folder(String name,Integer parentFolder,User createUser,String path)
	{
		this(createUser);
		this.name = name;
		this.parentFolder = parentFolder;
		this.path = path;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Integer parentFolder) {
		this.parentFolder = parentFolder;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString()
	{
		return "文件夹名：" + name + " 父文件夹id:" + parentFolder + " 路径：" + path + " 用户：" + createUser.getUsername();
	}
}
