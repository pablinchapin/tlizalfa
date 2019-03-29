/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.service;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 *
 * @author pvargas
 */

@Component
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final String supportEmail = "tiendalizgt@gmail.com";
    
    @Autowired
    JavaMailSender javaMailSender;
    
    
    public void sendEmailSimple(String to, String subject, String content){
    
        try{
            final SimpleMailMessage message = new SimpleMailMessage();
            
            message.setSubject(subject);
            message.setFrom(supportEmail);
            message.setTo(to);
            message.setText(content);
                        
            javaMailSender.send(message);
        
        }catch(MailException e){
            
            logger.error(e.getMessage());
        
        }
    
    }
    
    
    
    public void sendMailWithAttachments(String to, String subject, String content, String imageUri){
    
        try{
            final MimeMessage message = javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(supportEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            
            
            FileSystemResource attachment = new FileSystemResource(new File(imageUri));
            helper.addAttachment("Image", attachment);
            
            javaMailSender.send(message);
            
        }catch(MessagingException e){
        
            logger.error(e.getMessage());
        }
    
    }
    
    
    public void sendEmailFromTemplate(String to, String subject, String template, String attachment){
    
        try{
        
            final MimeMessage message = javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(supportEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            
            
            message.setContent(template, "text/html");
            
            javaMailSender.send(message);
            
        }catch(MessagingException e){
        
            logger.error(e.getMessage());
        }
    
    
    }
    
    
    
    
    
    
}
