package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

public class TDMEnvironment  implements Serializable{
	private String environmentId;
	private String dataSource;	
	private String releaseVersion;
	private String environmentName;
	private String dataBaseType;
	private String serviceName;
	private String instanceName;
	private String schemaName;
	private String userId;
	private String connectionStatus;
	private String dataBaseStatus;
	private char isActive;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	public String getEnvironmetnId() {
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
	public String getEnvironmentId() {
		return environmentId;
	}

}
