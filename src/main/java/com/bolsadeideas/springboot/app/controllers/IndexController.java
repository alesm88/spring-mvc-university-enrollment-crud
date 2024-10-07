package com.bolsadeideas.springboot.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Role;
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
		
		model.addAttribute("title", "Main Page");
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
		User user = new User();
		model.addAttribute("title", "Login");
		model.addAttribute("roles", Role.values());
		model.addAttribute("user", user);
		
		return "login";
	}
	
	@PostMapping("/login")
	public String validateLogin(HttpSession session, @Valid User user, BindingResult result, Model model, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Login");
			model.addAttribute("roles", Role.values());
			return "login";
		}

		Optional<User> userOptional = userService.login(user.getUsername(), user.getPassword(), user.getRole());
		if (userOptional.isEmpty()) {
			flash.addFlashAttribute("error", "Username, password and/or role incorrect!");
			return "redirect:/login";
		}
		
		session.setAttribute("role", user.getRole());
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
