package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nt.model.PurchaseDtls;
import com.nt.model.PurchaseOrder;

public interface IPurchaseOrderService {
	
	Integer savePurchaseOrder(PurchaseOrder po);
	void updatePurchaseOrder(PurchaseOrder po);
	void deletePurchaseOrder(Integer id);
	Optional<PurchaseOrder> getOnePurchaseOrder(Integer id);
	List<PurchaseOrder> getAllPurchaseOrder();
	boolean isPurchaseOrderExist(Integer id);
	
	//----Screen#2 Operation----//
	Integer addPartToPo(PurchaseDtls dtl);
	List<PurchaseDtls> getPurchaeDtlWithPoId(Integer purchaseId);
	void deletePurchaseDtls(Integer id);
	void updatePurchaseOrderStatus(String defaultStatus,Integer id);
	Integer getPurchaseDtlCountWithPoId(Integer purchaseId );
	
	Map<Integer,String> getPoIdAndCodeByStatus(String status);

}
