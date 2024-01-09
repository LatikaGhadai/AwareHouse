package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.model.Grn;
import com.nt.model.GrnDtls;

public interface IGrnService {
	
	Integer saveGrn(Grn grn);
	void updateGrn(Grn grn);
	void deleteGrn(Integer id);
	Optional<Grn> getOneGrn(Integer id);
	List<Grn> getAllGrn();
	boolean isGrnExist(Integer id);
	
	Integer saveGrnDtls(GrnDtls dtls);
	List<GrnDtls> getAllDtlsByGrnId(Integer grnId);
	void updateStatusByGrnDtlsId(String status,Integer id);
	
	

}
