package com.tdm.email;

import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.DCAutoEmailDTO;
import com.tdm.model.DTO.ForgotPassword;

public interface EmailNotificationService {
	public void sendEmailNotification(ForgotPassword forgotPasswordDTO) throws ServiceException;
	public void sendEmail(final DCAutoEmailDTO pASServiceAutoEmailDTO)
			throws ServiceException;
}
