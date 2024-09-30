package com.bolsadeideas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.models.service.IUserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("title", "List of Users");
		model.addAttribute("users", userService.findAllUsers());
		return "list";
	}
	
	@GetMapping("/form")
	public String create(Model model) {
		
		User user = new User();
		model.addAttribute("title", "User's Form");
		model.addAttribute("user", user);
		
		return "form";
	}
	
	@GetMapping("/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		User user = null;
		if(id > 0) {
			user = userService.findUser(id);
			if (user == null) {
				flash.addFlashAttribute("error", "User id doesn't exist in the DB!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "User id can't be zero!");
			return "redirect:/listar";
		}
		model.addAttribute("title", "Edit user");
		model.addAttribute("user", user);
		
		return "form";
	}
	
	@PostMapping("/form")
	// Con @Valid habilitamos las validaciones de nuestra clases POJO (@NotNull, @NotEmpty, etc)
	// Con BindingResult (tiene que ir PEGADO al objeto User) preguntamos si hay errores	
	public String save(@Valid User user, BindingResult result, Model model, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "User's Form");
			return "form";
		}
		String messageFlash = (user.getId() != null)? "User edited successfully!" : "User created successfully!";
		
		userService.saveUser(user);
		
		flash.addFlashAttribute("success", messageFlash);
		
		return "redirect:/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			userService.deleteUser(id);
			flash.addFlashAttribute("success", "User deleted successfully!");
		}
		
		return "redirect:list";
	}

}
