package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_PERSONALVEHICLE database table.
 * 
 */
@Entity
@Table(name="PC_PERSONALVEHICLE")
@NamedQuery(name="PcPersonalvehicleDO.findAll", query="SELECT p FROM PcPersonalvehicleDO p")
public class PcPersonalvehicleDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal annualmileage;

	private BigDecimal archivepartition;

	private BigDecimal basedonid;

	private BigDecimal basisamount;

	private BigDecimal beanversion;

	private BigDecimal bodytype;

	private BigDecimal branchid;

	private BigDecimal changetype;

	private String color;

	private BigDecimal commutingmiles;

	private BigDecimal costnew;

	@Column(name="COSTNEW_CUR")
	private BigDecimal costnewCur;

	private String createtime;

	private BigDecimal createuserid;

	@Column(name="DATEADDED_CGIC")
	private String dateaddedCgic;

	private String effectivedate;

	private String expirationdate;

	private BigDecimal fixedid;

	private BigDecimal garagelocation;

	private BigDecimal initialconditionscreated;

	private BigDecimal initialcoveragescreated;

	private BigDecimal initialexclusionscreated;

	private BigDecimal leaseorrent;

	private BigDecimal lengthoflease;

	private String licenseplate;

	private BigDecimal licensestate;

	private String make;

	private String model;

	private BigDecimal paline;

	private BigDecimal pipcovered;

	private BigDecimal preferredcoveragecurrency;

	private BigDecimal primaryuse;

	private String publicid;

	private BigDecimal quickquotenumber;

	private String referencedateinternal;

	@Column(name="SR22_CGIC")
	private BigDecimal sr22Cgic;

	private BigDecimal statedvalue;

	@Column(name="STATEDVALUE_CUR")
	private BigDecimal statedvalueCur;

	private String updatetime;

	private BigDecimal updateuserid;

	private BigDecimal vehiclenumber;

	private BigDecimal vehicletype;

	private String vin;

	@Column(name="\"YEAR\"")
	private BigDecimal year;

	//bi-directional many-to-one association to PcContactDO
	@ManyToOne
	@JoinColumn(name="OWNER_CGIC")
	private PcContactDO pcContact;

	public PcPersonalvehicleDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAnnualmileage() {
		return this.annualmileage;
	}

	public void setAnnualmileage(BigDecimal annualmileage) {
		this.annualmileage = annualmileage;
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

	public BigDecimal getBasisamount() {
		return this.basisamount;
	}

	public void setBasisamount(BigDecimal basisamount) {
		this.basisamount = basisamount;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public BigDecimal getBodytype() {
		return this.bodytype;
	}

	public void setBodytype(BigDecimal bodytype) {
		this.bodytype = bodytype;
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

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getCommutingmiles() {
		return this.commutingmiles;
	}

	public void setCommutingmiles(BigDecimal commutingmiles) {
		this.commutingmiles = commutingmiles;
	}

	public BigDecimal getCostnew() {
		return this.costnew;
	}

	public void setCostnew(BigDecimal costnew) {
		this.costnew = costnew;
	}

	public BigDecimal getCostnewCur() {
		return this.costnewCur;
	}

	public void setCostnewCur(BigDecimal costnewCur) {
		this.costnewCur = costnewCur;
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

	public String getDateaddedCgic() {
		return this.dateaddedCgic;
	}

	public void setDateaddedCgic(String dateaddedCgic) {
		this.dateaddedCgic = dateaddedCgic;
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

	public BigDecimal getGaragelocation() {
		return this.garagelocation;
	}

	public void setGaragelocation(BigDecimal garagelocation) {
		this.garagelocation = garagelocation;
	}

	public BigDecimal getInitialconditionscreated() {
		return this.initialconditionscreated;
	}

	public void setInitialconditionscreated(BigDecimal initialconditionscreated) {
		this.initialconditionscreated = initialconditionscreated;
	}

	public BigDecimal getInitialcoveragescreated() {
		return this.initialcoveragescreated;
	}

	public void setInitialcoveragescreated(BigDecimal initialcoveragescreated) {
		this.initialcoveragescreated = initialcoveragescreated;
	}

	public BigDecimal getInitialexclusionscreated() {
		return this.initialexclusionscreated;
	}

	public void setInitialexclusionscreated(BigDecimal initialexclusionscreated) {
		this.initialexclusionscreated = initialexclusionscreated;
	}

	public BigDecimal getLeaseorrent() {
		return this.leaseorrent;
	}

	public void setLeaseorrent(BigDecimal leaseorrent) {
		this.leaseorrent = leaseorrent;
	}

	public BigDecimal getLengthoflease() {
		return this.lengthoflease;
	}

	public void setLengthoflease(BigDecimal lengthoflease) {
		this.lengthoflease = lengthoflease;
	}

	public String getLicenseplate() {
		return this.licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public BigDecimal getLicensestate() {
		return this.licensestate;
	}

	public void setLicensestate(BigDecimal licensestate) {
		this.licensestate = licensestate;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getPaline() {
		return this.paline;
	}

	public void setPaline(BigDecimal paline) {
		this.paline = paline;
	}

	public BigDecimal getPipcovered() {
		return this.pipcovered;
	}

	public void setPipcovered(BigDecimal pipcovered) {
		this.pipcovered = pipcovered;
	}

	public BigDecimal getPreferredcoveragecurrency() {
		return this.preferredcoveragecurrency;
	}

	public void setPreferredcoveragecurrency(BigDecimal preferredcoveragecurrency) {
		this.preferredcoveragecurrency = preferredcoveragecurrency;
	}

	public BigDecimal getPrimaryuse() {
		return this.primaryuse;
	}

	public void setPrimaryuse(BigDecimal primaryuse) {
		this.primaryuse = primaryuse;
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

	public String getReferencedateinternal() {
		return this.referencedateinternal;
	}

	public void setReferencedateinternal(String referencedateinternal) {
		this.referencedateinternal = referencedateinternal;
	}

	public BigDecimal getSr22Cgic() {
		return this.sr22Cgic;
	}

	public void setSr22Cgic(BigDecimal sr22Cgic) {
		this.sr22Cgic = sr22Cgic;
	}

	public BigDecimal getStatedvalue() {
		return this.statedvalue;
	}

	public void setStatedvalue(BigDecimal statedvalue) {
		this.statedvalue = statedvalue;
	}

	public BigDecimal getStatedvalueCur() {
		return this.statedvalueCur;
	}

	public void setStatedvalueCur(BigDecimal statedvalueCur) {
		this.statedvalueCur = statedvalueCur;
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

	public BigDecimal getVehiclenumber() {
		return this.vehiclenumber;
	}

	public void setVehiclenumber(BigDecimal vehiclenumber) {
		this.vehiclenumber = vehiclenumber;
	}

	public BigDecimal getVehicletype() {
		return this.vehicletype;
	}

	public void setVehicletype(BigDecimal vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public BigDecimal getYear() {
		return this.year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public PcContactDO getPcContact() {
		return this.pcContact;
	}

	public void setPcContact(PcContactDO pcContact) {
		this.pcContact = pcContact;
	}

}