package com.nt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.model.Part;
import com.nt.service.IPartService;
import com.nt.view.PartExcelView;

@Controller
@RequestMapping("/part")
public class PartController {
	
	@Autowired
	private IPartService service;
	
	@GetMapping("/register")
	private String showRegister(Model model) {
		
		model.addAttribute("part", new Part());
		return "partRegiser";
	}
	
	@PostMapping("/save")
	private String savePart(@ModelAttribute Part part,Model model) {
		String message=null;
		Integer id=service.savePart(part);
		message="part '"+id+"' saved";
		List<Part> list=service.getAllPart();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "partData";
		//return "partData";
	}
	
	@GetMapping("/all")
	private String showAll(Model model) {
		List<Part> list=service.getAllPart();
		model.addAttribute("list", list);
		return "partData";
	}
	
	@GetMapping("/delete/{id}")
	private String deletePart(@PathVariable Integer id,Model model) {
		
		String message=null;
		if(service.isPartExist(id)) {
			service.deletePart(id);
			message="Part '"+id+"' deleted";
		}
		else {
			message="Part '"+id+"' Not Exist";
		}
		List<Part> list=service.getAllPart();
		model.addAttribute("list", list);
	    model.addAttribute("message", message);
		return "partData";
	}
	
	@GetMapping("/edit/{id}")
	private String editPart(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<Part> opt=service.getOnePart(id);
		if(opt.isPresent()) {
			model.addAttribute("part", opt.get());
			page="partEdit";
		}
		else {
			//page="redirect:all";
			page="redirect:../all";
		}
		
		return page;
	}
	
	@PostMapping("/update")
	private String updatePart(@ModelAttribute Part part,Model model) {
		
		service.updatePart(part);
		String message="Part '"+part.getId()+"' updated ";
		List<Part> list=service.getAllPart();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "partData";
	}
	
	@GetMapping("/view/{id}")
	private String partView(@PathVariable Integer id,Model model) {
		
		String page=null;
		Optional<Part> opt=service.getOnePart(id);
		if(opt.isPresent()) {
			Part part=opt.get();
			model.addAttribute("part", part);
			page="partView";
		}
		else {
			page="redirect:all";
		}
		return page;
	}
	
	@GetMapping("/excel")
	private ModelAndView excelExport(){
		ModelAndView mv=new ModelAndView();
		mv.setView(new PartExcelView());
		
		//send data to view class
		List<Part> list=service.getAllPart();
		mv.addObject("part", list);
		return mv;
		
	}
	

}
