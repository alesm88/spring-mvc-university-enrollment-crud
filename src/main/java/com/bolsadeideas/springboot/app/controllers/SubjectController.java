package com.bolsadeideas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Student;
import com.bolsadeideas.springboot.app.models.entity.Subject;
import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.models.service.IProfessorService;
import com.bolsadeideas.springboot.app.models.service.IStudentService;
import com.bolsadeideas.springboot.app.models.service.ISubjectService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	private ISubjectService subjectService;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IProfessorService professorService;
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("username");
		Student student = null;
		if (user.getRole().equals("Student")) {
			student = studentService.findStudentByCardNumber(Integer.valueOf(user.getUsername()));
			model.addAttribute("subjects", subjectService.findSubjectsByStudentNotEnrolAndQuota(student.getId()));
		} else {
			
			model.addAttribute("subjects", subjectService.findAllSubjects());
		}
		
		model.addAttribute("title", "List of Subjects");
		
		return "subject/subjectlist";
	}
	
	@GetMapping("/detail/{id}")
	public String seeDetail(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Subject subject = null;
		if(id > 0) {
			subject = subjectService.findSubject(id);
			if (subject == null) {
				flash.addFlashAttribute("error", "Subject id doesn't exist in the DB!");
				return "redirect:/subjects/list";
			}
		} else {
			flash.addFlashAttribute("error", "Subject id can't be zero!");
			return "redirect:/subjects/list";
		}
		model.addAttribute("title", "Subject's detail");
		model.addAttribute("subject", subject);
		
		return "subject/subjectdetail";
	}
	
	@GetMapping("/enrol/{id}")
	public String enrol(@PathVariable(value="id") Long id, HttpSession session, Model model, RedirectAttributes flash) {
		
		User user = null;
		Student student = null;
		Subject subject = null;
		if(id > 0) {
			
			user = (User) session.getAttribute("username");
			subject = subjectService.findSubject(id);
			student = studentService.findStudentByCardNumber(Integer.valueOf(user.getUsername()));
			
			// To avoid that enrolls to the same timetable
			for (Subject s : subjectService.findSubjectsByStudent(student.getId())) {
				if (s.getTimetable().equals(subject.getTimetable()) && !s.getName().equals(subject.getName())) {
					flash.addFlashAttribute("error", "You cannot enrol to this subject because you are enrolled in another subject in the same time");
					return "redirect:/subjects/list";
				}
			}
			
			// Now that there is the method findSubjectsByStudentNotEnrolAndQuota(Integer student.id), this exception is not gonna happen
			try {
				subject.setMaxQuota(subject.getMaxQuota()-1);
				student.addSubject(subject);
				subjectService.saveSubject(subject);
			} catch (Exception e) {
				e.printStackTrace();
				flash.addFlashAttribute("error", "You cannot enrol this subject, you are already enrolled");
				return "redirect:/subjects/list";
			}
		}
		flash.addFlashAttribute("success", "You are enrolled");
		return "redirect:/subjects/list";
	}
	
	@GetMapping("/form")
	public String create(Model model) {
		
		Subject subject = new Subject();
		model.addAttribute("title", "Subject's Form");
		model.addAttribute("subject", subject);
		model.addAttribute("professors", professorService.findAllProfessorsActive());
		
		return "subject/subjectform";
	}
	
	@GetMapping("/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Subject subject = null;
		if(id > 0) {
			subject = subjectService.findSubject(id);
			if (subject == null) {
				flash.addFlashAttribute("error", "Subject id doesn't exist in the DB!");
				return "redirect:/subjects/list";
			}
		} else {
			flash.addFlashAttribute("error", "Subject id can't be zero!");
			return "redirect:/subjects/list";
		}
		model.addAttribute("title", "Edit subject");
		model.addAttribute("subject", subject);
		model.addAttribute("professors", professorService.findAllProfessorsActive());
		
		return "subject/subjectform";
	}
	
	@PostMapping("/form")
	// Con @Valid habilitamos las validaciones de nuestra clases POJO (@NotNull, @NotEmpty, etc)
	// Con BindingResult (tiene que ir PEGADO al objeto Subject) preguntamos si hay errores	
	public String save(@Valid Subject subject, BindingResult result, Model model, @RequestParam(value="professorId", required=false) Long idProfessor, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Subject's Form");
			model.addAttribute("professors", professorService.findAllProfessorsActive());
			return "subject/subjectform";
		}
		String messageFlash = (subject.getId() != null)? "Subject edited successfully!" : "Subject created successfully!";
		if (idProfessor != null) {
			subject.setProfessor(professorService.findProfessor(idProfessor));
		}
		
		subjectService.saveSubject(subject);
		
		flash.addFlashAttribute("success", messageFlash);
		
		return "redirect:/subjects/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			subjectService.deleteSubject(id);
			flash.addFlashAttribute("success", "Subject deleted successfully!");
		}
		
		return "redirect:/subjects/list";
	}

}
