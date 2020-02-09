package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PC_REINSURANCEAGREEMENT database table.
 * 
 */
@Entity
@Table(name="PC_REINSURANCEAGREEMENT")
@NamedQuery(name="PcReinsuranceagreementDO.findAll", query="SELECT p FROM PcReinsuranceagreementDO p")
public class PcReinsuranceagreementDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String agreementnumber;

	private BigDecimal amountofcoverage;

	@Column(name="AMOUNTOFCOVERAGE_CUR")
	private BigDecimal amountofcoverageCur;

	private BigDecimal applytogrossretentiononly;

	private BigDecimal attachmentbasis;

	private BigDecimal attachmentindexed;

	private BigDecimal attachmentpoint;

	@Column(name="ATTACHMENTPOINT_CUR")
	private BigDecimal attachmentpointCur;

	private BigDecimal beanversion;

	private BigDecimal calculatecededpremium;

	private BigDecimal cededpremium;

	@Column(name="CEDEDPREMIUM_CUR")
	private BigDecimal cededpremiumCur;

	private BigDecimal cededshare;

	private BigDecimal cedingrate;

	private BigDecimal cedingrateadjusted;

	@Lob
	private String comments;

	private BigDecimal commission;

	private BigDecimal commissionadjusted;

	private BigDecimal counttowardtotallimit;

	private BigDecimal coveragelimit;

	@Column(name="COVERAGELIMIT_CUR")
	private BigDecimal coveragelimitCur;

	private String createtime;

	private BigDecimal createuserid;

	private BigDecimal currency;

	private BigDecimal depositpaymentschedule;

	private String effectivedate;

	private String expirationdate;

	private BigDecimal flatpremiumadjusted;

	private BigDecimal gnpsubtotal;

	private BigDecimal limitindexed;

	private BigDecimal markup;

	private BigDecimal maxretention;

	@Column(name="MAXRETENTION_CUR")
	private BigDecimal maxretentionCur;

	private BigDecimal mindepositpremium;

	@Column(name="MINDEPOSITPREMIUM_CUR")
	private BigDecimal mindepositpremiumCur;

	private String name;

	private BigDecimal notificationthreshold;

	@Column(name="NOTIFICATIONTHRESHOLD_CUR")
	private BigDecimal notificationthresholdCur;

	private BigDecimal payablebasis;

	private String publicid;

	private BigDecimal retired;

	private BigDecimal status;

	private BigDecimal subtype;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcContactDO
	@ManyToOne
	@JoinColumn(name="BROKER")
	private PcContactDO pcContact;

	public PcReinsuranceagreementDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAgreementnumber() {
		return this.agreementnumber;
	}

	public void setAgreementnumber(String agreementnumber) {
		this.agreementnumber = agreementnumber;
	}

	public BigDecimal getAmountofcoverage() {
		return this.amountofcoverage;
	}

	public void setAmountofcoverage(BigDecimal amountofcoverage) {
		this.amountofcoverage = amountofcoverage;
	}

	public BigDecimal getAmountofcoverageCur() {
		return this.amountofcoverageCur;
	}

	public void setAmountofcoverageCur(BigDecimal amountofcoverageCur) {
		this.amountofcoverageCur = amountofcoverageCur;
	}

	public BigDecimal getApplytogrossretentiononly() {
		return this.applytogrossretentiononly;
	}

	public void setApplytogrossretentiononly(BigDecimal applytogrossretentiononly) {
		this.applytogrossretentiononly = applytogrossretentiononly;
	}

	public BigDecimal getAttachmentbasis() {
		return this.attachmentbasis;
	}

	public void setAttachmentbasis(BigDecimal attachmentbasis) {
		this.attachmentbasis = attachmentbasis;
	}

	public BigDecimal getAttachmentindexed() {
		return this.attachmentindexed;
	}

	public void setAttachmentindexed(BigDecimal attachmentindexed) {
		this.attachmentindexed = attachmentindexed;
	}

	public BigDecimal getAttachmentpoint() {
		return this.attachmentpoint;
	}

	public void setAttachmentpoint(BigDecimal attachmentpoint) {
		this.attachmentpoint = attachmentpoint;
	}

	public BigDecimal getAttachmentpointCur() {
		return this.attachmentpointCur;
	}

	public void setAttachmentpointCur(BigDecimal attachmentpointCur) {
		this.attachmentpointCur = attachmentpointCur;
	}

	public BigDecimal getBeanversion() {
		return this.beanversion;
	}

	public void setBeanversion(BigDecimal beanversion) {
		this.beanversion = beanversion;
	}

	public BigDecimal getCalculatecededpremium() {
		return this.calculatecededpremium;
	}

	public void setCalculatecededpremium(BigDecimal calculatecededpremium) {
		this.calculatecededpremium = calculatecededpremium;
	}

	public BigDecimal getCededpremium() {
		return this.cededpremium;
	}

	public void setCededpremium(BigDecimal cededpremium) {
		this.cededpremium = cededpremium;
	}

	public BigDecimal getCededpremiumCur() {
		return this.cededpremiumCur;
	}

	public void setCededpremiumCur(BigDecimal cededpremiumCur) {
		this.cededpremiumCur = cededpremiumCur;
	}

	public BigDecimal getCededshare() {
		return this.cededshare;
	}

	public void setCededshare(BigDecimal cededshare) {
		this.cededshare = cededshare;
	}

	public BigDecimal getCedingrate() {
		return this.cedingrate;
	}

	public void setCedingrate(BigDecimal cedingrate) {
		this.cedingrate = cedingrate;
	}

	public BigDecimal getCedingrateadjusted() {
		return this.cedingrateadjusted;
	}

	public void setCedingrateadjusted(BigDecimal cedingrateadjusted) {
		this.cedingrateadjusted = cedingrateadjusted;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getCommission() {
		return this.commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getCommissionadjusted() {
		return this.commissionadjusted;
	}

	public void setCommissionadjusted(BigDecimal commissionadjusted) {
		this.commissionadjusted = commissionadjusted;
	}

	public BigDecimal getCounttowardtotallimit() {
		return this.counttowardtotallimit;
	}

	public void setCounttowardtotallimit(BigDecimal counttowardtotallimit) {
		this.counttowardtotallimit = counttowardtotallimit;
	}

	public BigDecimal getCoveragelimit() {
		return this.coveragelimit;
	}

	public void setCoveragelimit(BigDecimal coveragelimit) {
		this.coveragelimit = coveragelimit;
	}

	public BigDecimal getCoveragelimitCur() {
		return this.coveragelimitCur;
	}

	public void setCoveragelimitCur(BigDecimal coveragelimitCur) {
		this.coveragelimitCur = coveragelimitCur;
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

	public BigDecimal getCurrency() {
		return this.currency;
	}

	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
	}

	public BigDecimal getDepositpaymentschedule() {
		return this.depositpaymentschedule;
	}

	public void setDepositpaymentschedule(BigDecimal depositpaymentschedule) {
		this.depositpaymentschedule = depositpaymentschedule;
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

	public BigDecimal getFlatpremiumadjusted() {
		return this.flatpremiumadjusted;
	}

	public void setFlatpremiumadjusted(BigDecimal flatpremiumadjusted) {
		this.flatpremiumadjusted = flatpremiumadjusted;
	}

	public BigDecimal getGnpsubtotal() {
		return this.gnpsubtotal;
	}

	public void setGnpsubtotal(BigDecimal gnpsubtotal) {
		this.gnpsubtotal = gnpsubtotal;
	}

	public BigDecimal getLimitindexed() {
		return this.limitindexed;
	}

	public void setLimitindexed(BigDecimal limitindexed) {
		this.limitindexed = limitindexed;
	}

	public BigDecimal getMarkup() {
		return this.markup;
	}

	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}

	public BigDecimal getMaxretention() {
		return this.maxretention;
	}

	public void setMaxretention(BigDecimal maxretention) {
		this.maxretention = maxretention;
	}

	public BigDecimal getMaxretentionCur() {
		return this.maxretentionCur;
	}

	public void setMaxretentionCur(BigDecimal maxretentionCur) {
		this.maxretentionCur = maxretentionCur;
	}

	public BigDecimal getMindepositpremium() {
		return this.mindepositpremium;
	}

	public void setMindepositpremium(BigDecimal mindepositpremium) {
		this.mindepositpremium = mindepositpremium;
	}

	public BigDecimal getMindepositpremiumCur() {
		return this.mindepositpremiumCur;
	}

	public void setMindepositpremiumCur(BigDecimal mindepositpremiumCur) {
		this.mindepositpremiumCur = mindepositpremiumCur;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNotificationthreshold() {
		return this.notificationthreshold;
	}

	public void setNotificationthreshold(BigDecimal notificationthreshold) {
		this.notificationthreshold = notificationthreshold;
	}

	public BigDecimal getNotificationthresholdCur() {
		return this.notificationthresholdCur;
	}

	public void setNotificationthresholdCur(BigDecimal notificationthresholdCur) {
		this.notificationthresholdCur = notificationthresholdCur;
	}

	public BigDecimal getPayablebasis() {
		return this.payablebasis;
	}

	public void setPayablebasis(BigDecimal payablebasis) {
		this.payablebasis = payablebasis;
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

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
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

	public PcContactDO getPcContact() {
		return this.pcContact;
	}

	public void setPcContact(PcContactDO pcContact) {
		this.pcContact = pcContact;
	}

}