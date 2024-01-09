package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.WhUserType;

public interface WhUsertypeRepository extends JpaRepository<WhUserType, Integer> {
	
	@Query("select wh.id,wh.userCode from WhUserType wh where wh.userType=:userType")
	public List<Object[]> getWhUserTypeIdAndCode(String userType);

}
