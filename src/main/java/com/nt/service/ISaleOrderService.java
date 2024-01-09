package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.model.SaleOrder;

public interface ISaleOrderService {
	
	Integer saveSaleOrder(SaleOrder so);
	void updateSaleOrder(SaleOrder so);
	void deleteSaleOrder(Integer id);
	Optional<SaleOrder> getOneSaleOrder(Integer id);
	List<SaleOrder> getAllSaleOrders();
	boolean isSaleOrderExist(Integer id);

}
