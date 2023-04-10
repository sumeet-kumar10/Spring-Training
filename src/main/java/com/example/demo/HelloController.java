package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public void sayHello() {
		System.out.println("Welcome to spring mvc....");
	}
	
	@RequestMapping(value="hello2",method = RequestMethod.GET)
	public String sayHello2() {
		System.out.println("hello2 method is called...");
		return "welcome2";			
	}
	
	@RequestMapping(value="hello3",method = RequestMethod.GET)
	public ResponseEntity<String> sayHello3(){
		System.out.println("hello3 method is called....");
		return ResponseEntity.ok("welcome to spring mvc3....");
	}
	
	@RequestMapping(value="hello4",method = RequestMethod.GET)
	public ModelAndView sayHello4() {
		ModelAndView mv = new ModelAndView();
		System.out.println("hello4 method is called...");
		mv.addObject("mykey","Welcome to spring mvc4...");
		mv.addObject("hello1","Hello world.......");
		mv.addObject("hello2","Hello world.....Hello world....");
		mv.setViewName("welcome2");
		return mv;
	}
	
	@RequestMapping(value="hello5",method = RequestMethod.GET)
	public ModelAndView sayHello5(ModelAndView mv) {
		System.out.println("hello5 method is called...");
		mv.addObject("mykey","welcome to spring mvc5........");
		mv.setViewName("welcome2");
		return mv;
	}
	
	@RequestMapping(value="hello6",method = RequestMethod.GET)
	public ModelAndView sayHello6(ModelAndView mv,HttpServletRequest request) {
		System.out.println("hello6 method is called...");
		mv.addObject("mykey","welcome to spring mvc6.......from injected object...");
		mv.setViewName("welcome2");
		HttpSession session=request.getSession();
		request.setAttribute("hello1","hello world... hello world... hello world...");
		session.setAttribute("hello2", "this is the session value of hello 6");
		request.setAttribute("hello2", session);
		return mv;
	}
	
	@RequestMapping(value="repeat",method = RequestMethod.GET)
	public ModelAndView repeat(ModelAndView mv,HttpServletRequest request) {
		System.out.println("repeat method is called...");
		mv.addObject("mykey","welcome to spring mvc.....repeat repeat repeat.....");
		mv.setViewName("welcome2");
		request.setAttribute("hello1","this is coming from repeat.....");
		return mv;
	}
	
	@RequestMapping(value="hello7/{name}",method = RequestMethod.GET)
	public ModelAndView sayHello7(@PathVariable String name, ModelAndView mv,HttpServletRequest request) {
		System.out.println("hello7 method is called...");
		mv.addObject("mykey",name);
		mv.setViewName("welcome2");
		request.setAttribute("hello1","Path variable example.....");
		return mv;
	}
	
	@RequestMapping(value="hello8",method = RequestMethod.GET)
	public ModelAndView sayHello8(@RequestParam("uname") String name, ModelAndView mv,HttpServletRequest request) {
		System.out.println("hello8 method is called...");
		mv.addObject("mykey",name);
		mv.setViewName("welcome2");
		request.setAttribute("hello1","this is coming from repeat....."+name);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="hello9")
	public ResponseEntity<User> sayHello9() {
		User user = new User();
		user.setUid(100);
		user.setUname("ramu");
		System.out.println("hello9 method is called...");
		return ResponseEntity.ok(user);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="hello10")
	public ResponseEntity<User> sayHello12(@RequestBody User user)
	{
		user.setUid(200);
		user.setUname("Java");
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(method = RequestMethod.POST,value="hello11")
	public User sayHello11(@RequestBody User user) {
		System.out.println("hello11 method is called...");
		user.setUid(19988);
		user.setUname("Spring Boot");
		return user;
		
	}
}


class User{
	int uid;
	String uname;
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getUid() {
		return uid;
	}

	public String getUname() {
		return uname;
	}	
}
