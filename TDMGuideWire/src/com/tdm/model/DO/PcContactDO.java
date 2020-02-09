package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PC_CONTACT database table.
 * 
 */
@Entity
@Table(name="PC_CONTACT")
@NamedQuery(name="PcContactDO.findAll", query="SELECT p FROM PcContactDO p")
public class PcContactDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal accountholdercount;

	private String addressbookuid;

	private String adjudicatorlicense;

	private BigDecimal autosync;

	private BigDecimal beanversion;

	private String cellphone;

	private BigDecimal cellphonecountry;

	private String cellphoneextension;

	private String citydenorm;

	private String citykanjidenorm;

	private BigDecimal country;

	private String createtime;

	private BigDecimal createuserid;

	private String dateofbirth;

	private String emailaddress1;

	private String emailaddress2;

	private String employeenumber;

	private BigDecimal externalversion;

	private String faxphone;

	private BigDecimal faxphonecountry;

	private String faxphoneextension;

	private String firstname;

	private String firstnamedenorm;

	private String firstnamekanji;

	private String formername;

	private BigDecimal gender;

	private String homephone;

	private BigDecimal homephonecountry;

	private String homephoneextension;

	private String lastname;

	private String lastnamedenorm;

	private String lastnamekanji;

	private String lastupdatetime;

	private String licensenumber;

	private BigDecimal licensestate;

	private BigDecimal loadcommandid;

	private BigDecimal loadrelatedcontacts;

	private BigDecimal maritalstatus;

	private String middlename;

	private String name;

	private String namedenorm;

	private String namekanji;

	@Lob
	private String notes;

	private BigDecimal numdependents;

	private BigDecimal numdependentsu18;

	private BigDecimal numdependentsu25;

	private String occupation;

	private String particle;

	private String postalcodedenorm;

	private BigDecimal preferred;

	private BigDecimal preferredcurrency;

	private BigDecimal preferredsettlementcurrency;

	@Column(name="\"PREFIX\"")
	private BigDecimal prefix;

	private BigDecimal primaryaddressid;

	private BigDecimal primarylanguage;

	private BigDecimal primarylocale;

	private BigDecimal primaryphone;

	private String publicid;

	private BigDecimal retired;

	private BigDecimal score;

	@Column(name="SR22_CGIC")
	private BigDecimal sr22Cgic;

	@Column(name="\"STATE\"")
	private BigDecimal state;

	private BigDecimal subtype;

	@Column(name="\"SUFFIX\"")
	private BigDecimal suffix;

	private BigDecimal taxfilingstatus;

	private String taxid;

	private BigDecimal taxstatus;

	private String temporarylastupdatetime;

	private String updatetime;

	private BigDecimal updateuserid;

	private BigDecimal validationlevel;

	private String vendornumber;

	private BigDecimal vendortype;

	private BigDecimal venuetype;

	private BigDecimal withholdingrate;

	private String workphone;

	private BigDecimal workphonecountry;

	private String workphoneextension;

	//bi-directional many-to-one association to PcPersonalvehicleDO
	@OneToMany(mappedBy="pcContact")
	private List<PcPersonalvehicleDO> pcPersonalvehicles;

	//bi-directional many-to-one association to PcPolicycontactroleDO
	@OneToMany(mappedBy="pcContact")
	private List<PcPolicycontactroleDO> pcPolicycontactroles;

	//bi-directional many-to-one association to PcPolicyperiodDO
	@OneToMany(mappedBy="pcContact", fetch=FetchType.LAZY)
	private List<PcPolicyperiodDO> pcPolicyperiods;

	//bi-directional many-to-one association to PcReinsuranceagreementDO
	@OneToMany(mappedBy="pcContact")
	private List<PcReinsuranceagreementDO> pcReinsuranceagreements;

	public PcContactDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAccountholdercount() {
		return this.accountholdercount;
	}

	public void setAccountholdercount(BigDecimal accountholdercount) {
		this.accountholdercount = accountholdercount;
	}

	public String getAddressbookuid() {
		return this.addressbookuid;
	}

	public void setAddressbookuid(String addressbookuid) {
		this.addressbookuid = addressbookuid;
	}

	public String getAdjudicatorlicense() {
		return this.adjudicatorlicense;
	}

	public void setAdjudicatorlicense(String adjudicatorlicense) {
		this.adjudicatorlicense = adjudicatorlicense;
	}

	public BigDecimal getAutosync() {
		return this.autosync;
	}

	public void setAutosync(BigDecimal autosync) {
		this.autosync = autosync;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public BigDecimal getCellphonecountry() {
		return this.cellphonecountry;
	}

	public void setCellphonecountry(BigDecimal cellphonecountry) {
		this.cellphonecountry = cellphonecountry;
	}

	public String getCellphoneextension() {
		return this.cellphoneextension;
	}

	public void setCellphoneextension(String cellphoneextension) {
		this.cellphoneextension = cellphoneextension;
	}

	public String getCitydenorm() {
		return this.citydenorm;
	}

	public void setCitydenorm(String citydenorm) {
		this.citydenorm = citydenorm;
	}

	public String getCitykanjidenorm() {
		return this.citykanjidenorm;
	}

	public void setCitykanjidenorm(String citykanjidenorm) {
		this.citykanjidenorm = citykanjidenorm;
	}

	public BigDecimal getCountry() {
		return this.country;
	}

	public void setCountry(BigDecimal country) {
		this.country = country;
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

	public String getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmailaddress1() {
		return this.emailaddress1;
	}

	public void setEmailaddress1(String emailaddress1) {
		this.emailaddress1 = emailaddress1;
	}

	public String getEmailaddress2() {
		return this.emailaddress2;
	}

	public void setEmailaddress2(String emailaddress2) {
		this.emailaddress2 = emailaddress2;
	}

	public String getEmployeenumber() {
		return this.employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public BigDecimal getExternalversion() {
		return this.externalversion;
	}

	public void setExternalversion(BigDecimal externalversion) {
		this.externalversion = externalversion;
	}

	public String getFaxphone() {
		return this.faxphone;
	}

	public void setFaxphone(String faxphone) {
		this.faxphone = faxphone;
	}

	public BigDecimal getFaxphonecountry() {
		return this.faxphonecountry;
	}

	public void setFaxphonecountry(BigDecimal faxphonecountry) {
		this.faxphonecountry = faxphonecountry;
	}

	public String getFaxphoneextension() {
		return this.faxphoneextension;
	}

	public void setFaxphoneextension(String faxphoneextension) {
		this.faxphoneextension = faxphoneextension;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstnamedenorm() {
		return this.firstnamedenorm;
	}

	public void setFirstnamedenorm(String firstnamedenorm) {
		this.firstnamedenorm = firstnamedenorm;
	}

	public String getFirstnamekanji() {
		return this.firstnamekanji;
	}

	public void setFirstnamekanji(String firstnamekanji) {
		this.firstnamekanji = firstnamekanji;
	}

	public String getFormername() {
		return this.formername;
	}

	public void setFormername(String formername) {
		this.formername = formername;
	}

	public BigDecimal getGender() {
		return this.gender;
	}

	public void setGender(BigDecimal gender) {
		this.gender = gender;
	}

	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public BigDecimal getHomephonecountry() {
		return this.homephonecountry;
	}

	public void setHomephonecountry(BigDecimal homephonecountry) {
		this.homephonecountry = homephonecountry;
	}

	public String getHomephoneextension() {
		return this.homephoneextension;
	}

	public void setHomephoneextension(String homephoneextension) {
		this.homephoneextension = homephoneextension;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastnamedenorm() {
		return this.lastnamedenorm;
	}

	public void setLastnamedenorm(String lastnamedenorm) {
		this.lastnamedenorm = lastnamedenorm;
	}

	public String getLastnamekanji() {
		return this.lastnamekanji;
	}

	public void setLastnamekanji(String lastnamekanji) {
		this.lastnamekanji = lastnamekanji;
	}

	public String getLastupdatetime() {
		return this.lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getLicensenumber() {
		return this.licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	public BigDecimal getLicensestate() {
		return this.licensestate;
	}

	public void setLicensestate(BigDecimal licensestate) {
		this.licensestate = licensestate;
	}

	public BigDecimal getLoadcommandid() {
		return this.loadcommandid;
	}

	public void setLoadcommandid(BigDecimal loadcommandid) {
		this.loadcommandid = loadcommandid;
	}

	public BigDecimal getLoadrelatedcontacts() {
		return this.loadrelatedcontacts;
	}

	public void setLoadrelatedcontacts(BigDecimal loadrelatedcontacts) {
		this.loadrelatedcontacts = loadrelatedcontacts;
	}

	public BigDecimal getMaritalstatus() {
		return this.maritalstatus;
	}

	public void setMaritalstatus(BigDecimal maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamedenorm() {
		return this.namedenorm;
	}

	public void setNamedenorm(String namedenorm) {
		this.namedenorm = namedenorm;
	}

	public String getNamekanji() {
		return this.namekanji;
	}

	public void setNamekanji(String namekanji) {
		this.namekanji = namekanji;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getNumdependents() {
		return this.numdependents;
	}

	public void setNumdependents(BigDecimal numdependents) {
		this.numdependents = numdependents;
	}

	public BigDecimal getNumdependentsu18() {
		return this.numdependentsu18;
	}

	public void setNumdependentsu18(BigDecimal numdependentsu18) {
		this.numdependentsu18 = numdependentsu18;
	}

	public BigDecimal getNumdependentsu25() {
		return this.numdependentsu25;
	}

	public void setNumdependentsu25(BigDecimal numdependentsu25) {
		this.numdependentsu25 = numdependentsu25;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getParticle() {
		return this.particle;
	}

	public void setParticle(String particle) {
		this.particle = particle;
	}

	public String getPostalcodedenorm() {
		return this.postalcodedenorm;
	}

	public void setPostalcodedenorm(String postalcodedenorm) {
		this.postalcodedenorm = postalcodedenorm;
	}

	public BigDecimal getPreferred() {
		return this.preferred;
	}

	public void setPreferred(BigDecimal preferred) {
		this.preferred = preferred;
	}

	public BigDecimal getPreferredcurrency() {
		return this.preferredcurrency;
	}

	public void setPreferredcurrency(BigDecimal preferredcurrency) {
		this.preferredcurrency = preferredcurrency;
	}

	public BigDecimal getPreferredsettlementcurrency() {
		return this.preferredsettlementcurrency;
	}

	public void setPreferredsettlementcurrency(BigDecimal preferredsettlementcurrency) {
		this.preferredsettlementcurrency = preferredsettlementcurrency;
	}

	public BigDecimal getPrefix() {
		return this.prefix;
	}

	public void setPrefix(BigDecimal prefix) {
		this.prefix = prefix;
	}

	public BigDecimal getPrimaryaddressid() {
		return this.primaryaddressid;
	}

	public void setPrimaryaddressid(BigDecimal primaryaddressid) {
		this.primaryaddressid = primaryaddressid;
	}

	public BigDecimal getPrimarylanguage() {
		return this.primarylanguage;
	}

	public void setPrimarylanguage(BigDecimal primarylanguage) {
		this.primarylanguage = primarylanguage;
	}

	public BigDecimal getPrimarylocale() {
		return this.primarylocale;
	}

	public void setPrimarylocale(BigDecimal primarylocale) {
		this.primarylocale = primarylocale;
	}

	public BigDecimal getPrimaryphone() {
		return this.primaryphone;
	}

	public void setPrimaryphone(BigDecimal primaryphone) {
		this.primaryphone = primaryphone;
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

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getSr22Cgic() {
		return this.sr22Cgic;
	}

	public void setSr22Cgic(BigDecimal sr22Cgic) {
		this.sr22Cgic = sr22Cgic;
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

	public BigDecimal getSuffix() {
		return this.suffix;
	}

	public void setSuffix(BigDecimal suffix) {
		this.suffix = suffix;
	}

	public BigDecimal getTaxfilingstatus() {
		return this.taxfilingstatus;
	}

	public void setTaxfilingstatus(BigDecimal taxfilingstatus) {
		this.taxfilingstatus = taxfilingstatus;
	}

	public String getTaxid() {
		return this.taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public BigDecimal getTaxstatus() {
		return this.taxstatus;
	}

	public void setTaxstatus(BigDecimal taxstatus) {
		this.taxstatus = taxstatus;
	}

	public String getTemporarylastupdatetime() {
		return this.temporarylastupdatetime;
	}

	public void setTemporarylastupdatetime(String temporarylastupdatetime) {
		this.temporarylastupdatetime = temporarylastupdatetime;
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

	public BigDecimal getValidationlevel() {
		return this.validationlevel;
	}

	public void setValidationlevel(BigDecimal validationlevel) {
		this.validationlevel = validationlevel;
	}

	public String getVendornumber() {
		return this.vendornumber;
	}

	public void setVendornumber(String vendornumber) {
		this.vendornumber = vendornumber;
	}

	public BigDecimal getVendortype() {
		return this.vendortype;
	}

	public void setVendortype(BigDecimal vendortype) {
		this.vendortype = vendortype;
	}

	public BigDecimal getVenuetype() {
		return this.venuetype;
	}

	public void setVenuetype(BigDecimal venuetype) {
		this.venuetype = venuetype;
	}

	public BigDecimal getWithholdingrate() {
		return this.withholdingrate;
	}

	public void setWithholdingrate(BigDecimal withholdingrate) {
		this.withholdingrate = withholdingrate;
	}

	public String getWorkphone() {
		return this.workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public BigDecimal getWorkphonecountry() {
		return this.workphonecountry;
	}

	public void setWorkphonecountry(BigDecimal workphonecountry) {
		this.workphonecountry = workphonecountry;
	}

	public String getWorkphoneextension() {
		return this.workphoneextension;
	}

	public void setWorkphoneextension(String workphoneextension) {
		this.workphoneextension = workphoneextension;
	}

	public List<PcPersonalvehicleDO> getPcPersonalvehicles() {
		return this.pcPersonalvehicles;
	}

	public void setPcPersonalvehicles(List<PcPersonalvehicleDO> pcPersonalvehicles) {
		this.pcPersonalvehicles = pcPersonalvehicles;
	}

	public PcPersonalvehicleDO addPcPersonalvehicle(PcPersonalvehicleDO pcPersonalvehicle) {
		getPcPersonalvehicles().add(pcPersonalvehicle);
		pcPersonalvehicle.setPcContact(this);

		return pcPersonalvehicle;
	}

	public PcPersonalvehicleDO removePcPersonalvehicle(PcPersonalvehicleDO pcPersonalvehicle) {
		getPcPersonalvehicles().remove(pcPersonalvehicle);
		pcPersonalvehicle.setPcContact(null);

		return pcPersonalvehicle;
	}

	public List<PcPolicycontactroleDO> getPcPolicycontactroles() {
		return this.pcPolicycontactroles;
	}

	public void setPcPolicycontactroles(List<PcPolicycontactroleDO> pcPolicycontactroles) {
		this.pcPolicycontactroles = pcPolicycontactroles;
	}

	public PcPolicycontactroleDO addPcPolicycontactrole(PcPolicycontactroleDO pcPolicycontactrole) {
		getPcPolicycontactroles().add(pcPolicycontactrole);
		pcPolicycontactrole.setPcContact(this);

		return pcPolicycontactrole;
	}

	public PcPolicycontactroleDO removePcPolicycontactrole(PcPolicycontactroleDO pcPolicycontactrole) {
		getPcPolicycontactroles().remove(pcPolicycontactrole);
		pcPolicycontactrole.setPcContact(null);

		return pcPolicycontactrole;
	}

	public List<PcPolicyperiodDO> getPcPolicyperiods() {
		return this.pcPolicyperiods;
	}

	public void setPcPolicyperiods(List<PcPolicyperiodDO> pcPolicyperiods) {
		this.pcPolicyperiods = pcPolicyperiods;
	}

	public PcPolicyperiodDO addPcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		getPcPolicyperiods().add(pcPolicyperiod);
		pcPolicyperiod.setPcContact(this);

		return pcPolicyperiod;
	}

	public PcPolicyperiodDO removePcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		getPcPolicyperiods().remove(pcPolicyperiod);
		pcPolicyperiod.setPcContact(null);

		return pcPolicyperiod;
	}

	public List<PcReinsuranceagreementDO> getPcReinsuranceagreements() {
		return this.pcReinsuranceagreements;
	}

	public void setPcReinsuranceagreements(List<PcReinsuranceagreementDO> pcReinsuranceagreements) {
		this.pcReinsuranceagreements = pcReinsuranceagreements;
	}

	public PcReinsuranceagreementDO addPcReinsuranceagreement(PcReinsuranceagreementDO pcReinsuranceagreement) {
		getPcReinsuranceagreements().add(pcReinsuranceagreement);
		pcReinsuranceagreement.setPcContact(this);

		return pcReinsuranceagreement;
	}

	public PcReinsuranceagreementDO removePcReinsuranceagreement(PcReinsuranceagreementDO pcReinsuranceagreement) {
		getPcReinsuranceagreements().remove(pcReinsuranceagreement);
		pcReinsuranceagreement.setPcContact(null);

		return pcReinsuranceagreement;
	}

}