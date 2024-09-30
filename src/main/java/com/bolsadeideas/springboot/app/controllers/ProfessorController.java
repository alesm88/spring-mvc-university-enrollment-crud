package com.bolsadeideas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Professor;
import com.bolsadeideas.springboot.app.models.service.IProfessorService;
import com.bolsadeideas.springboot.app.models.service.ISubjectService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/professors")
public class ProfessorController {
	
	@Autowired
	private IProfessorService professorService;
	
	@Autowired
	private ISubjectService subjectService;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("title", "List of Professors");
		model.addAttribute("professors", professorService.findAllProfessors());
		return "professor/professorlist";
	}
	
	@GetMapping("/form")
	public String create(Model model) {
		
		Professor professor = new Professor();
		model.addAttribute("title", "Professor's Form");
		model.addAttribute("professor", professor);
		
		return "professor/professorform";
	}
	
	@GetMapping("/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Professor professor = null;
		if(id > 0) {
			professor = professorService.findProfessor(id);
			if (professor == null) {
				flash.addFlashAttribute("error", "Professor id doesn't exist in the DB!");
				return "redirect:/professors/list";
			}
		} else {
			flash.addFlashAttribute("error", "Professor id can't be zero!");
			return "redirect:/professors/list";
		}
		model.addAttribute("title", "Edit professor");
		model.addAttribute("professor", professor);
		
		return "professor/professorform";
	}
	
	@PostMapping("/form")
	// Con @Valid habilitamos las validaciones de nuestra clases POJO (@NotNull, @NotEmpty, etc)
	// Con BindingResult (tiene que ir PEGADO al objeto Professor) preguntamos si hay errores	
	public String save(@Valid Professor professor, BindingResult result, Model model, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Professor's Form");
			return "professor/professorform";
		}
		String messageFlash = (professor.getId() != null)? "Professor edited successfully!" : "Professor created successfully!";
		
		professorService.saveProfessor(professor);
		if (professor.getActive() == 0) {
			subjectService.unassignedProfessor(professor.getId());
		}
		
		flash.addFlashAttribute("success", messageFlash);
		
		return "redirect:/professors/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			professorService.deleteProfessor(id);
			flash.addFlashAttribute("success", "Professor deleted successfully!");
		}
		
		return "redirect:/professors/list";
	}

}
