package com.tdm.model.DTO;

import java.math.BigDecimal;


/**
 * The persistent class for the PCTL_POLICYCONTACTROLE database table.
 * 
 */
public class PctlPolicycontactroleDTO  {
		private long id;

	private String description;

	private String lEnUs;

	private String name;

	private BigDecimal priority;

	private BigDecimal retired;

	private BigDecimal sEnUs;

	private String typecode;

	public PctlPolicycontactroleDTO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLEnUs() {
		return this.lEnUs;
	}

	public void setLEnUs(String lEnUs) {
		this.lEnUs = lEnUs;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public BigDecimal getRetired() {
		return this.retired;
	}

	public void setRetired(BigDecimal retired) {
		this.retired = retired;
	}

	public BigDecimal getSEnUs() {
		return this.sEnUs;
	}

	public void setSEnUs(BigDecimal sEnUs) {
		this.sEnUs = sEnUs;
	}

	public String getTypecode() {
		return this.typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

}