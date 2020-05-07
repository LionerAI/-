package com.cloud.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.entity.CloudFile;
import com.cloud.entity.CloudFileMapper;
import com.cloud.entity.Folder;
import com.cloud.entity.FolderMapper;
import com.cloud.entity.User;
import com.post.util.MyBatisUtil;

@Controller
@RequestMapping(value="/main")
public class ViewController
{
	@RequestMapping(value="")
	public ModelAndView mainPage(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		User user = (User)session.getAttribute("user");
		if(user == null)
			mav.setViewName("/index.jsp");
		else
			mav.setViewName("/WEB-INF/view/main.jsp");
		return mav;
	}
	
	@RequestMapping(value="/file")
	public ModelAndView filePage(@RequestParam("dir") Integer folderId, Model model, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			FolderMapper folderMapper = sqlSession.getMapper(FolderMapper.class);
			CloudFileMapper cloudFileMapper = sqlSession.getMapper(CloudFileMapper.class);
			List<Folder> folderList;
			List<CloudFile> fileList;
			if(folderId == 0)
			{
				User user = (User)session.getAttribute("user");
				if(user == null)
				{
					mav.setViewName("/index.jsp");
					return mav;
				}
				Folder rootFolder = folderMapper.selectRootFolderByUserId(user.getId());
				folderList = folderMapper.selectFolderListByFolderId(rootFolder.getId());
				fileList = cloudFileMapper.selectFileListByFolderId(rootFolder.getId());
			}
			else
			{
				folderList = folderMapper.selectFolderListByFolderId(folderId);
				fileList = cloudFileMapper.selectFileListByFolderId(folderId);
				Folder fatherFolder = folderMapper.selectFatherFolderById(
						folderMapper.selectFolderById(folderId).getParentFolder());
				model.addAttribute("fatherFolder",fatherFolder);
			}
			model.addAttribute("folderId",folderId);
			model.addAttribute("folderList",folderList);
			model.addAttribute("fileList",fileList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sqlSession.close();
		}
	
		mav.setViewName("/WEB-INF/view/file.jsp");
		return mav;
	}
	
	@RequestMapping(value="/class")
	public ModelAndView photoPage(@RequestParam("type")String type,Model model, HttpSession session)
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		User user = (User)session.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		try
		{
			if(user == null)
			{
				mav.setViewName("/index.jsp");
				return mav;
			}
			CloudFileMapper cloudFileMapper = sqlSession.getMapper(CloudFileMapper.class);
			List<CloudFile> fileList = null;
			if(type.equals("photo"))
				fileList = cloudFileMapper.selectPhotoByUserId(user.getId());
			else if(type.equals("document"))
				fileList = cloudFileMapper.selectDocumentByUserId(user.getId());
			else if(type.equals("movie"))
				fileList = cloudFileMapper.selectMovieByUserId(user.getId());
			else if(type.equals("music"))
				fileList = cloudFileMapper.selectMusicByUserId(user.getId());
			else if(type.equals("zip"))
				fileList = cloudFileMapper.selectZipByUserId(user.getId());
			model.addAttribute("fileList",fileList);
			mav.setViewName("/WEB-INF/view/class.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sqlSession.close();
		}
		return mav;
	}
	
	@RequestMapping("/public")
	public ModelAndView publicPage(Model model)
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			CloudFileMapper cloudFileMapper = sqlSession.getMapper(CloudFileMapper.class);
			List<CloudFile> fileList = cloudFileMapper.selectPublicFile();
			model.addAttribute("fileList",fileList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sqlSession.close();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/WEB-INF/view/public.jsp");
		return mav;
	}
}
