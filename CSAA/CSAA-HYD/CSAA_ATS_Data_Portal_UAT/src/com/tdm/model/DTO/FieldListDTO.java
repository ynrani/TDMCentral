package com.tdm.model.DTO;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Narasimha
 * @version 1.0
 */

public class FieldListDTO implements Serializable {

	
	/**
	 * To store the field list tables data
	 */
	private static final long serialVersionUID = 3588485825050471279L;

	private Long listId;
	private String listName;
	private String listValue;
	private String valueCode;
	private Boolean isActive;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((listId == null) ? 0 : listId.hashCode());
		result = prime * result
				+ ((listName == null) ? 0 : listName.hashCode());
		result = prime * result
				+ ((listValue == null) ? 0 : listValue.hashCode());
		result = prime * result
				+ ((valueCode == null) ? 0 : valueCode.hashCode());
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
		FieldListDTO other = (FieldListDTO) obj;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (listId == null) {
			if (other.listId != null)
				return false;
		} else if (!listId.equals(other.listId))
			return false;
		if (listName == null) {
			if (other.listName != null)
				return false;
		} else if (!listName.equals(other.listName))
			return false;
		if (listValue == null) {
			if (other.listValue != null)
				return false;
		} else if (!listValue.equals(other.listValue))
			return false;
		if (valueCode == null) {
			if (other.valueCode != null)
				return false;
		} else if (!valueCode.equals(other.valueCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FieldListDTO [" + ", listValue=" + listValue + "]";
	}

}