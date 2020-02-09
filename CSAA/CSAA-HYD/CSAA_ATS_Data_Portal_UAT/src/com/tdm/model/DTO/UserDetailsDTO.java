package com.tdm.model.DTO;

import java.io.Serializable;

/***
 * To send data from UI to server side
 * @author mshaik6
 *
 */

public  class UserDetailsDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String userEmail;
	private String consumerGroup;
	private String manager;
	private String accessReason;
	/*private String isActive;
	private Date createdDate;*/
	
	/*public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}*/

public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public String getConsumerGroup() {
	return consumerGroup;
}
public void setConsumerGroup(String consumerGroup) {
	this.consumerGroup = consumerGroup;
}
public String getManager() {
	return manager;
}
public void setManager(String manager) {
	this.manager = manager;
}
public String getAccessReason() {
	return accessReason;
}
public void setAccessReason(String accessReason) {
	this.accessReason = accessReason;
}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessReason == null) ? 0 : accessReason.hashCode());
		result = prime * result
				+ ((consumerGroup == null) ? 0 : consumerGroup.hashCode());
		/*result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());*/
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsDTO other = (UserDetailsDTO) obj;
		if (accessReason == null) {
			if (other.accessReason != null)
				return false;
		} else if (!accessReason.equals(other.accessReason))
			return false;
		if (consumerGroup == null) {
			if (other.consumerGroup != null)
				return false;
		} else if (!consumerGroup.equals(other.consumerGroup))
			return false;
		/*if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;*/
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserDetailsDTO [userId=" + userId + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", consumerGroup="
				+ consumerGroup + ", manager=" + manager + ", accessReason="
				+ accessReason /*+ ", isActive=" + isActive + ", createdDate="
				+ createdDate + "]"*/;
	
	}
}
	
	

