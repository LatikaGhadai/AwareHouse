package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer>{

}
