package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nt.model.WhUserType;

public interface IWhUserTypeService {
	
	Integer saveWhUserType(WhUserType user);
	void updateWhUserType(WhUserType user);
	void deleteWhUserType(Integer id);
	Optional<WhUserType> getOneWhUserType(Integer id);
	List<WhUserType> getAllWhUserType();
	boolean isWhUserTypeExist(Integer id);
	
	Map<Integer,String> getWhUserTypeIdAndCode(String userType); 

}
