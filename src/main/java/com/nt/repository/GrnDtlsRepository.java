package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.GrnDtls;

public interface GrnDtlsRepository extends JpaRepository<GrnDtls, Integer> {
	
	@Query("SELECT dtls from GrnDtls dtls INNER JOIN dtls.grn AS grn WHERE grn.id=:grnId")
	public List<GrnDtls> getAllDtlsByGrnid(Integer grnId); 
	@Modifying
	@Query("update GrnDtls set status=:status where id=:id")
    void updateStatusBygrnDtlsId(String status,Integer id);	

}
