/*---------------------------------------------------------------------------------------
 * Object Name: EmailNotificationServiceImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.email;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.tesda.model.DTO.AutoEmailDTO;
import com.tesda.model.DTO.ForgotPassword;

/*
 * EmailNotificationServiceImpl provides the email service.
 */
public class EmailNotificationServiceImpl implements EmailNotificationService
{

	@Autowired
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender()
	{
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine()
	{
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine)
	{
		this.velocityEngine = velocityEngine;
	}

	private VelocityEngine velocityEngine;

	@Override
	public void sendEmailNotification(final ForgotPassword forgotPasswordDTO)
	{
		try
		{
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception
				{
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setTo(forgotPasswordDTO.getEmailId());
					// message.setFrom("abc.xxxx@bcbsil.com");
					message.setSubject("Password Recover Request");
					message.setSentDate(new Date());
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("newMessage", forgotPasswordDTO);
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
							"properties/forgotPassword.vm", "UTF-8", model);
					message.setText(text, true);
				}
			};
			this.mailSender.send(preparator);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public void sendEmailNotificationL1L2Support(final AutoEmailDTO autoEmailDTO)
	{
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(autoEmailDTO.getTo());
				message.setFrom(autoEmailDTO.getCc());
				message.setCc(autoEmailDTO.getCc());
				autoEmailDTO.getMsg();
				message.setSubject(autoEmailDTO.getSubject());
				message.setSentDate(new Date());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("autoEmailDTO", autoEmailDTO);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"properties/l1l2Support.vm", "UTF-8", model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
}