package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.model.UOM;

public interface IUomService {
	
	Integer saveUom(UOM uom);
	void updateUom(UOM uom);
	void deleteUom(Integer id);
	Optional<UOM> getOneUom(Integer id);
	List<UOM> getAllUom();
	boolean isUomExist(Integer id);

}
