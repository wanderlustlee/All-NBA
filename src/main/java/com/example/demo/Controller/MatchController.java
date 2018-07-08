package com.example.demo.Controller;

import com.example.demo.entity.Diary;
import com.example.demo.match.APItest;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@EnableCaching
@Controller
public class MatchController {
	@Autowired
	UserServiceImpl userService;

	public static final ThreadLocal<HttpSession> threadLocal = new ThreadLocal<HttpSession>();

	@RequestMapping(value = "/match",method = RequestMethod.GET)
	public String seeMatch(Model model,HttpServletRequest request){
		List matchlist = APItest.getRequest1();
		HttpSession session = request.getSession();
		threadLocal.set(session);
		threadLocal.get().setAttribute("matchlist",matchlist);
		model.addAttribute("pane1","tab-pane active");
		model.addAttribute("pane2","tab-pane");
		model.addAttribute("pane3","tab-pane");
		return "matchAPI/matchSearch";
	}

	@RequestMapping(value = "/teammatch",method = RequestMethod.POST)
	public String seeTeammatch(@RequestParam("teamname") String teamname,Model model){
		List teammatchList = APItest.getRequest2(teamname);
		model.addAttribute("teammatchList",teammatchList);
		model.addAttribute("pane1","tab-pane");
		model.addAttribute("pane2","tab-pane active");
		model.addAttribute("pane3","tab-pane");
		return "matchAPI/matchSearch";
	}

	@RequestMapping(value = "/teampk",method = RequestMethod.POST)
	public String seeTeampk(@RequestParam("teamname1") String teamname1,@RequestParam("teamname2") String teamname2,Model model){
		List teampkList = APItest.getRequest3(teamname1,teamname2);
		model.addAttribute("teampkList",teampkList);
		model.addAttribute("pane1","tab-pane");
		model.addAttribute("pane2","tab-pane");
		model.addAttribute("pane3","tab-pane active");

		return "matchAPI/matchSearch";
	}
}
