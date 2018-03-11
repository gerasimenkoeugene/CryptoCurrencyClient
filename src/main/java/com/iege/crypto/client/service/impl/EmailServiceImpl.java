package com.iege.crypto.client.service.impl;

import com.iege.crypto.client.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendActivationMessage(String to, String subject, String userName) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("<html><body>" +
                    "Hello! You successfully registered in Crypto Service Viewer <p>" +
                    " To confirm please click <a href=\"http://localhost:8080/activation/" + userName + "\">link</a>" +
                    "</body></html>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }
}
