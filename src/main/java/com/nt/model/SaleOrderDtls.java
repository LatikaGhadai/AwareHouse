package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="sale_ordr_dtls_tab")
public class SaleOrderDtls {
	@Id
	@GeneratedValue
	@Column(name="sale_ordr_dtls_id_col")
	private Integer id;
	@Transient
	private Integer slno;
	@Column(name="sale_ordr__dtls_qty_col")
	private Double qty;
	
	@ManyToOne
	@JoinColumn(name="part_id_col")
	private Part part;
	@ManyToOne
    @JoinColumn(name="so_id_fk")
	private SaleOrder so;

}
