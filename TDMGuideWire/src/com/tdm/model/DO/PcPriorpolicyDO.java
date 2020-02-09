package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_PRIORPOLICY database table.
 * 
 */
@Entity
@Table(name="PC_PRIORPOLICY")
@NamedQuery(name="PcPriorpolicyDO.findAll", query="SELECT p FROM PcPriorpolicyDO p")
public class PcPriorpolicyDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal annualpremium;

	@Column(name="ANNUALPREMIUM_CUR")
	private BigDecimal annualpremiumCur;

	private BigDecimal beanversion;

	private String carrier;

	private String createtime;

	private BigDecimal createuserid;

	private String effectivedate;

	private String expirationdate;

	private BigDecimal expmod;

	private BigDecimal numlosses;

	private String policylinepatterncode;

	private String policynumber;

	private String publicid;

	private BigDecimal subtype;

	private BigDecimal totallosses;

	@Column(name="TOTALLOSSES_CUR")
	private BigDecimal totallossesCur;

	private BigDecimal totalpremium;

	@Column(name="TOTALPREMIUM_CUR")
	private BigDecimal totalpremiumCur;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcPolicyDO
	@ManyToOne
	@JoinColumn(name="POLICYID")
	private PcPolicyDO pcPolicy;

	public PcPriorpolicyDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAnnualpremium() {
		return this.annualpremium;
	}

	public void setAnnualpremium(BigDecimal annualpremium) {
		this.annualpremium = annualpremium;
	}

	public BigDecimal getAnnualpremiumCur() {
		return this.annualpremiumCur;
	}

	public void setAnnualpremiumCur(BigDecimal annualpremiumCur) {
		this.annualpremiumCur = annualpremiumCur;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
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

	public BigDecimal getExpmod() {
		return this.expmod;
	}

	public void setExpmod(BigDecimal expmod) {
		this.expmod = expmod;
	}

	public BigDecimal getNumlosses() {
		return this.numlosses;
	}

	public void setNumlosses(BigDecimal numlosses) {
		this.numlosses = numlosses;
	}

	public String getPolicylinepatterncode() {
		return this.policylinepatterncode;
	}

	public void setPolicylinepatterncode(String policylinepatterncode) {
		this.policylinepatterncode = policylinepatterncode;
	}

	public String getPolicynumber() {
		return this.policynumber;
	}

	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public BigDecimal getSubtype() {
		return this.subtype;
	}

	public void setSubtype(BigDecimal subtype) {
		this.subtype = subtype;
	}

	public BigDecimal getTotallosses() {
		return this.totallosses;
	}

	public void setTotallosses(BigDecimal totallosses) {
		this.totallosses = totallosses;
	}

	public BigDecimal getTotallossesCur() {
		return this.totallossesCur;
	}

	public void setTotallossesCur(BigDecimal totallossesCur) {
		this.totallossesCur = totallossesCur;
	}

	public BigDecimal getTotalpremium() {
		return this.totalpremium;
	}

	public void setTotalpremium(BigDecimal totalpremium) {
		this.totalpremium = totalpremium;
	}

	public BigDecimal getTotalpremiumCur() {
		return this.totalpremiumCur;
	}

	public void setTotalpremiumCur(BigDecimal totalpremiumCur) {
		this.totalpremiumCur = totalpremiumCur;
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

	public PcPolicyDO getPcPolicy() {
		return this.pcPolicy;
	}

	public void setPcPolicy(PcPolicyDO pcPolicy) {
		this.pcPolicy = pcPolicy;
	}

}