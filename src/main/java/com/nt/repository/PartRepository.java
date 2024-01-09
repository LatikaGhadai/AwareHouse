package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {
	
	@Query("SELECT P.id,P.partCode FROM Part P")
	public List<Object[]> getPartIdAndCode();

}
