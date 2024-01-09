package com.nt.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.UOM;
import com.nt.repository.IUomRepository;

@Service
public class UomServiceImpl implements IUomService {
	
	@Autowired
	private IUomRepository repo;

	@Override
	@Transactional
	public Integer saveUom(UOM uom) {
		
		return repo.save(uom).getId();
	}

	@Override
	@Transactional
	public void updateUom(UOM uom) {
		repo.save(uom);

	}

	@Override
	@Transactional
	public void deleteUom(Integer id) {
		repo.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UOM> getOneUom(Integer id) {

		return repo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UOM> getAllUom() {
		
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isUomExist(Integer id) {
		
		return repo.existsById(id);
	}

}
