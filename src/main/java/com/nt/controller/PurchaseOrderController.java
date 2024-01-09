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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nt.model.PurchaseDtls;
import com.nt.model.PurchaseDtlsPrac;
import com.nt.model.PurchaseOrder;
import com.nt.service.IPartService;
import com.nt.service.IPurchaseOrderService;
import com.nt.service.IShipmentTypeService;
import com.nt.service.IWhUserTypeService;
import com.nt.view.PurchaseOrderExcelview;
import com.nt.view.PurchaseOrderPdfView;
import com.nt.view.VendorInvoicePdf;

@Controller
@RequestMapping("/purchaseOrd")
public class PurchaseOrderController {
	
	@Autowired
	private IPurchaseOrderService service;
	@Autowired
	private IPartService partService;
	
	@Autowired
	private IShipmentTypeService shipmentService;
	@Autowired
	private IWhUserTypeService whUserService;
	
	private void addDropDown(Model model) {
		model.addAttribute("shipmentTypes", shipmentService.getShipmentIdAndCode());
		model.addAttribute("whUsertypes", whUserService.getWhUserTypeIdAndCode("Vendor"));
		model.addAttribute("parts", partService.getPartIdAndCode());
	}
	
	
	@GetMapping("/register")
	private String showRegister(Model model) {
		model.addAttribute("purchaseOrder", new PurchaseOrder());
		addDropDown(model);
		return "purchaseOrderRegister";
	}
	
	@PostMapping("/save")
	private String savePurchase(@ModelAttribute PurchaseOrder po,Model model) {
		
		Integer id=service.savePurchaseOrder(po);
		String message="Purchase Order '"+id+"' save Successfully";
		model.addAttribute("message", message);
		model.addAttribute("purchaseOrder", new PurchaseOrder());
		addDropDown(model);
		return "purchaseOrderRegister";
	}
	
	@GetMapping("/all")
	private String getAllPurchaseOrder(Model model) {
		List<PurchaseOrder> list=service.getAllPurchaseOrder();
		model.addAttribute("list", list);
		return "purchaseOrderData";
	}
	@GetMapping("/delete/{id}")
	private String deletePurchaseOrder(@PathVariable Integer id,Model model) {
		
		String message=null;
		if(service.isPurchaseOrderExist(id)) {
			service.deletePurchaseOrder(id);
			message="Purchase order '"+id+"' deleted";
		}
		else {
			message="Purchase order '"+id+"' id Not Exist";
		}
		List<PurchaseOrder> list=service.getAllPurchaseOrder();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "purchaseOrderData";
	}
	@GetMapping("/edit/{id}")
	private String editPurchaseOrder(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<PurchaseOrder> opt=service.getOnePurchaseOrder(id);
		if(opt.isPresent()) {
			model.addAttribute("purchaseOrder", opt.get());
			addDropDown(model);
			page="purchaseOrderEdit";
		}
		else {
			page="redirect:../all";
			
		}
		return page;
	}
	@PostMapping("/update")
	private String updatePurchaseOrder(@ModelAttribute PurchaseOrder po,Model model) {
		String message=null;
		service.updatePurchaseOrder(po);
		message="Purchase Order '"+po.getId()+"' Updated";
		List<PurchaseOrder> list=service.getAllPurchaseOrder();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "purchaseOrderData";
	}
	@GetMapping("/view/{id}")
	private String viewPurchaseOrder(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<PurchaseOrder> opt=service.getOnePurchaseOrder(id);
		if(opt.isPresent()) {
			PurchaseOrder po=opt.get();
			model.addAttribute("ob", po);
			page="purchaseOrderView";
		}
		else {
			page="redirect:../all";
		}
		
		return page;
	}
	
