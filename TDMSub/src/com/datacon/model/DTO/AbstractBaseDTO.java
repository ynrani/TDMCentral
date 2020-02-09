/**
 * 
 */
package com.datacon.model.DTO;

import java.io.Serializable;

/**
 * @author vkrish14
 *
 */
abstract class AbstractBaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private String message;

	private String messageConstant;

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessageConstant(){
		return messageConstant;
	}

	public void setMessageConstant(String messageConstant){
		this.messageConstant = messageConstant;
	}


}
