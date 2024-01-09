package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	@Modifying
	@Query("update PurchaseOrder set defaultStatus=:defaultStatus Where id=:id")
	public void updatePurchaseOrderStatus(String defaultStatus,Integer id);
	
	
	@Query("SELECT PO.id,PO.orderCode FROM PurchaseOrder PO WHERE PO.defaultStatus=:status")
	public List<Object[]> getPoIdAndCodeByStatus(String status);


}
