package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {
	
	@Query("select id,shipmentCode from ShipmentType st where st.enableShipment='YES'")
	public List<Object[]> getShipmentIdAndShipmentCode();

}
