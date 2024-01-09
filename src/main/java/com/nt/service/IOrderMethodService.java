package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.model.OrderMethod;

public interface IOrderMethodService {
	
	Integer saveOrderMethod(OrderMethod om);
	void updateOrderMethod(OrderMethod om);
	void deleteOrderMethod(Integer id);
	Optional<OrderMethod> getOneOrderMethod(Integer id);
	List<OrderMethod> getAllOrderMethods();
	boolean isOrderMethodExist(Integer id);

}
