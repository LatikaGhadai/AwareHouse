package com.nt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="grn_tab")
public class Grn {
	
	@Id
	@GeneratedValue
	@Column(name="grn_id_col")
	private Integer id;
	@Column(name="grn_code_col")
	private String grnCode;
	@Column(name="grn_type_col")
    private String grnType;
	@Column(name="grn_desc_col")
    private String description;
	
	@ManyToOne
	@JoinColumn(name="po_id_fk",unique=true)
	private PurchaseOrder po;
	
	@OneToMany(mappedBy = "grn")
	private List<GrnDtls> dtls;

}
