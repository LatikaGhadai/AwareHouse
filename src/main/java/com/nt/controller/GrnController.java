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

import com.nt.model.Grn;
import com.nt.model.GrnDtls;
import com.nt.model.PurchaseDtls;
import com.nt.service.IGrnService;
import com.nt.service.IPurchaseOrderService;
import com.nt.view.GrnExcelView;
import com.nt.view.GrnPdfView;

@Controller
@RequestMapping("/grn")
public class GrnController {
	
	@Autowired
	private IGrnService service;
	
	@Autowired
	private IPurchaseOrderService poService;
	
	private void addDropDownUi(Model model) {
		model.addAttribute("pos", poService.getPoIdAndCodeByStatus("INVOICED"));
	}
	@GetMapping("/register")
	private String showRegister(Model model) {
		model.addAttribute("grn", new Grn());
		addDropDownUi(model);
		return "grnRegister";
	}
	
	@PostMapping("/save")
	private String saveGrn(@ModelAttribute Grn grn,Model model) {
		
		Integer id=service.saveGrn(grn);
		List<Grn> list=service.getAllGrn();
		String message="Grn Id- '"+id+"' Saved Successfully";
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		model.addAttribute("grn", new Grn());
		if(id!=null) {
		    Integer poId=grn.getPo().getId();
			//pois used in GRN so it is recived
			poService.updatePurchaseOrderStatus("RECEIVED", poId);
			convertPurchaseDtlsToGrnDtls(id, poId);
		}
		addDropDownUi(model);
		return "grnRegister";
		
	}
	
	@GetMapping("/all")
	private String showAll(Model model) {
		List<Grn> list=service.getAllGrn();
		model.addAttribute("list", list);
		return "grnData";
		
	}
	
	@GetMapping("/delete/{id}")
	private String deleteGrn(@PathVariable Integer id,Model model) {
		String message=null;
		if(service.isGrnExist(id)) {
			service.deleteGrn(id);
			message="Grn Id- '"+id+"' delete Successfully";
		}
		else {
			message="Grn Id- '"+id+"' is not Exist";
		}
		
		List<Grn> list=service.getAllGrn(); 
		model.addAttribute("list", list);
		 		 
		model.addAttribute("message", message);
		return "grnData";
	}
	
	@GetMapping("/edit/{id}")
	private String editGrn(@PathVariable Integer id,Model model) {
		
		String page=null;
		Optional<Grn> opt=service.getOneGrn(id);
		if(opt.isPresent()) {
			model.addAttribute("grn", opt.get());
			page="grnEdit";
		}
		else {
			page="redirect:../all";
		}
		return page;
	}
	
	@PostMapping("/update")
	private String updategrn(@ModelAttribute Grn grn,Model model) {
		
		service.updateGrn(grn);
		String message="Grn Id- '"+grn.getId()+"' updated";
		List<Grn> list=service.getAllGrn();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "grnData";
		
	}
	
	@GetMapping("/view/{id}")
	private String viewGrn(@PathVariable Integer id,Model model) {
		String message=null;
		Optional<Grn> opt=service.getOneGrn(id);
		  
		if(opt.isPresent()) {
			Grn grn=opt.get();
			model.addAttribute("ob", grn);
			message="GrnView";
		}
		
		else {
			message="redirect:../all";
		}
		return message;
	  }
	
	@GetMapping("/excel")
	public ModelAndView exceExport(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new GrnExcelView());
		//set data and view class
		List<Grn> list=service.getAllGrn();
		//mv.addObject("list",list);
		model.addAttribute("grn", list);
		return mv;
	}
	
	@GetMapping("/excel/{id}")
	public ModelAndView excel(@PathVariable Integer id,Model model) {
		ModelAndView mv= new ModelAndView();
		mv.setView(new GrnExcelView());
		Optional<Grn> opt=service.getOneGrn(id);
		if(opt.isPresent()) {
			model.addAttribute("grn", Arrays.asList(opt.get()));
		}
		return mv;
	}
	
	@GetMapping("/pdf")
	private ModelAndView exportToPdf(Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new GrnPdfView());
		List<Grn> list=service.getAllGrn();
		model.addAttribute("list", list);
		return mv;
		
	}
	@GetMapping("/pdf/{id}")
	private ModelAndView getonePdf(@PathVariable Integer id,Model model) {
		ModelAndView mv=new ModelAndView();
		mv.setView(new GrnPdfView());
		//get Data from service
		Optional<Grn> opt=service.getOneGrn(id);
		if(opt.isPresent()) {
			mv.addObject("list",Arrays.asList(opt.get()));
		}
		return mv;
	}
	
	//code for Screen#2
	
	private void convertPurchaseDtlsToGrnDtls(Integer grnId, Integer poId) {
		//1.Get Purchase Details
		List<PurchaseDtls> poDtlsList=poService.getPurchaeDtlWithPoId(poId);
		//2.convert one poDtlsobject to one GrnDtl Object
		for(PurchaseDtls poDtls:poDtlsList) {
			GrnDtls grnDtls=new GrnDtls();
			grnDtls.setPartCode(poDtls.getPart().getPartCode());
			grnDtls.setBaseCost(poDtls.getPart().getBaseCost());
			grnDtls.setQty(poDtls.getQty());
			grnDtls.setLineValue(grnDtls.getQty()*grnDtls.getBaseCost());
			
			//lin with Grn Object
			Grn grn=new Grn();
			grn.setId(grnId);
			grnDtls.setGrn(grn);
			//Save in Data base
			service.saveGrnDtls(grnDtls);
		}
	}
	//Display All Grn Dtls hera
		@GetMapping("/ViewDtls/{id}")
		public String showDtls(@PathVariable Integer id,Model model) {
			List<GrnDtls> dtls=service.getAllDtlsByGrnId(id);
			Optional<Grn> grn=service.getOneGrn(id);
			model.addAttribute("dtls", dtls);
			model.addAttribute("grn", grn.get());
			return "grnViewDetails";
		}
		
		@GetMapping("/status")
		public String updateDtlStatus(@RequestParam Integer grnId,@RequestParam Integer dtlsId,@RequestParam String status) {
			
			service.updateStatusByGrnDtlsId(status, dtlsId);
			return "redirect:ViewDtls/"+grnId;
		}
		
		
		
		
}