	@GetMapping("/excel")
	private ModelAndView exportToExcel(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new PurchaseOrderExcelview());
		//send data view class
		List<PurchaseOrder> list=service.getAllPurchaseOrder();
		model.addAttribute("purchaseOrder", list);
		return mv;
	}
	
	@GetMapping("/excel/{id}")
	private ModelAndView export(@PathVariable Integer id,Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new PurchaseOrderExcelview());
		//send data view class
		Optional<PurchaseOrder> opt=service.getOnePurchaseOrder(id);
		if(opt.isPresent()) {
			model.addAttribute("purchaseOrder", Arrays.asList(opt.get()));
		}
		return mv;
	}
	
	@GetMapping("/pdf")
	private ModelAndView exportToPdf(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new PurchaseOrderPdfView());
		return mv;
		
	}
	@GetMapping("/pdf/{id}")
	private ModelAndView getonePdf(@PathVariable Integer id,Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new PurchaseOrderPdfView());
		//get Data from service
		Optional<PurchaseOrder> opt=service.getOnePurchaseOrder(id);
		if(opt.isPresent()) {
			mv.addObject("list",Arrays.asList(opt.get()));
		}
		return mv;
	}
	
	/***********************************************************************************
     *                         Screen#2 Operation                                      *
     ***********************************************************************************/
    
	//1. Show details
	@GetMapping("/partDtls/{id}")
	public String showDtls(@PathVariable Integer id,Model model) {
		         
		String page=null; 
		Optional<PurchaseOrder> po=service.getOnePurchaseOrder(id);
		if(po.isPresent()) {
			//It will show part DropDown
			model.addAttribute("po", po.get());
			//form backing object for adding part
			model.addAttribute("purchaseDtls", new PurchaseDtls());
			addDropDown(model);
			
			//form backing object for adding part Linked with po
			  PurchaseDtls purchaseDtls=new PurchaseDtls(); purchaseDtls.setPo(po.get());
			  model.addAttribute("purchaseDtls", purchaseDtls);
			  model.addAttribute("dtlList",service.getPurchaeDtlWithPoId(po.get().getId()));
			  page="purchaseDtls";
			 
		}
		else {
			page="redirect:../all";
		}
		return page;
	}
	
	//-----OnClick add button-----//
	@PostMapping("/addPart")
	public String addPartToPo(@ModelAttribute PurchaseDtls purchaseDtls) {
		
		service.addPartToPo(purchaseDtls);
		Integer poId=purchaseDtls.getPo().getId();
		service.updatePurchaseOrderStatus("PICKING", poId);
		return "redirect:partDtls/"+purchaseDtls.getPo().getId();
	}
	
	@GetMapping("/removePart")
	private String removePart(@RequestParam Integer dtlsId,@RequestParam Integer poId) {
		service.deletePurchaseDtls(dtlsId);
		//service.deletePurchaseOrder(dtlsId);
		Integer dtlsCount=service.getPurchaseDtlCountWithPoId(poId);
		
		if(dtlsCount==0) {
			service.updatePurchaseOrderStatus("OPEN", poId);
		}
		return "redirect:partDtls/"+poId;
	}
	
	  @GetMapping("/confirm/{id}")
	  private String confirmOrder(@PathVariable Integer id) {
		  
		  Integer dtlsCount=service.getPurchaseDtlCountWithPoId(id);
		  if(dtlsCount>0) {
			  service.updatePurchaseOrderStatus("ORDERED", id);
		  }		  
		  return "redirect:../partDtls/"+id;
	  
	  }
	  
	  @GetMapping("/invoice/{id}")
	  private String invoicOrder(@PathVariable Integer id) {
		  
		  service.updatePurchaseOrderStatus("INVOICED", id);
		  return "redirect:../all";
	  }
	  
	  @GetMapping("/printInVoice/{id}")
	  private ModelAndView PrintInvoice(@PathVariable Integer id) {
		  ModelAndView mv=new ModelAndView();
		  mv.setView(new VendorInvoicePdf());
		  mv.addObject("po",service.getOnePurchaseOrder(id).get());
		  return mv;
	  }
	 
	
	/***********************************************************************************
     *                         Screen#2 Operation Practise                             *
     ***********************************************************************************/
	@GetMapping("partDtls2/{id}")
	private String showPartDtls2(@PathVariable Integer id,Model model) {
		
		String page=null;
		Optional<PurchaseOrder> po=service.getOnePurchaseOrder(id);
		if(po.isPresent()) {
			//it will show part Drop down
			model.addAttribute("po", po.get());
			//form backing object for adding part
			model.addAttribute("purchaseDtls", new PurchaseDtlsPrac());
			addDropDown(model);
			
			PurchaseDtlsPrac purchaseDtls=new PurchaseDtlsPrac();
			purchaseDtls.setPo(po.get());
			model.addAttribute("purchaseDtls", purchaseDtls);
			
			
			page="purchaseDtlsPrac";
			
		}
		else {
			page="redirect:../all";
		}
		return"";
	}
	



}

	
	
	
	
	
	
	
	



