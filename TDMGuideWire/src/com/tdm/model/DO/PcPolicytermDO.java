package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PC_POLICYTERM database table.
 * 
 */
@Entity
@Table(name="PC_POLICYTERM")
@NamedQuery(name="PcPolicytermDO.findAll", query="SELECT p FROM PcPolicytermDO p")
public class PcPolicytermDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal beanversion;

	private BigDecimal bound;

	private String createtime;

	private BigDecimal createuserid;

	private BigDecimal daysreported;

	private BigDecimal depositamount;

	@Column(name="DEPOSITAMOUNT_CUR")
	private BigDecimal depositamountCur;

	private BigDecimal depositreleased;

	private BigDecimal finalauditoption;

	private BigDecimal generatereinsurables;

	private String lastrestoredate;

	private BigDecimal mostrecentterm;

	private String nextarchivecheckdate;

	private String nextrenewalcheckdate;

	private String nonrenewaddexplanation;

	private BigDecimal nonrenewreason;

	private BigDecimal policytermarchivestate;

	private BigDecimal prerenewaldirection;

	private String publicid;

	private BigDecimal retired;

	private BigDecimal totalestimatedpremium;

	@Column(name="TOTALESTIMATEDPREMIUM_CUR")
	private BigDecimal totalestimatedpremiumCur;

	private BigDecimal totalreportedpremium;

	@Column(name="TOTALREPORTEDPREMIUM_CUR")
	private BigDecimal totalreportedpremiumCur;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcPolicyperiodDO
	@OneToMany(mappedBy="pcPolicyterm")
	private List<PcPolicyperiodDO> pcPolicyperiods;

	//bi-directional many-to-one association to PcPolicyDO
	@ManyToOne
	@JoinColumn(name="POLICYID")
	private PcPolicyDO pcPolicy;

	public PcPolicytermDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public BigDecimal getBound() {
		return this.bound;
	}

	public void setBound(BigDecimal bound) {
		this.bound = bound;
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

	public BigDecimal getDaysreported() {
		return this.daysreported;
	}

	public void setDaysreported(BigDecimal daysreported) {
		this.daysreported = daysreported;
	}

	public BigDecimal getDepositamount() {
		return this.depositamount;
	}

	public void setDepositamount(BigDecimal depositamount) {
		this.depositamount = depositamount;
	}

	public BigDecimal getDepositamountCur() {
		return this.depositamountCur;
	}

	public void setDepositamountCur(BigDecimal depositamountCur) {
		this.depositamountCur = depositamountCur;
	}

	public BigDecimal getDepositreleased() {
		return this.depositreleased;
	}

	public void setDepositreleased(BigDecimal depositreleased) {
		this.depositreleased = depositreleased;
	}

	public BigDecimal getFinalauditoption() {
		return this.finalauditoption;
	}

	public void setFinalauditoption(BigDecimal finalauditoption) {
		this.finalauditoption = finalauditoption;
	}

	public BigDecimal getGeneratereinsurables() {
		return this.generatereinsurables;
	}

	public void setGeneratereinsurables(BigDecimal generatereinsurables) {
		this.generatereinsurables = generatereinsurables;
	}

	public String getLastrestoredate() {
		return this.lastrestoredate;
	}

	public void setLastrestoredate(String lastrestoredate) {
		this.lastrestoredate = lastrestoredate;
	}

	public BigDecimal getMostrecentterm() {
		return this.mostrecentterm;
	}

	public void setMostrecentterm(BigDecimal mostrecentterm) {
		this.mostrecentterm = mostrecentterm;
	}

	public String getNextarchivecheckdate() {
		return this.nextarchivecheckdate;
	}

	public void setNextarchivecheckdate(String nextarchivecheckdate) {
		this.nextarchivecheckdate = nextarchivecheckdate;
	}

	public String getNextrenewalcheckdate() {
		return this.nextrenewalcheckdate;
	}

	public void setNextrenewalcheckdate(String nextrenewalcheckdate) {
		this.nextrenewalcheckdate = nextrenewalcheckdate;
	}

	public String getNonrenewaddexplanation() {
		return this.nonrenewaddexplanation;
	}

	public void setNonrenewaddexplanation(String nonrenewaddexplanation) {
		this.nonrenewaddexplanation = nonrenewaddexplanation;
	}

	public BigDecimal getNonrenewreason() {
		return this.nonrenewreason;
	}

	public void setNonrenewreason(BigDecimal nonrenewreason) {
		this.nonrenewreason = nonrenewreason;
	}

	public BigDecimal getPolicytermarchivestate() {
		return this.policytermarchivestate;
	}

	public void setPolicytermarchivestate(BigDecimal policytermarchivestate) {
		this.policytermarchivestate = policytermarchivestate;
	}

	public BigDecimal getPrerenewaldirection() {
		return this.prerenewaldirection;
	}

	public void setPrerenewaldirection(BigDecimal prerenewaldirection) {
		this.prerenewaldirection = prerenewaldirection;
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

	public BigDecimal getTotalestimatedpremium() {
		return this.totalestimatedpremium;
	}

	public void setTotalestimatedpremium(BigDecimal totalestimatedpremium) {
		this.totalestimatedpremium = totalestimatedpremium;
	}

	public BigDecimal getTotalestimatedpremiumCur() {
		return this.totalestimatedpremiumCur;
	}

	public void setTotalestimatedpremiumCur(BigDecimal totalestimatedpremiumCur) {
		this.totalestimatedpremiumCur = totalestimatedpremiumCur;
	}

	public BigDecimal getTotalreportedpremium() {
		return this.totalreportedpremium;
	}

	public void setTotalreportedpremium(BigDecimal totalreportedpremium) {
		this.totalreportedpremium = totalreportedpremium;
	}

	public BigDecimal getTotalreportedpremiumCur() {
		return this.totalreportedpremiumCur;
	}

	public void setTotalreportedpremiumCur(BigDecimal totalreportedpremiumCur) {
		this.totalreportedpremiumCur = totalreportedpremiumCur;
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

	public List<PcPolicyperiodDO> getPcPolicyperiods() {
		return this.pcPolicyperiods;
	}

	public void setPcPolicyperiods(List<PcPolicyperiodDO> pcPolicyperiods) {
		this.pcPolicyperiods = pcPolicyperiods;
	}

	public PcPolicyperiodDO addPcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		getPcPolicyperiods().add(pcPolicyperiod);
		pcPolicyperiod.setPcPolicyterm(this);

		return pcPolicyperiod;
	}

	public PcPolicyperiodDO removePcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		getPcPolicyperiods().remove(pcPolicyperiod);
		pcPolicyperiod.setPcPolicyterm(null);

		return pcPolicyperiod;
	}

	public PcPolicyDO getPcPolicy() {
		return this.pcPolicy;
	}

	public void setPcPolicy(PcPolicyDO pcPolicy) {
		this.pcPolicy = pcPolicy;
	}

}