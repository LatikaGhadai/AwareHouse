package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.WhUserType;
import com.nt.repository.WhUsertypeRepository;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {
	
	@Autowired
	private WhUsertypeRepository repo;
	
	@Override
	@Transactional
	public Integer saveWhUserType(WhUserType user) {
		return repo.save(user).getId();
	}

	@Override
	@Transactional
	public void updateWhUserType(WhUserType user) {
		repo.save(user);

	}

	@Override
	@Transactional
	public void deleteWhUserType(Integer id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<WhUserType> getOneWhUserType(Integer id) {
		Optional<WhUserType> opt=repo.findById(id);
		return opt;
	}

	@Override
	@Transactional(readOnly = true)
	public List<WhUserType> getAllWhUserType() {
		List<WhUserType> list=repo.findAll();
		return list;
	}
	 
    
	@Override
	@Transactional(readOnly = true)
	public boolean isWhUserTypeExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}

	@Override
	//@Transactional(readOnly = true)
	public Map<Integer, String> getWhUserTypeIdAndCode(String userType) {
		
		return repo.getWhUserTypeIdAndCode(userType)
				.stream().collect(Collectors.toMap(ob->Integer.valueOf(ob[0].toString()), ob->ob[1].toString()));
	}

}
