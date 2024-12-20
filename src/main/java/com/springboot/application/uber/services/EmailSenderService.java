package com.springboot.application.uber.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {

    public void sendEmail(String toEmail, String subject, String body);

    public void sendEmail(String[] toEmail, String subject, String body);
}
