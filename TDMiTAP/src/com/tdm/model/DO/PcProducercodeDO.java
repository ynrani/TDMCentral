package com.tdm.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PC_PRODUCERCODE database table.
 * 
 */
@Entity
@Table(name="PC_PRODUCERCODE")
@NamedQuery(name="PcProducercodeDO.findAll", query="SELECT p FROM PcProducercodeDO p")
public class PcProducercodeDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String appointmentdate;

	private BigDecimal beanversion;

	private BigDecimal branchid;

	private String code;

	private String codedenorm;

	private String commissionplanid;

	private String createtime;

	private BigDecimal createuserid;

	private String description;

	private String descriptiondenorm;

	private BigDecimal organizationid;

	private BigDecimal preferredunderwriterid;

	private BigDecimal producerstatus;

	private String publicid;

	private BigDecimal retired;

	private String terminationdate;

	private String updatetime;

	private BigDecimal updateuserid;

	//bi-directional many-to-one association to PcEffectivedatedfieldDO
	@OneToMany(mappedBy="pcProducercode")
	private List<PcEffectivedatedfieldDO> pcEffectivedatedfields;

	//bi-directional many-to-one association to PcAddressDO
	@ManyToOne
	@JoinColumn(name="ADDRESSID")
	private PcAddressDO pcAddress;

	public PcProducercodeDO() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppointmentdate() {
		return this.appointmentdate;
	}

	public void setAppointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodedenorm() {
		return this.codedenorm;
	}

	public void setCodedenorm(String codedenorm) {
		this.codedenorm = codedenorm;
	}

	public String getCommissionplanid() {
		return this.commissionplanid;
	}

	public void setCommissionplanid(String commissionplanid) {
		this.commissionplanid = commissionplanid;
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

	public String getDescriptiondenorm() {
		return this.descriptiondenorm;
	}

	public void setDescriptiondenorm(String descriptiondenorm) {
		this.descriptiondenorm = descriptiondenorm;
	}

	public BigDecimal getOrganizationid() {
		return this.organizationid;
	}

	public void setOrganizationid(BigDecimal organizationid) {
		this.organizationid = organizationid;
	}

	public BigDecimal getPreferredunderwriterid() {
		return this.preferredunderwriterid;
	}

	public void setPreferredunderwriterid(BigDecimal preferredunderwriterid) {
		this.preferredunderwriterid = preferredunderwriterid;
	}

	public BigDecimal getProducerstatus() {
		return this.producerstatus;
	}

	public void setProducerstatus(BigDecimal producerstatus) {
		this.producerstatus = producerstatus;
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

	public String getTerminationdate() {
		return this.terminationdate;
	}

	public void setTerminationdate(String terminationdate) {
		this.terminationdate = terminationdate;
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

	public List<PcEffectivedatedfieldDO> getPcEffectivedatedfields() {
		return this.pcEffectivedatedfields;
	}

	public void setPcEffectivedatedfields(List<PcEffectivedatedfieldDO> pcEffectivedatedfields) {
		this.pcEffectivedatedfields = pcEffectivedatedfields;
	}

	public PcEffectivedatedfieldDO addPcEffectivedatedfield(PcEffectivedatedfieldDO pcEffectivedatedfield) {
		getPcEffectivedatedfields().add(pcEffectivedatedfield);
		pcEffectivedatedfield.setPcProducercode(this);

		return pcEffectivedatedfield;
	}

	public PcEffectivedatedfieldDO removePcEffectivedatedfield(PcEffectivedatedfieldDO pcEffectivedatedfield) {
		getPcEffectivedatedfields().remove(pcEffectivedatedfield);
		pcEffectivedatedfield.setPcProducercode(null);

		return pcEffectivedatedfield;
	}

	public PcAddressDO getPcAddress() {
		return this.pcAddress;
	}

	public void setPcAddress(PcAddressDO pcAddress) {
		this.pcAddress = pcAddress;
	}

}