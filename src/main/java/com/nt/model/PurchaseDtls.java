package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.repository.Modifying;

import lombok.Data;

@Data
@Entity
@Table(name="purchase_dtl_tab")
public class PurchaseDtls {
	
	@Id
	@GeneratedValue
	@Column(name="pur_dtls_id_col")
	private Integer id;
	@Transient
	private Integer slno;
	
	@Column(name="pur_dtls_qty_col")
	private Double qty;
	
	@ManyToOne
	@JoinColumn(name = "part_id_fk")
	private Part part;//HAS-A Relation
	
    @ManyToOne
    @JoinColumn(name="po_id_fk") 
    private PurchaseOrder po;
	 

}
