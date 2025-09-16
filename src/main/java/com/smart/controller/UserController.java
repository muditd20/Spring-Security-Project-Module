package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.config.MyConfig;
import com.smart.dao.UserRepository;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDetailsService getUserDetailsService;

    private final MyConfig myConfig;
	@Autowired
	private UserRepository userRepository;

	private final HomeController homeController;

	UserController(HomeController homeController, MyConfig myConfig, UserDetailsService getUserDetailsService) {
		this.homeController = homeController;
		this.myConfig = myConfig;
		this.getUserDetailsService = getUserDetailsService;
	}

	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME" + userName);

		User user = userRepository.getUserbyName(userName);
		System.out.println("USER" + user);

			model.addAttribute("user", user);
		return "normal/user_dashboard";
	}

}
