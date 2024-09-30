package com.bolsadeideas.springboot.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.models.service.IUserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class IndexController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping({"/", "/index"})
	public String index(HttpSession session, Model model) {
		/*
		User user = new User();
		if (session.getAttribute("username") != null) {
			user = (User) session.getAttribute("username");
		} else {
			user.setUsername("empty");
		}
		*/
		model.addAttribute("title", "Main Page");
		// model.addAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
		User user = new User();
		model.addAttribute("title", "Login");
		model.addAttribute("user", user);
		
		return "login";
	}
	
	@PostMapping("/login")
	public String validateLogin(HttpSession session, @Valid User user, BindingResult result, Model model, RedirectAttributes flash) {
		
		// User user2 = userService.findUserByName(user.getUsername());
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Login");
			return "login";
		}
		/*
		if (user2 == null) {
			flash.addFlashAttribute("error", "Username doesn't exist!");
			return "redirect:/login";
		}
		
		if ( !(user2.getUsername().equals(user.getUsername())) || 
				!(user2.getPassword().equals(user.getPassword())) || 
				!(user2.getRole().equals(user.getRole())) ) {
			flash.addFlashAttribute("error", "Username, password and/or role incorrect!");
			return "redirect:/login";
		}
		*/
		Optional<User> userOptional = userService.login(user.getUsername(), user.getPassword(), user.getRole());
		if (userOptional.isEmpty()) {
			flash.addFlashAttribute("error", "Username, password and/or role incorrect!");
			return "redirect:/login";
		}
		
		session.setAttribute("username", user);
		flash.addFlashAttribute("success", "Welcome " + user.getUsername());
		return "redirect:/menu";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/menu")
	public String menu(HttpSession session, Model model) {
		model.addAttribute("title", "Menu");
		
		return "menu";
	}

}
