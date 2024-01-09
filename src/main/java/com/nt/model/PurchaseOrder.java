package com.nt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="prch_ordr_tab")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(generator ="prch_gen")
	@SequenceGenerator(name="prch_gen",sequenceName = "prch_gen_seq")
	@Column(name = "prch_ordr_id_col")
	private Integer id;
	@Column(name = "ordr_code_col")
	private String orderCode;
	@Column(name = "ref_no_col")
	private String referenceNo;
	@Column(name = "qlty_chck_col")
	private String qualityCheck;
	@Column(name = "dflt_stas_col")
	//@Value("${some.key:Open}")
	private String defaultStatus;
	@Column(name = "prch_desc_col")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "ship_type_id_fk")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name="wh_user_type_id_fk")
	private WhUserType vendor;
	
	@OneToMany(mappedBy="po", fetch=FetchType.EAGER)
	private List<PurchaseDtls> dtls;

}
