package com.iege.crypto.client.service;

public interface EmailService {
    void sendActivationMessage(String to, String subject, String userName);
}
