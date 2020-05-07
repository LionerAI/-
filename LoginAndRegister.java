package com.cloud.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.entity.FolderMapper;
import com.cloud.entity.User;
import com.cloud.entity.UserMapper;
import com.post.util.MyBatisUtil;
import com.post.util.SystemConstant;

@Controller
public class LoginAndRegister
{
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String Login(@RequestParam("username") String username,@RequestParam("password") String password,
			HttpSession session, Model model, HttpServletRequest request) throws IOException
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		String sysMsg = "";
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.selectUserByUsername(username);
			if(user == null || !user.getPassword().equals(password))
			{
				sysMsg = "用户名或密码错误";
				return "/index.jsp";
			}
			else
			{
				if(user.getStatus() == User.STATUS_NEW)
				{
					FolderMapper folderMapper = sqlSession.getMapper(FolderMapper.class);
					folderMapper.addRootFolder(user);
					
					String path = SystemConstant.USER_FILE_PATH;
					File folder = new File(path + "\\" + user.getUsername());
					if(!folder.exists())
						folder.mkdirs();
					
					userMapper.changeUserStatus(user.getId());
					sqlSession.commit();
				}
				session.setAttribute("user", user);				
				return "/main";
			}
		}
		catch(Exception e)
		{
			sysMsg = "系统异常";
			sqlSession.rollback();
			e.printStackTrace();
		}
		finally
		{
			model.addAttribute("sysMsg", sysMsg);
			sqlSession.close();
		}
		return "/main";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String Register(@RequestParam("username") String username,@RequestParam("password") String password,
			@RequestParam("rePassword")String rePassword,@RequestParam("email") String email, Model model) throws IOException
	{
		if(username == null || username.equals(""))
		{
			model.addAttribute("sysMsg","请填写用户名");
			return "/index.jsp";
		}
		if(password == null || password.equals(""))
		{
			model.addAttribute("sysMsg","请填写密码");
			return "/index.jsp";
		}
		if(rePassword == null || rePassword.equals(""))
		{
			model.addAttribute("sysMsg","请填写确认密码");
			return "/index.jsp";
		}
		if(email == null || email.equals(""))
		{
			model.addAttribute("sysMsg","请填写邮箱");
			return "/index.jsp";
		}
		if(username.length() < 5 || username.length() > 30)
		{
			model.addAttribute("sysMsg","用户名应在5~30字符内");
			return "/index.jsp";
		}
		if(password.length() < 5 || password.length() > 30)
		{
			model.addAttribute("sysMsg","密码应在5~30字符内");
			return "/index.jsp";
		}
		if(!password.equals(rePassword))
		{
			model.addAttribute("sysMsg","密码与确认密码不符");
			return "/index.jsp";
		}
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		String sysMsg = "";
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User(username, password, email);
			userMapper.addUser(user);
			sqlSession.commit();
			
			sysMsg = "注册成功";
		}
		catch(Exception e)
		{
			sysMsg = "系统异常";
			sqlSession.rollback();
			e.printStackTrace();
		}
		finally
		{
			model.addAttribute("sysMsg",sysMsg);
			sqlSession.close();
		}
		return "/index.jsp";
	}
}
