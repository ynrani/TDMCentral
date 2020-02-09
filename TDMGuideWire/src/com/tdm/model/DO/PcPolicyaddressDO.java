package com.tdm.model.DO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PC_POLICYADDRESS database table.
 * 
 */
@Entity
@Table(name="PC_POLICYADDRESS")
@NamedQuery(name="PcPolicyaddressDO.findAll", query="SELECT p FROM PcPolicyaddressDO p")
public class PcPolicyaddressDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal address;

	private String addressline1internal;

	private String addressline1kanjiinternal;

	private String addressline2internal;

	private String addressline2kanjiinternal;

	private String addressline3internal;

	private BigDecimal addresstypeinternal;

	private BigDecimal archivepartition;

	private BigDecimal basedonid;

	private BigDecimal beanversion;

	private BigDecimal branchid;

	private String cedexbureauinternal;

	private BigDecimal cedexinternal;

	private BigDecimal changetype;

	private String cityinternal;

	private String cityinternaldenorm;

	private String citykanjiinternal;

	private BigDecimal countryinternal;

	private String countyinternal;

	private String createtime;

	private BigDecimal createuserid;

	private String descriptioninternal;

	@Basic(optional = false)
	@Column(name = "LastTouched", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectivedate;
	@Basic(optional = false)
	@Column(name = "LastTouched", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationdate;

	private BigDecimal fixedid;

	private String postalcodeinternal;

	private String postalcodeinternaldenorm;

	private String publicid;

	private BigDecimal stateinternal;

	private String updatetime;

	private BigDecimal updateuserid;

	public PcPolicyaddressDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAddress() {
		return this.address;
	}

	public void setAddress(BigDecimal address) {
		this.address = address;
	}

	public String getAddressline1internal() {
		return this.addressline1internal;
	}

	public void setAddressline1internal(String addressline1internal) {
		this.addressline1internal = addressline1internal;
	}

	public String getAddressline1kanjiinternal() {
		return this.addressline1kanjiinternal;
	}

	public void setAddressline1kanjiinternal(String addressline1kanjiinternal) {
		this.addressline1kanjiinternal = addressline1kanjiinternal;
	}

	public String getAddressline2internal() {
		return this.addressline2internal;
	}

	public void setAddressline2internal(String addressline2internal) {
		this.addressline2internal = addressline2internal;
	}

	public String getAddressline2kanjiinternal() {
		return this.addressline2kanjiinternal;
	}

	public void setAddressline2kanjiinternal(String addressline2kanjiinternal) {
		this.addressline2kanjiinternal = addressline2kanjiinternal;
	}

	public String getAddressline3internal() {
		return this.addressline3internal;
	}

	public void setAddressline3internal(String addressline3internal) {
		this.addressline3internal = addressline3internal;
	}

	public BigDecimal getAddresstypeinternal() {
		return this.addresstypeinternal;
	}

	public void setAddresstypeinternal(BigDecimal addresstypeinternal) {
		this.addresstypeinternal = addresstypeinternal;
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

	public String getCedexbureauinternal() {
		return this.cedexbureauinternal;
	}

	public void setCedexbureauinternal(String cedexbureauinternal) {
		this.cedexbureauinternal = cedexbureauinternal;
	}

	public BigDecimal getCedexinternal() {
		return this.cedexinternal;
	}

	public void setCedexinternal(BigDecimal cedexinternal) {
		this.cedexinternal = cedexinternal;
	}

	public BigDecimal getChangetype() {
		return this.changetype;
	}

	public void setChangetype(BigDecimal changetype) {
		this.changetype = changetype;
	}

	public String getCityinternal() {
		return this.cityinternal;
	}

	public void setCityinternal(String cityinternal) {
		this.cityinternal = cityinternal;
	}

	public String getCityinternaldenorm() {
		return this.cityinternaldenorm;
	}

	public void setCityinternaldenorm(String cityinternaldenorm) {
		this.cityinternaldenorm = cityinternaldenorm;
	}

	public String getCitykanjiinternal() {
		return this.citykanjiinternal;
	}

	public void setCitykanjiinternal(String citykanjiinternal) {
		this.citykanjiinternal = citykanjiinternal;
	}

	public BigDecimal getCountryinternal() {
		return this.countryinternal;
	}

	public void setCountryinternal(BigDecimal countryinternal) {
		this.countryinternal = countryinternal;
	}

	public String getCountyinternal() {
		return this.countyinternal;
	}

	public void setCountyinternal(String countyinternal) {
		this.countyinternal = countyinternal;
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

	public String getDescriptioninternal() {
		return this.descriptioninternal;
	}

	public void setDescriptioninternal(String descriptioninternal) {
		this.descriptioninternal = descriptioninternal;
	}

	public Date getEffectivedate() {
		return this.effectivedate;
	}

	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}

	public Date getExpirationdate() {
		return this.expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public BigDecimal getFixedid() {
		return this.fixedid;
	}

	public void setFixedid(BigDecimal fixedid) {
		this.fixedid = fixedid;
	}

	public String getPostalcodeinternal() {
		return this.postalcodeinternal;
	}

	public void setPostalcodeinternal(String postalcodeinternal) {
		this.postalcodeinternal = postalcodeinternal;
	}

	public String getPostalcodeinternaldenorm() {
		return this.postalcodeinternaldenorm;
	}

	public void setPostalcodeinternaldenorm(String postalcodeinternaldenorm) {
		this.postalcodeinternaldenorm = postalcodeinternaldenorm;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public BigDecimal getStateinternal() {
		return this.stateinternal;
	}

	public void setStateinternal(BigDecimal stateinternal) {
		this.stateinternal = stateinternal;
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