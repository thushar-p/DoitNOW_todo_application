package com.thushar.todoWebApp.helper;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
	this class is responsible for creating the connection to SMTP server and sending the OTP to userEmail with the OTP generated
*/
public class OTPGenerator {

	public int sendOTPToEmail(String userEmail) {

		String to = userEmail;

		final String from = "thusharkulal001@gmail.com";

		String host = "smtp.gmail.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from , "gmail password");
			}
		});
		
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(from));
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("Here is the OTP");

			Random random = new Random();
			int randomNumber = random.nextInt(999999);
			
			message.setText("Your OTP is "+randomNumber+"\n"
					+ "Provide this OTP to reset your password\n"
					+ "Do not share this OTP with anyone");
			
			Transport.send(message);
			
			
			System.out.println("Email Message Sent Successfully");
			
			return randomNumber;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
