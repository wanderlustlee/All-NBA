package com.example.demo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Notice;
import com.example.demo.map.CommentMapper;
import com.example.demo.map.NoticeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Diary;
import com.example.demo.entity.User;
import com.example.demo.map.DiaryMapper;
import com.example.demo.map.UserMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames="userCache") // 本类内方法指定使用缓存时，默认的名称就是userCache
public class UserDAOImpl implements UserDAO{
	
	String resource = null;
	InputStream is = null;
	SqlSessionFactory sqlSessionFactory = null;
	SqlSession sqlSession = null;
	int count = 0;

	public UserDAOImpl() throws IOException {
		resource = "sqlMapConfig.xml";
		is = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		sqlSession = sqlSessionFactory.openSession();
		count++;
		System.out.println(count);
	}
	//检查注册时用户名是否可用
	public boolean checkUsername(String username) {
		boolean flag = true;
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		String name = userMapper.selectPwdByUsername(username);
		sqlSession.commit();
		if (name==username) {
			flag = true;
		}
		return flag;
	}
	//登录时密码验证
	@Cacheable()
	public boolean checkPass(String username,String pwd){
		boolean flag = false;
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		String pass = userMapper.selectPwdByUsername(username);
		System.out.println("查询数据库");
		sqlSession.commit();
		if(pass != null){
			if (pass.equals(pwd)) {
				flag = true;
			}
			
		}else{
			
		}
		return flag;
	}
	//注册
	@CachePut()
	public void register(String username,String userpwd,String email,String question,String answer,String photo){
		User user = new User();
		user.setUsername(username);
		user.setPwd(userpwd);
		user.setEmail(email);
		user.setQuestion(question);
		user.setAnswer(answer);
		user.setImage(photo);
		user.setRole("ROLE_USER");
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		try {
			int flag = userMapper.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSession.commit();

	}
	//修改
	public int setting(String username,String userpwd,String email,String question,String answer){
		User user = new User();
		user.setUsername(username);
		user.setPwd(userpwd);
		user.setEmail(email);
		user.setQuestion(question);
		user.setAnswer(answer);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int flag = 0;
		try {
			flag = userMapper.updateUser(username,userpwd,email,question,answer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSession.commit();
		return flag;
	}
	//分享
	@CachePut(key = "#title")
	public void share(String username,String title,String content){
		String time=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
		Diary diary = new Diary();
		diary.setTitle(title);
		diary.setContent(content);
		diary.setWriteTime(time);
		//User user = new User();
		diary.setUsername(username);
		DiaryMapper diaryMapper=sqlSession.getMapper(DiaryMapper.class);
		try {
			int flag = diaryMapper.insertDiary(diary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSession.commit();
	}

	//@Cacheable(key = "#username") // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
	public User selectUser(String username){
		User user = null;
		System.out.println("Select User! "+username);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		user = userMapper.selectUser(username);
		System.out.println(user.getUsername());
		sqlSession.commit();
		return user;
	}
	@Cacheable(key = "#time") // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    public List<Diary> seeShare(String time) {

		List<Diary> list = new LinkedList<Diary>();
		DiaryMapper diaryMapper = sqlSession.getMapper(DiaryMapper.class);
		list = diaryMapper.selectShare();
		sqlSession.commit();
		return list;
    }

	public List<Comment> seeComment(int commentDiaryID) {
		List<Comment> list = new LinkedList<>();
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		list = commentMapper.selectComment(commentDiaryID);
		sqlSession.commit();
		return list;
	}

	public int insertComment(Comment comment) {
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		int total = 0;
		try {
			total = commentMapper.insertComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqlSession.commit();
		return total;
	}

	//发布公告
	@Override
	public void releaseNotice(String notice) {
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
		Notice noticeObj = new Notice();
		noticeObj.setNotice(notice);
		noticeObj.setReleaseTime(time);
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		try {
			noticeMapper.insertNotice(noticeObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqlSession.commit();
	}

	//查询公告
	@Override
	public List<Notice> seeNotice() {
		List<Notice> Noticelist = new ArrayList<>();
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		Noticelist = noticeMapper.selectNotice();
		sqlSession.commit();
		return Noticelist;
	}
}
