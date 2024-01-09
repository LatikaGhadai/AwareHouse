package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.PurchaseDtls;
import com.nt.model.PurchaseOrder;
import com.nt.repository.PurchaseDtlsRepo;
import com.nt.repository.PurchaseOrderRepository;


@Service
public class PurchasOrderServiceImpl  implements IPurchaseOrderService{
	
	@Autowired
	private PurchaseOrderRepository repo;
	@Autowired
	private PurchaseDtlsRepo dtlRepo;


	@Override
	@Transactional
	public Integer savePurchaseOrder(PurchaseOrder po) {
		return repo.save(po).getId();
	}

	@Override
	@Transactional 
	public void updatePurchaseOrder(PurchaseOrder po) {
		repo.save(po);
		
	}

	@Override
	@Transactional
	public void deletePurchaseOrder(Integer id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PurchaseOrder> getOnePurchaseOrder(Integer id) {
		Optional<PurchaseOrder> opt=repo.findById(id);
		return opt;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PurchaseOrder> getAllPurchaseOrder() {
		List<PurchaseOrder>list=repo.findAll();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isPurchaseOrderExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}
	
	//-----Screen#2 Operation-----//

	@Override
	public Integer addPartToPo(PurchaseDtls dtl) {
		return dtlRepo.save(dtl).getId();
	}

	@Override
	public List<PurchaseDtls> getPurchaeDtlWithPoId(Integer purchaseId) {
		return dtlRepo.getPurchaseDtlsWithPoId(purchaseId);
	}

	@Override
	public void deletePurchaseDtls(Integer id) {
		dtlRepo.deleteById(id);
		
	}
    
	@Override
	@Transactional
	public void updatePurchaseOrderStatus(String defaultStatus, Integer id) {
		repo.updatePurchaseOrderStatus(defaultStatus, id);
		
	}

	@Override
	public Integer getPurchaseDtlCountWithPoId(Integer purchaseId) {
		return dtlRepo.getPurchaseDtlCountWithPoId(purchaseId);
	}

	@Override
	public Map<Integer, String> getPoIdAndCodeByStatus(String status) {
		return repo.getPoIdAndCodeByStatus(status)
		.stream()
		.collect(Collectors.toMap(ob->Integer.valueOf(ob[0].toString()),ob->ob[1].toString()));
		
	}
	
	
	

}
