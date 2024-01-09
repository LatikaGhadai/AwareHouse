package com.nt.controller;

import java.util.List;
import java.util.Map;
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

import com.nt.model.OrderMethod;
import com.nt.model.ShipmentType;
import com.nt.service.IShipmentTypeService;
import com.nt.view.ShipmentTypeExcelView;

@Controller
@RequestMapping("/shipmentType")
public class ShipmentTypeController {
	
	@Autowired
	private IShipmentTypeService service;
	
	@GetMapping("/register")
	private String showRegister(Model model) {
		System.out.println("hii");
		model.addAttribute("ShipmentType", new ShipmentType());
		System.out.println("helo");
		return "shipmenTypeRegister";
	}
	
	@PostMapping("/save")
	private String save(@ModelAttribute ShipmentType shipmentType,Model model) {
		Integer id=service.saveShipmentType(shipmentType);
		List<ShipmentType> list=service.getAllShipmentTypes();
		String msg="ShipmentType '"+id+"' saved";
		model.addAttribute("list", list);
		model.addAttribute("message", msg);
		return "shipmentTypeData";
	}
	
	@GetMapping("/all")
	private String showAll(Model model) {
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		return "shipmentTypeData";
	}
	
	@GetMapping("/delete/{id}")
	private String remove(@PathVariable Integer id,Model model) {
		String msg=null;
		if(service.isShipmentTypeExist(id)) {
		   service.deleteShipmentType(id);
		   msg="Shipment type '"+id+"' Deleted";
		}
		else {
		   msg="Shipment type '"+id+"' Not Exist";
		}
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		model.addAttribute("message", msg);
		return "shipmentTypeData";
	}
	
	@GetMapping("/edit/{id}")
	private String showEdit(@PathVariable Integer id,Model model) {
		Optional<ShipmentType> opt=service.getOneShipmentType(id);
		
		String page=null;
		
		if(opt.isPresent()) {
			ShipmentType st=opt.get();
			model.addAttribute("ShipmentType", st);
			page="shipmentTypeEdit";
		}
		
		else {
			page="redirect:/shipmentType/all";
		}
		
		
		return page;
	}
	
	@PostMapping("/update")
	private String update(@ModelAttribute ShipmentType shipmentType,Model model) {
		
		service.updateShipmentType(shipmentType);
		String msg="ShipmentType '" +shipmentType.getId()+ "' updated";
		model.addAttribute("message", msg);
		
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		
		return "shipmentTypeData";
	}

	@GetMapping("/view/{id}")
	private String viewOrder(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<ShipmentType> opt=service.getOneShipmentType(id);
		
		if(opt.isPresent()) {
			ShipmentType st=opt.get();
			model.addAttribute("ob", st);
			page="shipmentTypeView";
		}
		else {
			//page="redirect:all";
			page="redirect:../all";
		}
		return page;
	}
	
	@GetMapping("/excel")
	private ModelAndView ExportToExcel(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new ShipmentTypeExcelView());
		
		//send data view class
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("st", list);
		return mv;
	}

}
