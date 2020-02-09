package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PC_ADDRESS database table.
 * 
 */
@Entity
@Table(name="PC_ADDRESS")
@NamedQuery(name="PcAddressDO.findAll", query="SELECT p FROM PcAddressDO p")
public class PcAddressDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal account;

	private BigDecimal active;

	private String addressbookuid;

	private String addressline1;

	private String addressline1kanji;

	private String addressline2;

	private String addressline2kanji;

	private String addressline3;

	private BigDecimal addresstype;

	private BigDecimal batchgeocode;

	private BigDecimal beanversion;

	private BigDecimal cedex;

	private String cedexbureau;

	private String city;

	private String citydenorm;

	private String citykanji;

	private BigDecimal country;

	private String county;

	private String createtime;

	private BigDecimal createuserid;

	private String description;

	private BigDecimal employeecount;

	private BigDecimal geocodestatus;

	private String lastupdatetime;

	private BigDecimal linkedaddress;

	private BigDecimal loadcommandid;

	private String locationcode;

	private String locationname;

	private BigDecimal locationnum;

	private BigDecimal nonspecific;

	private String phone;

	private BigDecimal phonecountry;

	private String phoneextension;

	private String postalcode;

	private String postalcodedenorm;

	private String publicid;

	private BigDecimal referenced;

	private BigDecimal retired;

	@Lob
	private byte[] spatialpoint;

	@Column(name="\"STATE\"")
	private BigDecimal state;

	private BigDecimal subtype;

	private String temporarylastupdatetime;

	private String updatetime;

	private BigDecimal updateuserid;

	private String validuntil;

	//bi-directional many-to-one association to PcProducercodeDO
	@OneToMany(mappedBy="pcAddress")
	private List<PcProducercodeDO> pcProducercodes;

	public PcAddressDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAccount() {
		return this.account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public String getAddressbookuid() {
		return this.addressbookuid;
	}

	public void setAddressbookuid(String addressbookuid) {
		this.addressbookuid = addressbookuid;
	}

	public String getAddressline1() {
		return this.addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline1kanji() {
		return this.addressline1kanji;
	}

	public void setAddressline1kanji(String addressline1kanji) {
		this.addressline1kanji = addressline1kanji;
	}

	public String getAddressline2() {
		return this.addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline2kanji() {
		return this.addressline2kanji;
	}

	public void setAddressline2kanji(String addressline2kanji) {
		this.addressline2kanji = addressline2kanji;
	}

	public String getAddressline3() {
		return this.addressline3;
	}

	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}

	public BigDecimal getAddresstype() {
		return this.addresstype;
	}

	public void setAddresstype(BigDecimal addresstype) {
		this.addresstype = addresstype;
	}

	public BigDecimal getBatchgeocode() {
		return this.batchgeocode;
	}

	public void setBatchgeocode(BigDecimal batchgeocode) {
		this.batchgeocode = batchgeocode;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public BigDecimal getCedex() {
		return this.cedex;
	}

	public void setCedex(BigDecimal cedex) {
		this.cedex = cedex;
	}

	public String getCedexbureau() {
		return this.cedexbureau;
	}

	public void setCedexbureau(String cedexbureau) {
		this.cedexbureau = cedexbureau;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCitydenorm() {
		return this.citydenorm;
	}

	public void setCitydenorm(String citydenorm) {
		this.citydenorm = citydenorm;
	}

	public String getCitykanji() {
		return this.citykanji;
	}

	public void setCitykanji(String citykanji) {
		this.citykanji = citykanji;
	}

	public BigDecimal getCountry() {
		return this.country;
	}

	public void setCountry(BigDecimal country) {
		this.country = country;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getEmployeecount() {
		return this.employeecount;
	}

	public void setEmployeecount(BigDecimal employeecount) {
		this.employeecount = employeecount;
	}

	public BigDecimal getGeocodestatus() {
		return this.geocodestatus;
	}

	public void setGeocodestatus(BigDecimal geocodestatus) {
		this.geocodestatus = geocodestatus;
	}

	public String getLastupdatetime() {
		return this.lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public BigDecimal getLinkedaddress() {
		return this.linkedaddress;
	}

	public void setLinkedaddress(BigDecimal linkedaddress) {
		this.linkedaddress = linkedaddress;
	}

	public BigDecimal getLoadcommandid() {
		return this.loadcommandid;
	}

	public void setLoadcommandid(BigDecimal loadcommandid) {
		this.loadcommandid = loadcommandid;
	}

	public String getLocationcode() {
		return this.locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public String getLocationname() {
		return this.locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public BigDecimal getLocationnum() {
		return this.locationnum;
	}

	public void setLocationnum(BigDecimal locationnum) {
		this.locationnum = locationnum;
	}

	public BigDecimal getNonspecific() {
		return this.nonspecific;
	}

	public void setNonspecific(BigDecimal nonspecific) {
		this.nonspecific = nonspecific;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getPhonecountry() {
		return this.phonecountry;
	}

	public void setPhonecountry(BigDecimal phonecountry) {
		this.phonecountry = phonecountry;
	}

	public String getPhoneextension() {
		return this.phoneextension;
	}

	public void setPhoneextension(String phoneextension) {
		this.phoneextension = phoneextension;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPostalcodedenorm() {
		return this.postalcodedenorm;
	}

	public void setPostalcodedenorm(String postalcodedenorm) {
		this.postalcodedenorm = postalcodedenorm;
	}

	public String getPublicid() {
		return this.publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	public BigDecimal getReferenced() {
		return this.referenced;
	}

	public void setReferenced(BigDecimal referenced) {
		this.referenced = referenced;
	}

	public BigDecimal getRetired() {
		return this.retired;
	}

	public void setRetired(BigDecimal retired) {
		this.retired = retired;
	}

	public byte[] getSpatialpoint() {
		return this.spatialpoint;
	}

	public void setSpatialpoint(byte[] spatialpoint) {
		this.spatialpoint = spatialpoint;
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

	public String getValiduntil() {
		return this.validuntil;
	}

	public void setValiduntil(String validuntil) {
		this.validuntil = validuntil;
	}

	public List<PcProducercodeDO> getPcProducercodes() {
		return this.pcProducercodes;
	}

	public void setPcProducercodes(List<PcProducercodeDO> pcProducercodes) {
		this.pcProducercodes = pcProducercodes;
	}

	public PcProducercodeDO addPcProducercode(PcProducercodeDO pcProducercode) {
		getPcProducercodes().add(pcProducercode);
		pcProducercode.setPcAddress(this);

		return pcProducercode;
	}

	public PcProducercodeDO removePcProducercode(PcProducercodeDO pcProducercode) {
		getPcProducercodes().remove(pcProducercode);
		pcProducercode.setPcAddress(null);

		return pcProducercode;
	}

}