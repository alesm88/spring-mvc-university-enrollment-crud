package com.bolsadeideas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Student;
import com.bolsadeideas.springboot.app.models.service.IStudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("title", "List of Students");
		model.addAttribute("students", studentService.findAllStudents());
		return "student/studentlist";
	}
	
	@GetMapping("/form")
	public String create(Model model) {
		
		Student student = new Student();
		model.addAttribute("title", "Student's Form");
		model.addAttribute("student", student);
		
		return "student/studentform";
	}
	
	@GetMapping("/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Student student = null;
		if(id > 0) {
			student = studentService.findStudent(id);
			if (student == null) {
				flash.addFlashAttribute("error", "Student id doesn't exist in the DB!");
				return "redirect:/students/list";
			}
		} else {
			flash.addFlashAttribute("error", "Student id can't be zero!");
			return "redirect:/students/list";
		}
		model.addAttribute("title", "Edit student");
		model.addAttribute("student", student);
		
		return "student/studentform";
	}
	
	@PostMapping("/form")
	// Con @Valid habilitamos las validaciones de nuestra clases POJO (@NotNull, @NotEmpty, etc)
	// Con BindingResult (tiene que ir PEGADO al objeto Cliente) preguntamos si hay errores	
	public String save(@Valid Student student, BindingResult result, Model model, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Student's Form");
			return "student/studentform";
		}
		String messageFlash = (student.getId() != null)? "Student edited successfully!" : "Student created successfully!";
		
		studentService.saveStudent(student);
		
		flash.addFlashAttribute("success", messageFlash);
		
		return "redirect:/students/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			studentService.deleteStudent(id);
			flash.addFlashAttribute("success", "Student deleted successfully!");
		}
		
		return "redirect:/students/list";
	}

}
