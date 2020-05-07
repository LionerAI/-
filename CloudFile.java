package com.cloud.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.post.util.SystemTime;

public class CloudFile implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int STATUS_PRIVATE = 0;
	public static final int STATUS_PUBLIC = 1;
	
	private Integer id;
	private String name;
	private Integer size;
	private String fileType;
	private Timestamp uploadTime;
	private Integer status;
	
	private Folder fatherFolder;
	private User uploadUser;
	
	public CloudFile() 
	{
		this.status = STATUS_PRIVATE;
		this.uploadTime = SystemTime.getTime();
	}
	
	public CloudFile(String name, Integer size, String fileType, Folder fatherFolder, User uploadUser)
	{
		this();
		this.name = name;
		this.size = size;
		this.fileType = fileType;
		this.fatherFolder = fatherFolder;
		this.uploadUser = uploadUser;
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
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public User getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(User uploadUser) {
		this.uploadUser = uploadUser;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Folder getFatherFolder() {
		return fatherFolder;
	}

	public void setFatherFolder(Folder fatherFolder) {
		this.fatherFolder = fatherFolder;
	}
	
}
