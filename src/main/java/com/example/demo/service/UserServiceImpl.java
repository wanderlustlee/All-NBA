package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAOImpl;
import com.example.demo.entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;
	public boolean checkPass(String username,String pwd){
		return userDAO.checkPass(username, pwd);
	}
	public void register(String username,String userpwd,String email,String question,String answer,String photo){
		userDAO.register(username, userpwd, email, question, answer, photo);
	}
	public void share(String username,String title,String content){
		userDAO.share(username,title, content);
	}
	public User selectUser(String username){
		return userDAO.selectUser(username);
	}
	public boolean checkUsername(String username){
		return userDAO.checkUsername(username);
	}
	public List<Diary> seeShare(){
		String time=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
		return userDAO.seeShare(time);
	}
	public int setting(String username,String userpwd,String email,String question,String answer){
		return userDAO.setting(username,userpwd,email,question,answer);
	}
}
