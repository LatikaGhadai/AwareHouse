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

import com.nt.model.SaleOrder;
import com.nt.model.SaleOrderDtls;
import com.nt.service.IPartService;
import com.nt.service.ISaleOrderService;
import com.nt.service.IShipmentTypeService;
import com.nt.service.IWhUserTypeService;
import com.nt.view.SaleOrderExcelView;
import com.nt.view.SaleOrderPdfView;

@Controller
@RequestMapping("/saleOrder")
public class SaleOrderController {
	
	@Autowired
	private ISaleOrderService saleService;
	@Autowired
	private IShipmentTypeService shipmentService;
	@Autowired
	private IWhUserTypeService whUserService;
	@Autowired
	private IPartService partService;
	
	private void addDropDown(Model model) {
		model.addAttribute("shipmentTypes", shipmentService.getShipmentIdAndCode());
		model.addAttribute("whUsertypes", whUserService.getWhUserTypeIdAndCode("Customer"));
		model.addAttribute("parts", partService.getPartIdAndCode());
	}
	
	@GetMapping("/register")
	private String showRegister(Model model) {
		model.addAttribute("saleOrder", new SaleOrder());
		addDropDown(model);
		return "saleOrderRegister";
	}
	
	@PostMapping("/save")
	private String saveOrder(@ModelAttribute SaleOrder so,Model model) {
		Integer id=saleService.saveSaleOrder(so);
		String message="Sale Order '"+id+"' is saved";
		model.addAttribute("message", message);
		model.addAttribute("saleOrder", new SaleOrder());
		addDropDown(model);
		return "saleOrderRegister";	
	}
	
	@GetMapping("/all")
	private String showAll(Model model) {
		List<SaleOrder> list=saleService.getAllSaleOrders();
		model.addAttribute("list", list);
		return "saleOrderData";
	}
	
	@GetMapping("/delete/{id}")
	private String deleteSaleOrder(@PathVariable Integer id,Model model) {
		String message=null;
		if(saleService.isSaleOrderExist(id)) {
			saleService.deleteSaleOrder(id);
			message="Sale Order '"+id+"' is deleted";
		}
		else {
			message="Sale Order '"+id+"' is Not Exist";
		}
		List<SaleOrder> list=saleService.getAllSaleOrders();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "saleOrderData";
	}
	
	@GetMapping("/edit/{id}")
	private String editSaleOrder(@PathVariable Integer id,Model  model) {
		String page=null;
		String message=null;
		Optional<SaleOrder> opt=saleService.getOneSaleOrder(id);
		if(opt.isPresent()) {
			model.addAttribute("saleOrder", opt.get());
			addDropDown(model);
			page="saleOrderEdit";
			message="SaleOrder '"+id+"' Modify Successfully";
		}
		else {
			page="redirect:../all";
			message="SaleOrder '"+id+"' is not Exist";
		}
		model.addAttribute("message", message);
		return page;
	}
	
	@PostMapping("/update")
	private String updateSaleOrder(@ModelAttribute SaleOrder so,Model model) {
		String message=null;
		saleService.updateSaleOrder(so);
		message="Sale Order '"+so.getId()+"' updated";
		List<SaleOrder> list=saleService.getAllSaleOrders();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "saleOrderData";
	}
	
	@GetMapping("/view/{id}")
	private String saleOrderView(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<SaleOrder> opt=saleService.getOneSaleOrder(id);
		if(opt.isPresent()) {
			SaleOrder so=opt.get();
			model.addAttribute("ob", so);
			page = "saleOrderView";
		}
		else {
			page="redirect:../all";
		}
		
		return page;
	}
	
	@GetMapping("/excel")
	private ModelAndView exportToExcel(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new SaleOrderExcelView());
		//send data via class
		List<SaleOrder> list=saleService.getAllSaleOrders();
		model.addAttribute("saleOrder", list);
		return mv;
	}
	
	@GetMapping("/excel/{id}")
	private ModelAndView exportToExcel(@PathVariable Integer id,Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new SaleOrderExcelView());
		//send data via class
		Optional<SaleOrder> opt=saleService.getOneSaleOrder(id);
		if(opt.isPresent()) {
			model.addAttribute("saleOrder", Arrays.asList(opt.get()));
		}
		return mv;
	}
	
	@GetMapping("/pdf")
	private ModelAndView SaleOrderPdf(Model model) {
		
		ModelAndView mv=new ModelAndView();
		mv.setView(new SaleOrderPdfView());
		List<SaleOrder> list=saleService.getAllSaleOrders();
		model.addAttribute("list", list);
		return mv;
	}
	@GetMapping("/pdf/{id}")
	private ModelAndView SaleOrderPdf(@PathVariable Integer id,Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new SaleOrderPdfView());
		//get Data from service
		Optional<SaleOrder> opt=saleService.getOneSaleOrder(id);
		if(opt.isPresent()) {
		mv.addObject("list",Arrays.asList(opt.get()));
	   }
		return mv;
	}
	
	/*************************************************
	 *              Screen#2 Opertion                *
	 *************************************************/
	
	//1.show details
	@GetMapping("/itemDtls/{id}")
	private String showDtls(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<SaleOrder> so=saleService.getOneSaleOrder(id);
		if(so.isPresent()) {
			//it will show part DropDown
			model.addAttribute("so", so);
			//form backing object for adding part
			model.addAttribute("saleDtls", new SaleOrderDtls());
			addDropDown(model);
			page="saleOrderDtls";
		}
		else {
			page="redirect::/all";
		}
		return page;
	}

}
