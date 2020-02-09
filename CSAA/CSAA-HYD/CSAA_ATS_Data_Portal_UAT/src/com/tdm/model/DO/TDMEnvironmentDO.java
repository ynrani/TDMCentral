package com.tdm.model.DO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CFG_ENVIRONMENT")
@NamedQuery(name = "TDMEnvironmentDO.findAll", query = "SELECT t FROM TDMEnvironmentDO t order by sortOrder")
public class TDMEnvironmentDO extends TDMObject {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENVIRONMENT_ID")
	private String environmentId;
	@Column(name = "DATA_SOURCE")
	private String dataSource;
	@Column(name = "RELEASE_VERSION")
	private String releaseVersion;
	@Column(name = "ENVIRONMENT_NAME")
	private String environmentName;
	@Column(name = "DATABASE_TYPE")
	private String dataBaseType;
	@Column(name = "SERVICE_NAME")
	private String serviceName;
	@Column(name = "INSTANCE_NAME")
	private String instanceName;
	@Column(name = "SCHEMA_NAME")
	private String schemaName;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "CONNECTION_STATUS")
	private String connectionStatus;
	@Column(name = "DATABASE_STATUS")
	private String dataBaseStatus;
	@Column(name = "IS_ACTIVE")
	private char isActive;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	@Column(name = "SORT_ORDER")
	private Integer sortOrder;
	@Column(name = "APPLICATION_URL")
	private String applicationUrl;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	public String getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(String environmetnId) {
		this.environmentId = environmetnId;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public String getDataBaseStatus() {
		return dataBaseStatus;
	}

	public void setDataBaseStatus(String dataBaseStatus) {
		this.dataBaseStatus = dataBaseStatus;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}
