package com.sendingEmail.demo.service;

public interface EmailService {
    void sendText(String from, String to, String subject, String body);   
    void sendHTML(String from, String to, String subject, String body);
}
