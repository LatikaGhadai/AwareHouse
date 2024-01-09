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
@Table(name="part_tab")
public class Part {
	
	@Id
	@GeneratedValue(generator = "part_gen")
	@SequenceGenerator(name = "part_gen" , sequenceName = "part_gen_seq")
	@Column(name = "part_id_col")
	private Integer id;
	@Column(name = "part_code_col")
	private String partCode;
	@Column(name = "part_wdth_col")
	private Double partWidth;
	@Column(name = "part_lnth_col")
	private Double partLength;
	@Column(name = "part_hght_col")
	private Double partHeight;
	@Column(name = "part_cost_col")
	private Double baseCost;
	@Column(name = "part_curr_col")
	private String baseCurrency;
	@Column(name = "part_desc_col")
	private String description;

}
