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

import com.nt.model.WhUserType;
import com.nt.service.IWhUserTypeService;
import com.nt.view.WhUserExcelView;

@Controller
@RequestMapping("/whUserType")
public class WhUserTypeController {
	
	@Autowired
	private IWhUserTypeService service;
	
	@GetMapping("/register")
	private String showRegister(Model model) {
		model.addAttribute("whUserType", new WhUserType());
		return "whUserTypeRegister";
	}
	
	@PostMapping("/save")
	private String save(@ModelAttribute WhUserType  whUserType ,Model model) {
		Integer id=service.saveWhUserType( whUserType );
		List<WhUserType> list=service.getAllWhUserType();
		String message="Wh User Type '"+id+"' saved";
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		
		return "whUserTypeData";
	}
	@GetMapping("/all")
	private  String showAll(Model model) {
		List<WhUserType> list=service.getAllWhUserType();
		model.addAttribute("list", list);
		return "whUserTypeData";
	}
	
	@GetMapping("/delete/{id}")
	private String remove(@PathVariable Integer id, Model model) {
		String message=null;
		if(service.isWhUserTypeExist(id)) {
			service.deleteWhUserType(id);
			message="WH User '"+id+"' deleted";
		}
		else {
			message="WH User '"+id+"' is Not Exist";
		}
		List<WhUserType> list=service.getAllWhUserType();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "whUserTypeData";
	
	}
	
	@PostMapping("/update")
	private String updatWhUser(@ModelAttribute WhUserType whUserType, Model model) {
		service.updateWhUserType(whUserType);
		String message="WH User '"+whUserType.getId()+"' updated";
		List<WhUserType> list=service.getAllWhUserType();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return"whUserTypeData";
	}
	
	@GetMapping("/edit/{id}")
	private String editWhUser(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<WhUserType> opt=service.getOneWhUserType(id);
		if(opt.isPresent()) {
			model.addAttribute("whUserType", opt.get());
			page="whUserTypeEdit";
		}
		else {
			page="redirect:all";
		}
		return page;
	}
	
	@GetMapping("/view/{id}")
	private String viewWhUser(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<WhUserType> opt=service.getOneWhUserType(id);
		if(opt.isPresent()) {
			WhUserType whUser=opt.get();
			model.addAttribute("ob", whUser);
			page="whUserTypeView";
		}
		else {
			page="redirect:all";
		}
		return page;			
	}
	
	@GetMapping("/excel")
	private ModelAndView ExportToExcel(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new WhUserExcelView());
		
		//send data view class
		List<WhUserType> list=service.getAllWhUserType();
		model.addAttribute("whUser", list);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
