package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="uom_tab")
public class UOM {
	
	@Id
	@GeneratedValue(generator ="uom_gen")
	@SequenceGenerator(name="uom_gen",sequenceName="uom_gen_seq")
	@Column(name="uom_id_col")
	private Integer id;
	
	@Column(name="uom_type_col")
	private String uomType;
	
	@Column(name="uom_modl_col")
	private String uomModel;
	
	@Column(name="uom_desc_col")
	private String description;

}
