package com.silcom.manager.domain.service;

import com.silcom.manager.domain.model.EmailMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(EmailMessage message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(message.getSender());
        simpleMailMessage.setTo(message.getRecipients().toArray(new String[message.getRecipients().size()]));
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getBody());

        javaMailSender.send(simpleMailMessage);
    }
}
