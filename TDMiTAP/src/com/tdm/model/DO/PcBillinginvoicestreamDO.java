package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_BILLINGINVOICESTREAM database table.
 * 
 */
@Entity
@Table(name="PC_BILLINGINVOICESTREAM")
@NamedQuery(name="PcBillinginvoicestreamDO.findAll", query="SELECT p FROM PcBillinginvoicestreamDO p")
public class PcBillinginvoicestreamDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal archivepartition;

	private BigDecimal beanversion;

	private String createtime;

	private BigDecimal createuserid;

	@Column(name="\"DAYOFWEEK\"")
	private BigDecimal dayofweek;

	@Lob
	private String description;

	private BigDecimal duedatebilling;

	private String firstanchordate;

	private BigDecimal firstdayofmonth;

	@Column(name="\"INTERVAL\"")
	private BigDecimal interval;

	private String paymentinstrumentid;

	private BigDecimal paymentmethod;

	private String publicid;

	private BigDecimal retired;

	private String secondanchordate;

	private BigDecimal seconddayofmonth;

	private BigDecimal selected;

	private String unappliedfunddescription;

	private String unappliedfundid;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcPolicyperiodDO
	@ManyToOne
	@JoinColumn(name="POLICYPERIOD")
	private PcPolicyperiodDO pcPolicyperiod;

	public PcBillinginvoicestreamDO() {
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

	public BigDecimal getDayofweek() {
		return this.dayofweek;
	}

	public void setDayofweek(BigDecimal dayofweek) {
		this.dayofweek = dayofweek;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDuedatebilling() {
		return this.duedatebilling;
	}

	public void setDuedatebilling(BigDecimal duedatebilling) {
		this.duedatebilling = duedatebilling;
	}

	public String getFirstanchordate() {
		return this.firstanchordate;
	}

	public void setFirstanchordate(String firstanchordate) {
		this.firstanchordate = firstanchordate;
	}

	public BigDecimal getFirstdayofmonth() {
		return this.firstdayofmonth;
	}

	public void setFirstdayofmonth(BigDecimal firstdayofmonth) {
		this.firstdayofmonth = firstdayofmonth;
	}

	public BigDecimal getInterval() {
		return this.interval;
	}

	public void setInterval(BigDecimal interval) {
		this.interval = interval;
	}

	public String getPaymentinstrumentid() {
		return this.paymentinstrumentid;
	}

	public void setPaymentinstrumentid(String paymentinstrumentid) {
		this.paymentinstrumentid = paymentinstrumentid;
	}

	public BigDecimal getPaymentmethod() {
		return this.paymentmethod;
	}

	public void setPaymentmethod(BigDecimal paymentmethod) {
		this.paymentmethod = paymentmethod;
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

	public String getSecondanchordate() {
		return this.secondanchordate;
	}

	public void setSecondanchordate(String secondanchordate) {
		this.secondanchordate = secondanchordate;
	}

	public BigDecimal getSeconddayofmonth() {
		return this.seconddayofmonth;
	}

	public void setSeconddayofmonth(BigDecimal seconddayofmonth) {
		this.seconddayofmonth = seconddayofmonth;
	}

	public BigDecimal getSelected() {
		return this.selected;
	}

	public void setSelected(BigDecimal selected) {
		this.selected = selected;
	}

	public String getUnappliedfunddescription() {
		return this.unappliedfunddescription;
	}

	public void setUnappliedfunddescription(String unappliedfunddescription) {
		this.unappliedfunddescription = unappliedfunddescription;
	}

	public String getUnappliedfundid() {
		return this.unappliedfundid;
	}

	public void setUnappliedfundid(String unappliedfundid) {
		this.unappliedfundid = unappliedfundid;
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

	public PcPolicyperiodDO getPcPolicyperiod() {
		return this.pcPolicyperiod;
	}

	public void setPcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		this.pcPolicyperiod = pcPolicyperiod;
	}

}