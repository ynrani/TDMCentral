/*---------------------------------------------------------------------------------------
 * Object Name: EmailNotificationService.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.email;

import com.tesda.model.DTO.AutoEmailDTO;
import com.tesda.model.DTO.ForgotPassword;

/**
 *  Transaction class used between the EmailNotificationServiceImpl class and Data base.
 *  Used to send Email to Admin user in case if data is not found during Find test data search.
 */

public interface EmailNotificationService
{
	public void sendEmailNotification(ForgotPassword forgotPasswordDTO);

	public void sendEmailNotificationL1L2Support(final AutoEmailDTO autoEmailDTO);
}
