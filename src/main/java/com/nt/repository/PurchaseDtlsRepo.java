package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.PurchaseDtls;

public interface PurchaseDtlsRepo extends JpaRepository<PurchaseDtls, Integer> {
	
	@Query("SELECT PDTL FROM PurchaseDtls PDTL INNER JOIN PDTL.po as po WHERE po.id=:purchaseId")
	public List<PurchaseDtls> getPurchaseDtlsWithPoId(Integer purchaseId);
	
	@Query("SELECT COUNT(PDTL.id) FROM PurchaseDtls PDTL INNER JOIN PDTL.po AS po WHERE po.id=:purchaseId")
	public Integer getPurchaseDtlCountWithPoId(Integer purchaseId);

}
