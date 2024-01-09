package com.nt.controller;

import java.util.Arrays;
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

import com.nt.model.OrderMethod;
import com.nt.service.IOrderMethodService;
import com.nt.view.OrderMothodExcelView;

@Controller
@RequestMapping("/orderMethod")
public class OrderMethodController {
	
	@Autowired
	private IOrderMethodService service;
	
	@GetMapping("/register")
	private String showRegister(Model model) {
		
		model.addAttribute("orderMethod", new OrderMethod());
		return "OrderMethodRegister";
	}
	
	@PostMapping("/save")
	private String saveOrder(@ModelAttribute OrderMethod orderMethod,Model model) {
		
		  Integer id=service.saveOrderMethod(orderMethod); 
		  List<OrderMethod> list=service.getAllOrderMethods(); 
		  String message="Order Method '"+id+"' saved";
		  model.addAttribute("message",message);
		  model.addAttribute("list",list); //clear form
		  model.addAttribute("ordMethod", new OrderMethod());
		 
		 return "orderMethodData";
	}
	
	@GetMapping("/all")
	private String showAll(Model model) {
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("list", list);
		return "orderMethodData";
	}
	
	@GetMapping("/delete/{id}")
	private String delete(@PathVariable Integer id,Model model) {
		
		
		String message=null;
		if(service.isOrderMethodExist(id)) {
			service.deleteOrderMethod(id);
			message="Order Method '"+id+"' Deleted";
		}
		else {
			message="Order Method '"+id+"' NotExist";
		}
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		
	    return "orderMethodData";
	}
	
	@GetMapping("/edit/{id}")
	private String editOrder(@PathVariable Integer id,Model model) {
		
		String page=null;
		Optional<OrderMethod> opt=service.getOneOrderMethod(id);
		if(opt.isPresent()) {
			model.addAttribute("orderMethod", opt.get());
			page="orderMethodEdit";
		}
		else {
			page="redirect:showAll";
		}
		
		return page;
	}
	
	@PostMapping("/update")
	private String updateOrder(@ModelAttribute OrderMethod orderMethod,Model model) {
		service.updateOrderMethod(orderMethod);
		String message="OrderMethod '"+orderMethod.getId()+"' updated";
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "orderMethodData";
	}
	
	@GetMapping("/view/{id}")
	private String viewOrder(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<OrderMethod> opt=service.getOneOrderMethod(id);
		
		if(opt.isPresent()) {
			OrderMethod om=opt.get();
			model.addAttribute("ob", om);
			page="orderMethodView";
		}
		else {
			page="redirect:showAll";
		}
		return page;
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView mv=new ModelAndView();
		mv.setView(new OrderMothodExcelView());
		
		//send data to view class
		List<OrderMethod> list=service.getAllOrderMethods();
		mv.addObject("obj", list);
		return mv;
	}
	
	@GetMapping("/excel/{id}")
	public ModelAndView exportToOneData(@PathVariable Integer id) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new OrderMothodExcelView());
		
		//send data to view class
		Optional<OrderMethod> opt=service.getOneOrderMethod(id);
		if(opt.isPresent()) {
		mv.addObject("obj", Arrays.asList(opt.get()));
		}
		return mv;
	}
	
	
	

}
