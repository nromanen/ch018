package com.ch018.library.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ch018.library.util.IConstants;

@Service("mailService")
public class MailServiceImpl implements MailService {

	private static Logger log = LogManager.getLogger(MailServiceImpl.class);
	
	@Autowired
	private MailSender mailSender;
	
	private ExecutorService executorService = Executors
			.newFixedThreadPool(IConstants.MAX_ALLOWED_THREADS);
	
	@Override
	public void sendMail(String to, String subject, String body) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(IConstants.MAIL_FROM);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		try {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					mailSender.send(message);
				}
			});
		} catch (Exception e) {
			log.error(e);
		}
	}
}
