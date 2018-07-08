package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Diary;
import com.example.demo.match.APItest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.UserServiceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableCaching
@Controller
public class ShareController {
	@Autowired
	UserServiceImpl userService;

	public static final ThreadLocal<HttpSession> threadLocal = new ThreadLocal<HttpSession>();

	@RequestMapping(value = "/usershare",method=RequestMethod.POST)
	public String share(Model model,HttpServletRequest request){
		if (threadLocal.get() == null){
			HttpSession session = request.getSession();
			threadLocal.set(session);
		}
		String username = (String)threadLocal.get().getAttribute("sessusername");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		userService.share(username,title, content);
		//System.out.println(title+content);
		return "/share/share.html";
	}

	@RequestMapping(value = "/seeShare",method=RequestMethod.POST)
	public String seeshare(Model model,HttpServletRequest request){
		List<Diary> list = new LinkedList<Diary>();
		list = userService.seeShare();
		model.addAttribute("list",list);
		return "/share/seeShare.html";
	}

	@RequestMapping(value = "/gotoComment",method=RequestMethod.POST)
	public String gotoComment(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		Diary diary = new Diary();
        String diaryTitle = URLDecoder.decode(request.getParameter("diaryTitle"),"utf-8");
        String diaryContent = URLDecoder.decode(request.getParameter("diaryContent"),"utf-8");
        String diaryTime = URLDecoder.decode(request.getParameter("diaryTime"),"utf-8");
        String diaryUser = URLDecoder.decode(request.getParameter("diaryUser"),"utf-8");
        String diaryID = request.getParameter("diaryID");
        diary.setTitle(diaryTitle);
        diary.setContent(diaryContent);
        diary.setWriteTime(diaryTime);
        diary.setUsername(diaryUser);
        diary.setId(Integer.parseInt(diaryID));
		if (threadLocal.get() == null){
			HttpSession session = request.getSession();
			threadLocal.set(session);
		}
		threadLocal.get().setAttribute("diary2",diary);

		List<Comment> commentList= new ArrayList<>();
		commentList = userService.seeComment(diary.getId());
		threadLocal.get().setAttribute("commentList",commentList);
		return "/share/comment.html";
	}

    @RequestMapping(value = "/comment",method=RequestMethod.POST)
    public String comment(Model model, HttpServletRequest request){
		if (threadLocal.get() == null){
			HttpSession session = request.getSession();
			threadLocal.set(session);
		}
		String username = (String)threadLocal.get().getAttribute("sessusername");
        Comment comment = new Comment();
        comment.setContent(request.getParameter("diarycontent"));
        comment.setCommentDiaryID(request.getParameter("diaryID"));
        comment.setCommentUsername(username);
        comment.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
        System.out.println(comment.getContent()+comment.getCommentDiaryID());
        userService.insertComment(comment);
        return "/share/comment";
    }

}
