package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.OrderMethod;
import com.nt.repository.OrderMethodRepository;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {
	
	@Autowired
	private OrderMethodRepository repo;

	@Override
	@Transactional
	public Integer saveOrderMethod(OrderMethod om) {
		return repo.save(om).getId();
	}

	@Override
	@Transactional
	public void updateOrderMethod(OrderMethod om) {
		repo.save(om);
	}

	@Override
    @Transactional
	public void deleteOrderMethod(Integer id) {
		repo.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {
		return repo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderMethod> getAllOrderMethods() {
		
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isOrderMethodExist(Integer id) {
		return repo.existsById(id);
	}

}
