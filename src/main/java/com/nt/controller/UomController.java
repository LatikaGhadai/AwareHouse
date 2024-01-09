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

import com.nt.model.ShipmentType;
import com.nt.model.UOM;
import com.nt.service.IUomService;

@Controller
@RequestMapping("/uom")
public class UomController {
	@Autowired
	private IUomService service;
	
	@GetMapping("/register")
	private String UomRegister(Model model) {
		model.addAttribute("uom", new UOM());
		return "uom_register";
		
	}
	
	@PostMapping("/save")
	private String saveUOM(@ModelAttribute UOM uom,Model model) {
		
		Integer id=service.saveUom(uom);
		List<UOM> list=service.getAllUom();
		String message="UOM '"+id+"' saved";
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		model.addAttribute("uom", new UOM());
		return "uom_Data";
	}
	@GetMapping("/all")
	private String showAll(Model model) {
		List<UOM> list=service.getAllUom();
		model.addAttribute("list", list);
		return "uom_Data";
	}
	
	@GetMapping("/delete/{id}")
	private String deleteUOM(@PathVariable Integer id,Model model) {
		String message=null;
		if(service.isUomExist(id)) {
			service.deleteUom(id);
			message="UOM '"+id+"' Deleted";
		}
		else {
			message="UOM '"+id+"' is not Exist";
		}
		List<UOM> list=service.getAllUom();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "uom_data";
	}
	
	@GetMapping("/edit/{id}")
	private String editUOM(@PathVariable Integer id,Model model) {
		
		String page=null;
		Optional<UOM> opt=service.getOneUom(id);
		if(opt.isPresent()) {
			model.addAttribute("uom", opt.get());
			page="uom_Edit";
		}
		else {
			page="redirect:showAll";
		}
		return page;
	}
	
	  @PostMapping("/update") 
	  private String updateUOM(@ModelAttribute UOM uom,Model model) { 
		  service.updateUom(uom);
		  String message="UOM '"+uom.getId()+"' updated";
		  List<UOM> list=service.getAllUom();
		  model.addAttribute("list", list);
		  model.addAttribute("message", message);
	  
	      return "uom_data";
	  }
	  @GetMapping("/view/{id}")
		private String viewOrder(@PathVariable Integer id,Model model) {
			String page=null;
			Optional<UOM> opt=service.getOneUom(id);
			
			if(opt.isPresent()) {
				UOM uom=opt.get();
				model.addAttribute("ob", uom);
				page="uom_View";
			}
			else {
				page="redirect:showAll";
			}
			return page;
		}
}
