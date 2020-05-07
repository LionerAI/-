package com.cloud.entity;

import java.util.List;

public interface FolderMapper
{
	//增加文件夹
	public void addFolder(Folder folder);
	
	//增加根文件夹，仅在用户注册时使用
	public void addRootFolder(User user);
	
	//根据父文件夹ID选取出子文件夹
	public List<Folder> selectFolderListByFolderId(Integer folderId);
	
	/*根据用户ID选取出根文件夹*/
	public Folder selectRootFolderByUserId(Integer userId);
	
	//根据文件夹ID获取文件夹
	public Folder selectFolderById(Integer folderId);
	
	//根据子文件夹的父文件夹ID列查找父文件夹
	public Folder selectFatherFolderById(Integer folderId);
	
	//根据文件夹ID删除该文件夹
	public void deleteFolderById(Integer id);
	
	//根据父文件夹筛选出其中所有的文件夹
	public List<Folder> selectAllFolderByFatherFolder(Folder fatherFolder);
	
}
