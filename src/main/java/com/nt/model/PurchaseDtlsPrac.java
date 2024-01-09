package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="pur_dtls_prac_tab")
public class PurchaseDtlsPrac {
	
	@Id
	@GeneratedValue
	@Column(name="pur_dtls_id_col")
	private Integer id;
	@Column(name="pur_dtls_slno_col")
	private Integer slno;
	@Column(name="pur_dtls_qty_col")
	private Integer qty;
	
	@ManyToOne
	@JoinColumn(name="part_id_fk")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="po_id_fk")
	private PurchaseOrder po;

}
