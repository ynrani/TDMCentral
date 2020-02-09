package com.tdm.model.DO;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PC_POLICY database table.
 * 
 */
@Entity
@Table(name="PC_POLICY")
@NamedQuery(name="PcPolicyDO.findAll", query="SELECT p FROM PcPolicyDO p")
public class PcPolicyDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String archivedate;

	private BigDecimal archivefailuredetailsid;

	private BigDecimal archivefailureid;

	private BigDecimal archivepartition;

	private BigDecimal archiveschemainfo;

	private BigDecimal archivestate;

	private BigDecimal beanversion;

	private String createtime;

	private BigDecimal createuserid;

	private BigDecimal donotarchive;

	private BigDecimal donotpurge;

	private BigDecimal excludedfromarchive;

	private String excludereason;

	private String issuedate;

	private BigDecimal losshistorytype;

	@Column(name="NONOWNER_CGIC")
	private BigDecimal nonownerCgic;

	private BigDecimal numpriorlosses;

	@Basic(optional = false)
	@Column(name = "LastTouched", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date originaleffectivedate;

	private BigDecimal packagerisk;

	@Column(name="POLICYSTATE_CGIC")
	private BigDecimal policystateCgic;

	@Column(name="PREMIUMSTATUS_CGIC")
	private BigDecimal premiumstatusCgic;

	private BigDecimal primarylanguage;

	private BigDecimal primarylocale;

	private BigDecimal priorpremiums;

	@Column(name="PRIORPREMIUMS_CUR")
	private BigDecimal priorpremiumsCur;

	private BigDecimal priortotalincurred;

	@Column(name="PRIORTOTALINCURRED_CUR")
	private BigDecimal priortotalincurredCur;

	private BigDecimal producercodeofserviceid;

	private String productcode;

	private String publicid;

	private BigDecimal retired;

	@Column(name="RRE_CGIC")
	private String rreCgic;

	@Column(name="SITEID_CGIC")
	private String siteidCgic;

	@Column(name="SR22_CGIC")
	private BigDecimal sr22Cgic;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcAccountDO
	@ManyToOne
	@JoinColumn(name="MOVEDPOLICYSOURCEACCOUNTID")
	private PcAccountDO pcAccount1;

	//bi-directional many-to-one association to PcAccountDO
	@ManyToOne
	@JoinColumn(name="ACCOUNTID")
	private PcAccountDO pcAccount2;

	//bi-directional many-to-one association to PcPolicyperiodDO
	@OneToMany(mappedBy="pcPolicy")
	private List<PcPolicyperiodDO> pcPolicyperiods;

	//bi-directional many-to-one association to PcPolicytermDO
	@OneToMany(mappedBy="pcPolicy")
	private List<PcPolicytermDO> pcPolicyterms;

	//bi-directional many-to-one association to PcPriorpolicyDO
	@OneToMany(mappedBy="pcPolicy")
	private List<PcPriorpolicyDO> pcPriorpolicies;

	public PcPolicyDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArchivedate() {
		return this.archivedate;
	}

	public void setArchivedate(String archivedate) {
		this.archivedate = archivedate;
	}

	public BigDecimal getArchivefailuredetailsid() {
		return this.archivefailuredetailsid;
	}

	public void setArchivefailuredetailsid(BigDecimal archivefailuredetailsid) {
		this.archivefailuredetailsid = archivefailuredetailsid;
	}

	public BigDecimal getArchivefailureid() {
		return this.archivefailureid;
	}

	public void setArchivefailureid(BigDecimal archivefailureid) {
		this.archivefailureid = archivefailureid;
	}

	public BigDecimal getArchivepartition() {
		return this.archivepartition;
	}

	public void setArchivepartition(BigDecimal archivepartition) {
		this.archivepartition = archivepartition;
	}

	public BigDecimal getArchiveschemainfo() {
		return this.archiveschemainfo;
	}

	public void setArchiveschemainfo(BigDecimal archiveschemainfo) {
		this.archiveschemainfo = archiveschemainfo;
	}

	public BigDecimal getArchivestate() {
		return this.archivestate;
	}

	public void setArchivestate(BigDecimal archivestate) {
		this.archivestate = archivestate;
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

	public BigDecimal getDonotarchive() {
		return this.donotarchive;
	}

	public void setDonotarchive(BigDecimal donotarchive) {
		this.donotarchive = donotarchive;
	}

	public BigDecimal getDonotpurge() {
		return this.donotpurge;
	}

	public void setDonotpurge(BigDecimal donotpurge) {
		this.donotpurge = donotpurge;
	}

	public BigDecimal getExcludedfromarchive() {
		return this.excludedfromarchive;
	}

	public void setExcludedfromarchive(BigDecimal excludedfromarchive) {
		this.excludedfromarchive = excludedfromarchive;
	}

	public String getExcludereason() {
		return this.excludereason;
	}

	public void setExcludereason(String excludereason) {
		this.excludereason = excludereason;
	}

	public String getIssuedate() {
		return this.issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public BigDecimal getLosshistorytype() {
		return this.losshistorytype;
	}

	public void setLosshistorytype(BigDecimal losshistorytype) {
		this.losshistorytype = losshistorytype;
	}

	public BigDecimal getNonownerCgic() {
		return this.nonownerCgic;
	}

	public void setNonownerCgic(BigDecimal nonownerCgic) {
		this.nonownerCgic = nonownerCgic;
	}

	public BigDecimal getNumpriorlosses() {
		return this.numpriorlosses;
	}

	public void setNumpriorlosses(BigDecimal numpriorlosses) {
		this.numpriorlosses = numpriorlosses;
	}

	

	public BigDecimal getPackagerisk() {
		return this.packagerisk;
	}

	public void setPackagerisk(BigDecimal packagerisk) {
		this.packagerisk = packagerisk;
	}

	public BigDecimal getPolicystateCgic() {
		return this.policystateCgic;
	}

	public void setPolicystateCgic(BigDecimal policystateCgic) {
		this.policystateCgic = policystateCgic;
	}

	public BigDecimal getPremiumstatusCgic() {
		return this.premiumstatusCgic;
	}

	public void setPremiumstatusCgic(BigDecimal premiumstatusCgic) {
		this.premiumstatusCgic = premiumstatusCgic;
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

	public BigDecimal getPriorpremiums() {
		return this.priorpremiums;
	}

	public void setPriorpremiums(BigDecimal priorpremiums) {
		this.priorpremiums = priorpremiums;
	}

	public BigDecimal getPriorpremiumsCur() {
		return this.priorpremiumsCur;
	}

	public void setPriorpremiumsCur(BigDecimal priorpremiumsCur) {
		this.priorpremiumsCur = priorpremiumsCur;
	}

	public BigDecimal getPriortotalincurred() {
		return this.priortotalincurred;
	}

	public void setPriortotalincurred(BigDecimal priortotalincurred) {
		this.priortotalincurred = priortotalincurred;
	}

	public BigDecimal getPriortotalincurredCur() {
		return this.priortotalincurredCur;
	}

	public void setPriortotalincurredCur(BigDecimal priortotalincurredCur) {
		this.priortotalincurredCur = priortotalincurredCur;
	}

	public BigDecimal getProducercodeofserviceid() {
		return this.producercodeofserviceid;
	}

	public void setProducercodeofserviceid(BigDecimal producercodeofserviceid) {
		this.producercodeofserviceid = producercodeofserviceid;
	}

	public String getProductcode() {
		return this.productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
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

	public String getRreCgic() {
		return this.rreCgic;
	}

	public void setRreCgic(String rreCgic) {
		this.rreCgic = rreCgic;
	}

	public String getSiteidCgic() {
		return this.siteidCgic;
	}

	public void setSiteidCgic(String siteidCgic) {
		this.siteidCgic = siteidCgic;
	}

	public BigDecimal getSr22Cgic() {
		return this.sr22Cgic;
	}

	public void setSr22Cgic(BigDecimal sr22Cgic) {
		this.sr22Cgic = sr22Cgic;
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

	public PcAccountDO getPcAccount1() {
		return this.pcAccount1;
	}

	public void setPcAccount1(PcAccountDO pcAccount1) {
		this.pcAccount1 = pcAccount1;
	}

	public PcAccountDO getPcAccount2() {
		return this.pcAccount2;
	}

	public void setPcAccount2(PcAccountDO pcAccount2) {
		this.pcAccount2 = pcAccount2;
	}

	public List<PcPolicyperiodDO> getPcPolicyperiods() {
		return this.pcPolicyperiods;
	}

	public void setPcPolicyperiods(List<PcPolicyperiodDO> pcPolicyperiods) {
		this.pcPolicyperiods = pcPolicyperiods;
	}

	public PcPolicyperiodDO addPcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		getPcPolicyperiods().add(pcPolicyperiod);
		pcPolicyperiod.setPcPolicy(this);

		return pcPolicyperiod;
	}

	public PcPolicyperiodDO removePcPolicyperiod(PcPolicyperiodDO pcPolicyperiod) {
		getPcPolicyperiods().remove(pcPolicyperiod);
		pcPolicyperiod.setPcPolicy(null);

		return pcPolicyperiod;
	}

	public List<PcPolicytermDO> getPcPolicyterms() {
		return this.pcPolicyterms;
	}

	public void setPcPolicyterms(List<PcPolicytermDO> pcPolicyterms) {
		this.pcPolicyterms = pcPolicyterms;
	}

	public PcPolicytermDO addPcPolicyterm(PcPolicytermDO pcPolicyterm) {
		getPcPolicyterms().add(pcPolicyterm);
		pcPolicyterm.setPcPolicy(this);

		return pcPolicyterm;
	}

	public PcPolicytermDO removePcPolicyterm(PcPolicytermDO pcPolicyterm) {
		getPcPolicyterms().remove(pcPolicyterm);
		pcPolicyterm.setPcPolicy(null);

		return pcPolicyterm;
	}

	public List<PcPriorpolicyDO> getPcPriorpolicies() {
		return this.pcPriorpolicies;
	}

	public void setPcPriorpolicies(List<PcPriorpolicyDO> pcPriorpolicies) {
		this.pcPriorpolicies = pcPriorpolicies;
	}

	public PcPriorpolicyDO addPcPriorpolicy(PcPriorpolicyDO pcPriorpolicy) {
		getPcPriorpolicies().add(pcPriorpolicy);
		pcPriorpolicy.setPcPolicy(this);

		return pcPriorpolicy;
	}

	public PcPriorpolicyDO removePcPriorpolicy(PcPriorpolicyDO pcPriorpolicy) {
		getPcPriorpolicies().remove(pcPriorpolicy);
		pcPriorpolicy.setPcPolicy(null);

		return pcPriorpolicy;
	}

	public Date getOriginaleffectivedate(){
		return originaleffectivedate;
	}

	public void setOriginaleffectivedate(Date originaleffectivedate){
		this.originaleffectivedate = originaleffectivedate;
	}

}