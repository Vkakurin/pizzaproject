package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//для работы Bean нужно добавить зависимомти в pom and application.properties
@Service
public class MailSender {

    private final JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    public void send (String emailTO,String subject,String message){
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setFrom(username);
       mailMessage.setTo(emailTO);
       mailMessage.setSubject(subject);
       mailMessage.setText(message);

       mailSender.send(mailMessage);

   }


}
