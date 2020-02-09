package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_ALLOWEDPAYMENTMETHOD database table.
 * 
 */
@Entity
@Table(name="PC_ALLOWEDPAYMENTMETHOD")
@NamedQuery(name="PcAllowedpaymentmethodDO.findAll", query="SELECT p FROM PcAllowedpaymentmethodDO p")
public class PcAllowedpaymentmethodDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal archivepartition;

	private BigDecimal beanversion;

	private String createtime;

	private BigDecimal createuserid;

	private BigDecimal paymentmethod;

	private BigDecimal paymentplan;

	private String publicid;

	private BigDecimal retired;

	private String updatetime;

	private BigDecimal updateuserid;

	public PcAllowedpaymentmethodDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getArchivepartition() {
		return this.archivepartition;
	}

	public void setArchivepartition(BigDecimal archivepartition) {
		this.archivepartition = archivepartition;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public BigDecimal getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(BigDecimal createuserid) {
		this.createuserid = createuserid;
	}

	public BigDecimal getPaymentmethod() {
		return this.paymentmethod;
	}

	public void setPaymentmethod(BigDecimal paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public BigDecimal getPaymentplan() {
		return this.paymentplan;
	}

	public void setPaymentplan(BigDecimal paymentplan) {
		this.paymentplan = paymentplan;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public BigDecimal getRetired() {
		return this.retired;
	}

	public void setRetired(BigDecimal retired) {
		this.retired = retired;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public BigDecimal getUpdateuserid() {
		return this.updateuserid;
	}

	public void setUpdateuserid(BigDecimal updateuserid) {
		this.updateuserid = updateuserid;
	}

}