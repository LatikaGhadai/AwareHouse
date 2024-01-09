package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.ShipmentType;
import com.nt.repository.ShipmentTypeRepository;


@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {
	

	@Autowired
	private ShipmentTypeRepository repo;

	@Override
	@Transactional
	public Integer saveShipmentType(ShipmentType st) {
	
		return repo.save(st).getId();
	}

	@Override
	@Transactional
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);

	}

	@Override
	@Transactional
	public void deleteShipmentType(Integer id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt=repo.findById(id);
		
			return opt;
			
	}

	@Override
	@Transactional(readOnly=true)
	public List<ShipmentType> getAllShipmentTypes() {
		List<ShipmentType> list=repo.findAll();
		return list;
	}

	@Override
	@Transactional
	public boolean isShipmentTypeExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}

	@Override
	public Map<Integer, String> getShipmentIdAndCode() {
		
		return repo.getShipmentIdAndShipmentCode()
				    .stream()
				    .collect(Collectors.toMap(ob->Integer.valueOf(ob[0].toString()), ob->ob[1].toString()));
	}

}
