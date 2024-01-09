package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.SaleOrder;
import com.nt.repository.SaleOrderRepository;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService{
	
	@Autowired
	private SaleOrderRepository repo;

	@Override
	@Transactional
	public Integer saveSaleOrder(SaleOrder so) {
		return repo.save(so).getId();
	}

	@Override
	@Transactional
	public void updateSaleOrder(SaleOrder so) {
		repo.save(so);		
	}

	@Override
	@Transactional
	public void deleteSaleOrder(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SaleOrder> getOneSaleOrder(Integer id) {
		Optional<SaleOrder> opt=repo.findById(id);
		return opt;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SaleOrder> getAllSaleOrders() {
		List<SaleOrder> list=repo.findAll();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isSaleOrderExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}

}
