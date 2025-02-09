package com.utils;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ObjectDao;
import com.helper.AppConstants;
import com.model.CommonAppSetting;
import com.model.Credentials;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;

@Component
public class MailUtility {

	@Autowired
	private ObjectDao objectDao;

	public void sendExceptionEmailToDeveloper(Exception exception, String methodName) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					CommonAppSetting sendExceptionEmailSetting = objectDao.getObjectByParam(CommonAppSetting.class,
							"settingName", AppConstants.SEND_EXCEPTION_MAIL_TO_DEVELOPER);
					if (sendExceptionEmailSetting == null
							|| !"true".equalsIgnoreCase(sendExceptionEmailSetting.getSettingValue())) {
						System.out.println("Exception Email Setting is OFF...");
						return;
					}

					Credentials emailCredentials = objectDao.getObjectByParam(Credentials.class, "credentialName",
							AppConstants.CREDENTIAL_NAME_FOR_EXCEPTION_MAIL);
					if (emailCredentials == null || emailCredentials.getUsername() == null
							|| emailCredentials.getPassword() == null) {
						System.out.println("Missing Credentials in DB...");
						return;
					}

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");

					Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(emailCredentials.getUsername(),
									emailCredentials.getPassword());
						}
					});

					// Create the email message
					MimeMessage message = new MimeMessage(session);

					// Determine the sender email address and name
					String fromName = emailCredentials.getExtraInfo1();
					if (fromName == null || fromName.isEmpty()) {
						fromName = "VS BOOK STORE";
					}
					message.setFrom(new InternetAddress(emailCredentials.getUsername(), fromName));

					// Set recipient addresses
					String[] toEmail = AppConstants.DEVELOPER_EMAILS;
					if (toEmail == null || toEmail.length == 0) {
						toEmail = new String[] { "vishalshinde0804@gmail.com", "vishalushinde08042001@gmail.com",
								"vishalshinde9168574933@gmail.com", "sanketjain242000@gmail.com",
								"vishalshinde.rts@gmail.com" };
					}
					InternetAddress[] recipientAddresses = new InternetAddress[toEmail.length];
					for (int i = 0; i < toEmail.length; i++) {
						recipientAddresses[i] = new InternetAddress(toEmail[i]);
					}
					message.setRecipients(Message.RecipientType.TO, recipientAddresses);

					// Set email subject and body
					message.setSubject(methodName);
					message.setText(createBodyFromException(exception, fromName));

					// Send the email
					Transport.send(message);
					System.out.println("Exception Email sent successfully!");

				} catch (Exception e) {
					System.out.println("Exception Email Not Sent Due to Exception: " + e.getMessage());
					e.printStackTrace(); // Print stack trace for debugging
				}
			}
		});

		// Start the thread
		thread.start();
	}

	public String createBodyFromException(Exception e, String methodName) throws Exception {
		InetAddress ip = InetAddress.getLocalHost();
		ip = InetAddress.getLocalHost();
		InetAddress.getLoopbackAddress().getHostAddress();
		InetAddress.getLoopbackAddress().getHostName();
		final StackTraceElement[] elements = e.getStackTrace();
		String stackN = null;
		int depth = 0;
		if (elements.length > 100) {
			depth = 100;
		} else {
			depth = elements.length;
		}
		stackN = Arrays.stream(e.getStackTrace()).limit(depth).map(String::valueOf)
				.collect(Collectors.joining(System.lineSeparator()));
		String mailBody = e.toString() + System.lineSeparator() + stackN;

		String body = "Hello  " + ",Please check the exception occurred in Application.Details are given below: Name : "
				+ "Book Store  " + ",IP Address : " + ip.getHostAddress() + "  Method Name : " + methodName
				+ " Exception: " + mailBody + "" + " ";
		return body;
	}

}