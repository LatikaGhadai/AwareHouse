package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.Part;
import com.nt.repository.PartRepository;

@Service
public class PartServiceImpl implements IPartService {
	
	@Autowired
	private PartRepository repo;

	@Override
	@Transactional
	public Integer savePart(Part part) {
		return repo.save(part).getId();
	}

	@Override
	@Transactional
	public void updatePart(Part part) {
		repo.save(part);
	}

	@Override
	@Transactional
	public void deletePart(Integer id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Part> getOnePart(Integer id) {
		return repo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Part> getAllPart() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isPartExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Map<Integer, String> getPartIdAndCode() {
		
		return repo.getPartIdAndCode()
				.stream()
				.collect(Collectors.toMap(ob->Integer.valueOf(ob[0].toString()), ob->ob[1].toString()));
	}

}
