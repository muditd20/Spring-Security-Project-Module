package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smart.SmartcontactmanagerApplication;
import com.smart.dao.UserRepository;
import com.smart.entities.User;

@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private final SmartcontactmanagerApplication smartcontactmanagerApplication;
	@Autowired
	private UserRepository userRepository;

	HomeController(SmartcontactmanagerApplication smartcontactmanagerApplication) {
		this.smartcontactmanagerApplication = smartcontactmanagerApplication;
	}

//	@RequestMapping("/test")
//	@ResponseBody
//	public String test()
//	{
//		User user = new User();
//		user.setName("mudit daga");
//		user.setEmail("muditdaga07@gmail.com");
//		userRepository.save(user);
//		return "working";
//	}

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-Smart Contact Manager");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "SignUp-Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	// handler for registering user
	@RequestMapping(value="/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,Model model)
	{
		user.setRole("Role_user");
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User result = userRepository.save(user);
		System.out.println("user" + result);
		return "signup";
	}

}
