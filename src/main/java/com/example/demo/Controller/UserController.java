package com.example.demo.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.Util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;	
import com.example.demo.service.UserServiceImpl;

import java.util.Random;

@EnableCaching
@ComponentScan
@Controller
//@SessionAttributes("sessusername")
public class UserController {
	@Autowired
	UserServiceImpl userService;

	public static final ThreadLocal<HttpSession> threadLocal = new ThreadLocal<HttpSession>();

	@RequestMapping(value = "/userlogin",method=RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("userName")String username, @RequestParam("userPwd")String userpwd,Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		threadLocal.set(session);
		threadLocal.get().setAttribute("sessusername",username);
		boolean flag = userService.checkPass(username,userpwd);
		String status = null;
		if (flag) {
			status = "成功";
			ModelAndView modelAndView = new ModelAndView("home/home");
			modelAndView.addObject("status", status);
			User user = userService.selectUser(username);
			threadLocal.get().setAttribute("user",user);
			return modelAndView;
//			return "home/home.html";
		}else {
			status = "用户名或密码错误";
			ModelAndView modelAndView = new ModelAndView("login-signin/login");
			modelAndView.addObject("status", status);
			return modelAndView;
//			return "login-signin/login.html";
		}
	}

	@RequestMapping(value = "/userregister",method=RequestMethod.POST)
	public String userRegister(HttpServletRequest request,Model model){
		String username = request.getParameter("nickName");
		String userpwd = request.getParameter("password");
		String email = request.getParameter("email");
		String email_code = request.getParameter("emailcode");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String photo = request.getParameter("photo");
		if (threadLocal.get() == null){
			HttpSession session = request.getSession();
			threadLocal.set(session);
		}
		String mailcode = (String) threadLocal.get().getAttribute("mailcode");
		if (mailcode != null){
			if (!mailcode.equals(email_code)){
				model.addAttribute("signStatus","邮箱验证码错误");
				return "login-signin/signin.html";
			}
		}
		userService.register(username, userpwd, email, question, answer, photo);
		return "login-signin/login";
	}

	@RequestMapping(value = "/usersetting",method=RequestMethod.POST)
	public String userSetting(Model model,HttpServletRequest request){
		if (threadLocal.get() == null){
			HttpSession session = request.getSession();
			threadLocal.set(session);
		}
		String username = (String) threadLocal.get().getAttribute("sessusername");
		System.out.println("设置 "+username);
		String userpwd = request.getParameter("password");
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		int flag = userService.setting(username,userpwd,email,question,answer);
		String setStatus = null;
		if (flag>0){
			setStatus = "修改成功";

		}else {
			setStatus = "修改失败";
		}
		model.addAttribute("setStatus",setStatus);
		return "setting/setting";
//        ModelAndView modelAndView = new ModelAndView("/setting/setting.html",model.asMap());
//        return modelAndView;
	}


	@RequestMapping("/sendEmail")
	@ResponseBody
	public void sendEmail(HttpServletRequest request){
		String mail = request.getParameter("email");
		String code = "";
		for(int i = 0;i<4;i++){
			Random random = new Random();
			code += random.nextInt(9);
		}
		if (threadLocal.get() == null){
			HttpSession session = request.getSession();
			threadLocal.set(session);
		}
		threadLocal.get().setAttribute("mailcode", code);
		try {
			MailUtils.sendMail(mail, code);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/home")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String page2(){
		return "home/home";
	}
	@RequestMapping("/home2")
	public String page3(){
		return "home/home2";
	}
	@RequestMapping("/share")
	public String page4(){
		return "share/share";
	}
	@RequestMapping("/thoughts")
	public String page5(){
		return "thoughts/thoughts";
	}
	@RequestMapping("/home4")
	public String page6(){
		return "home/home4";
	}
	@RequestMapping("/shop")
	public String page7(){
		return "shop/shop";
	}
	@RequestMapping("/setting")
	public String page8(){
		return "setting/setting";
	}
	@GetMapping("/login")
	public String page9(Model model, @RequestParam(value = "error", required = false) String error){
		if (error != null) {
			model.addAttribute("status", "用户名或密码错误");
		}
		return "login-signin/login";
	}
	@RequestMapping("/signin")
	public String page10(){
		return "login-signin/signin";
	}

    @RequestMapping("/adminHome")
    public String page11() {
        return "home/adminHome";
    }

	@RequestMapping("/gotoreleaseNotice")
	public String page12() {
		return "notice/releaseNotice";
	}

}
