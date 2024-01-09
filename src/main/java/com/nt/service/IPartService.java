package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nt.model.Part;

public interface IPartService {
	
	Integer savePart(Part part);
	void updatePart(Part part);
	void deletePart(Integer id);
	Optional<Part> getOnePart(Integer id);
	List<Part> getAllPart();
	boolean isPartExist(Integer id);
	
	Map<Integer,String> getPartIdAndCode();

}
