package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_EFFECTIVEDATEDFIELDS database table.
 * 
 */
@Entity
@Table(name="PC_EFFECTIVEDATEDFIELDS")
@NamedQuery(name="PcEffectivedatedfieldDO.findAll", query="SELECT p FROM PcEffectivedatedfieldDO p")
public class PcEffectivedatedfieldDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal archivepartition;

	private BigDecimal basedonid;

	private BigDecimal beanversion;

	private BigDecimal billingcontact;

	private BigDecimal branchid;

	private BigDecimal changetype;

	private String createtime;

	private BigDecimal createuserid;

	private String effectivedate;

	private String expirationdate;

	private BigDecimal fixedid;

	private String offeringcode;

	private BigDecimal policyaddress;

	private BigDecimal primarylocation;

	private BigDecimal primarynamedinsured;

	private String publicid;

	private BigDecimal secondarynamedinsured;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcProducercodeDO
	@ManyToOne
	@JoinColumn(name="PRODUCERCODEID")
	private PcProducercodeDO pcProducercode;

	public PcEffectivedatedfieldDO() {
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

	public BigDecimal getBasedonid() {
		return this.basedonid;
	}

	public void setBasedonid(BigDecimal basedonid) {
		this.basedonid = basedonid;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public BigDecimal getBillingcontact() {
		return this.billingcontact;
	}

	public void setBillingcontact(BigDecimal billingcontact) {
		this.billingcontact = billingcontact;
	}

	public BigDecimal getBranchid() {
		return this.branchid;
	}

	public void setBranchid(BigDecimal branchid) {
		this.branchid = branchid;
	}

	public BigDecimal getChangetype() {
		return this.changetype;
	}

	public void setChangetype(BigDecimal changetype) {
		this.changetype = changetype;
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

	public String getEffectivedate() {
		return this.effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public String getExpirationdate() {
		return this.expirationdate;
	}

	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}

	public BigDecimal getFixedid() {
		return this.fixedid;
	}

	public void setFixedid(BigDecimal fixedid) {
		this.fixedid = fixedid;
	}

	public String getOfferingcode() {
		return this.offeringcode;
	}

	public void setOfferingcode(String offeringcode) {
		this.offeringcode = offeringcode;
	}

	public BigDecimal getPolicyaddress() {
		return this.policyaddress;
	}

	public void setPolicyaddress(BigDecimal policyaddress) {
		this.policyaddress = policyaddress;
	}

	public BigDecimal getPrimarylocation() {
		return this.primarylocation;
	}

	public void setPrimarylocation(BigDecimal primarylocation) {
		this.primarylocation = primarylocation;
	}

	public BigDecimal getPrimarynamedinsured() {
		return this.primarynamedinsured;
	}

	public void setPrimarynamedinsured(BigDecimal primarynamedinsured) {
		this.primarynamedinsured = primarynamedinsured;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public BigDecimal getSecondarynamedinsured() {
		return this.secondarynamedinsured;
	}

	public void setSecondarynamedinsured(BigDecimal secondarynamedinsured) {
		this.secondarynamedinsured = secondarynamedinsured;
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

	public PcProducercodeDO getPcProducercode() {
		return this.pcProducercode;
	}

	public void setPcProducercode(PcProducercodeDO pcProducercode) {
		this.pcProducercode = pcProducercode;
	}

}