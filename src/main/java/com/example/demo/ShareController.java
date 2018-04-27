package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.entity.Diary;
import com.example.demo.match.APItest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
@EnableCaching
@Controller
public class ShareController {
	@Autowired
	UserServiceImpl userService;
	@RequestMapping(value = "/usershare",method=RequestMethod.POST)
	public String share(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
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
//		Iterator<Diary> it = list.iterator();
//		while (it.hasNext()){
//			Diary diary = it.next();
//			System.out.println(diary.getContent());
//		}
		return "/share/seeShare.html";
	}

	@RequestMapping(value = "/match",method = RequestMethod.GET)
	public String seeMatch(Model model){
		List matchlist = APItest.getRequest1();
		model.addAttribute("matchlist",matchlist);
		return "matchAPI/matchSearch";
	}

	@RequestMapping(value = "/teammatch",method = RequestMethod.POST)
	public String seeTeammatch(@RequestParam("teamname") String teamname,Model model){
		List teammatchList = APItest.getRequest2(teamname);
		model.addAttribute("teammatchList",teammatchList);
		return "matchAPI/matchSearch";
	}

	@RequestMapping(value = "/teampk",method = RequestMethod.POST)
	public String seeTeampk(@RequestParam("teamname1") String teamname1,@RequestParam("teamname2") String teamname2,Model model){
		List teampkList = APItest.getRequest3(teamname1,teamname2);
		model.addAttribute("teampkList",teampkList);
		return "matchAPI/matchSearch";
	}
}
