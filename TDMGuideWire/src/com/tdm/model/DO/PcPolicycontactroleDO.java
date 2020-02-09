package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_POLICYCONTACTROLE database table.
 * 
 */
@Entity
@Table(name="PC_POLICYCONTACTROLE")
@NamedQuery(name="PcPolicycontactroleDO.findAll", query="SELECT p FROM PcPolicycontactroleDO p")
public class PcPolicycontactroleDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal accountcontactrole;

	private BigDecimal applicablegooddriverdiscount;

	private BigDecimal archivepartition;

	private BigDecimal basedonid;

	private BigDecimal beanversion;

	private BigDecimal branchid;

	private BigDecimal businessautoline;

	private BigDecimal businessownersline;

	private BigDecimal changetype;

	private BigDecimal classcodeid;

	private BigDecimal commercialpropertyline;

	private String companynameinternal;

	private String companynameinternaldenorm;

	private String companynamekanjiinternal;

	private String createtime;

	private BigDecimal createuserid;

	@Column(name="DATEADDED_CGICINTERNAL")
	private String dateaddedCgicinternal;

	@Column(name="DATEEXCLUDED_CGICINTERNAL")
	private String dateexcludedCgicinternal;

	private String dateofbirthinternal;

	private BigDecimal donotordermvr;

	private String effectivedate;

	private BigDecimal excludedinternal;

	private String expirationdate;

	private String firstnameinternal;

	private String firstnameinternaldenorm;

	private String firstnamekanjiinternal;

	private BigDecimal fixedid;

	private BigDecimal generalliabilityline;

	private BigDecimal included;

	private BigDecimal inlandmarineline;

	private String lastnameinternal;

	private String lastnameinternaldenorm;

	private String lastnamekanjiinternal;

	private String licensenumberinternal;

	private BigDecimal licensestateinternal;

	private BigDecimal maritalstatusinternal;

	private BigDecimal numberofaccidents;

	private BigDecimal numberofviolations;

	private BigDecimal ownershippct;

	private String particleinternal;

	private BigDecimal personalautoline;

	private BigDecimal policyline;

	private String publicid;

	private BigDecimal quickquotenumber;

	@Lob
	private String relationship;

	private BigDecimal relationshiptitleinternal;

	private BigDecimal seqnumber;

	@Column(name="SR22_CGICINTERNAL")
	private BigDecimal sr22Cgicinternal;

	@Column(name="\"STATE\"")
	private BigDecimal state;

	private BigDecimal subtype;

	private String updatetime;

	private BigDecimal updateuserid;

	private BigDecimal workerscompline;

	//bi-directional many-to-one association to PcContactDO
	@ManyToOne
	@JoinColumn(name="CONTACTDENORM")
	private PcContactDO pcContact;

	public PcPolicycontactroleDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAccountcontactrole() {
		return this.accountcontactrole;
	}

	public void setAccountcontactrole(BigDecimal accountcontactrole) {
		this.accountcontactrole = accountcontactrole;
	}

	public BigDecimal getApplicablegooddriverdiscount() {
		return this.applicablegooddriverdiscount;
	}

	public void setApplicablegooddriverdiscount(BigDecimal applicablegooddriverdiscount) {
		this.applicablegooddriverdiscount = applicablegooddriverdiscount;
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

	public BigDecimal getBranchid() {
		return this.branchid;
	}

	public void setBranchid(BigDecimal branchid) {
		this.branchid = branchid;
	}

	public BigDecimal getBusinessautoline() {
		return this.businessautoline;
	}

	public void setBusinessautoline(BigDecimal businessautoline) {
		this.businessautoline = businessautoline;
	}

	public BigDecimal getBusinessownersline() {
		return this.businessownersline;
	}

	public void setBusinessownersline(BigDecimal businessownersline) {
		this.businessownersline = businessownersline;
	}

	public BigDecimal getChangetype() {
		return this.changetype;
	}

	public void setChangetype(BigDecimal changetype) {
		this.changetype = changetype;
	}

	public BigDecimal getClasscodeid() {
		return this.classcodeid;
	}

	public void setClasscodeid(BigDecimal classcodeid) {
		this.classcodeid = classcodeid;
	}

	public BigDecimal getCommercialpropertyline() {
		return this.commercialpropertyline;
	}

	public void setCommercialpropertyline(BigDecimal commercialpropertyline) {
		this.commercialpropertyline = commercialpropertyline;
	}

	public String getCompanynameinternal() {
		return this.companynameinternal;
	}

	public void setCompanynameinternal(String companynameinternal) {
		this.companynameinternal = companynameinternal;
	}

	public String getCompanynameinternaldenorm() {
		return this.companynameinternaldenorm;
	}

	public void setCompanynameinternaldenorm(String companynameinternaldenorm) {
		this.companynameinternaldenorm = companynameinternaldenorm;
	}

	public String getCompanynamekanjiinternal() {
		return this.companynamekanjiinternal;
	}

	public void setCompanynamekanjiinternal(String companynamekanjiinternal) {
		this.companynamekanjiinternal = companynamekanjiinternal;
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

	public String getDateaddedCgicinternal() {
		return this.dateaddedCgicinternal;
	}

	public void setDateaddedCgicinternal(String dateaddedCgicinternal) {
		this.dateaddedCgicinternal = dateaddedCgicinternal;
	}

	public String getDateexcludedCgicinternal() {
		return this.dateexcludedCgicinternal;
	}

	public void setDateexcludedCgicinternal(String dateexcludedCgicinternal) {
		this.dateexcludedCgicinternal = dateexcludedCgicinternal;
	}

	public String getDateofbirthinternal() {
		return this.dateofbirthinternal;
	}

	public void setDateofbirthinternal(String dateofbirthinternal) {
		this.dateofbirthinternal = dateofbirthinternal;
	}

	public BigDecimal getDonotordermvr() {
		return this.donotordermvr;
	}

	public void setDonotordermvr(BigDecimal donotordermvr) {
		this.donotordermvr = donotordermvr;
	}

	public String getEffectivedate() {
		return this.effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public BigDecimal getExcludedinternal() {
		return this.excludedinternal;
	}

	public void setExcludedinternal(BigDecimal excludedinternal) {
		this.excludedinternal = excludedinternal;
	}

	public String getExpirationdate() {
		return this.expirationdate;
	}

	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}

	public String getFirstnameinternal() {
		return this.firstnameinternal;
	}

	public void setFirstnameinternal(String firstnameinternal) {
		this.firstnameinternal = firstnameinternal;
	}

	public String getFirstnameinternaldenorm() {
		return this.firstnameinternaldenorm;
	}

	public void setFirstnameinternaldenorm(String firstnameinternaldenorm) {
		this.firstnameinternaldenorm = firstnameinternaldenorm;
	}

	public String getFirstnamekanjiinternal() {
		return this.firstnamekanjiinternal;
	}

	public void setFirstnamekanjiinternal(String firstnamekanjiinternal) {
		this.firstnamekanjiinternal = firstnamekanjiinternal;
	}

	public BigDecimal getFixedid() {
		return this.fixedid;
	}

	public void setFixedid(BigDecimal fixedid) {
		this.fixedid = fixedid;
	}

	public BigDecimal getGeneralliabilityline() {
		return this.generalliabilityline;
	}

	public void setGeneralliabilityline(BigDecimal generalliabilityline) {
		this.generalliabilityline = generalliabilityline;
	}

	public BigDecimal getIncluded() {
		return this.included;
	}

	public void setIncluded(BigDecimal included) {
		this.included = included;
	}

	public BigDecimal getInlandmarineline() {
		return this.inlandmarineline;
	}

	public void setInlandmarineline(BigDecimal inlandmarineline) {
		this.inlandmarineline = inlandmarineline;
	}

	public String getLastnameinternal() {
		return this.lastnameinternal;
	}

	public void setLastnameinternal(String lastnameinternal) {
		this.lastnameinternal = lastnameinternal;
	}

	public String getLastnameinternaldenorm() {
		return this.lastnameinternaldenorm;
	}

	public void setLastnameinternaldenorm(String lastnameinternaldenorm) {
		this.lastnameinternaldenorm = lastnameinternaldenorm;
	}

	public String getLastnamekanjiinternal() {
		return this.lastnamekanjiinternal;
	}

	public void setLastnamekanjiinternal(String lastnamekanjiinternal) {
		this.lastnamekanjiinternal = lastnamekanjiinternal;
	}

	public String getLicensenumberinternal() {
		return this.licensenumberinternal;
	}

	public void setLicensenumberinternal(String licensenumberinternal) {
		this.licensenumberinternal = licensenumberinternal;
	}

	public BigDecimal getLicensestateinternal() {
		return this.licensestateinternal;
	}

	public void setLicensestateinternal(BigDecimal licensestateinternal) {
		this.licensestateinternal = licensestateinternal;
	}

	public BigDecimal getMaritalstatusinternal() {
		return this.maritalstatusinternal;
	}

	public void setMaritalstatusinternal(BigDecimal maritalstatusinternal) {
		this.maritalstatusinternal = maritalstatusinternal;
	}

	public BigDecimal getNumberofaccidents() {
		return this.numberofaccidents;
	}

	public void setNumberofaccidents(BigDecimal numberofaccidents) {
		this.numberofaccidents = numberofaccidents;
	}

	public BigDecimal getNumberofviolations() {
		return this.numberofviolations;
	}

	public void setNumberofviolations(BigDecimal numberofviolations) {
		this.numberofviolations = numberofviolations;
	}

	public BigDecimal getOwnershippct() {
		return this.ownershippct;
	}

	public void setOwnershippct(BigDecimal ownershippct) {
		this.ownershippct = ownershippct;
	}

	public String getParticleinternal() {
		return this.particleinternal;
	}

	public void setParticleinternal(String particleinternal) {
		this.particleinternal = particleinternal;
	}

	public BigDecimal getPersonalautoline() {
		return this.personalautoline;
	}

	public void setPersonalautoline(BigDecimal personalautoline) {
		this.personalautoline = personalautoline;
	}

	public BigDecimal getPolicyline() {
		return this.policyline;
	}

	public void setPolicyline(BigDecimal policyline) {
		this.policyline = policyline;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public BigDecimal getQuickquotenumber() {
		return this.quickquotenumber;
	}

	public void setQuickquotenumber(BigDecimal quickquotenumber) {
		this.quickquotenumber = quickquotenumber;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public BigDecimal getRelationshiptitleinternal() {
		return this.relationshiptitleinternal;
	}

	public void setRelationshiptitleinternal(BigDecimal relationshiptitleinternal) {
		this.relationshiptitleinternal = relationshiptitleinternal;
	}

	public BigDecimal getSeqnumber() {
		return this.seqnumber;
	}

	public void setSeqnumber(BigDecimal seqnumber) {
		this.seqnumber = seqnumber;
	}

	public BigDecimal getSr22Cgicinternal() {
		return this.sr22Cgicinternal;
	}

	public void setSr22Cgicinternal(BigDecimal sr22Cgicinternal) {
		this.sr22Cgicinternal = sr22Cgicinternal;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public BigDecimal getSubtype() {
		return this.subtype;
	}

	public void setSubtype(BigDecimal subtype) {
		this.subtype = subtype;
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

	public BigDecimal getWorkerscompline() {
		return this.workerscompline;
	}

	public void setWorkerscompline(BigDecimal workerscompline) {
		this.workerscompline = workerscompline;
	}

	public PcContactDO getPcContact() {
		return this.pcContact;
	}

	public void setPcContact(PcContactDO pcContact) {
		this.pcContact = pcContact;
	}

}