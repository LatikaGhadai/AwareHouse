package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sale_ordr_tab")
public class SaleOrder {
	
	@Id
	@GeneratedValue(generator = "sale_gen")
	@SequenceGenerator(name = "sale_gen",sequenceName = "sale_gen_seq")
	@Column(name = "sale_ordr_id_col")
	private Integer id;
	@Column(name = "sale_ordr_code_col")
	private String orderCode;
	@Column(name = "sale_ordr_ref_col")
	private String referenceNo;
	@Column(name = "sale_ordr_stock_mode_col")
	private String stockMode;
	@Column(name = "sale_ordr_stock_sorc_col")
	private String stockSource;
	@Column(name = "sale_ordr_stats_col")
	private String status;
	@Column(name = "sale_ordr_desc_col")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "ship_type_id_fk")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name = "wh_user_type_id_fk")
	private WhUserType  customer;

}
