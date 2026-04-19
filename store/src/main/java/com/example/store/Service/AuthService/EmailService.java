package com.example.store.Service.AuthService;


import com.example.store.Exception.SendEmailException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;



import java.util.concurrent.TimeUnit;

@Service
public class EmailService {



    private  static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateCode() {
        StringBuilder code = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }

    @Autowired
    private RedisTemplate<String, String> operations;

    public void saveCode(String email, String code) {
        String key = "verification_code:" + email;
        operations.opsForValue().set(key, code, 5, TimeUnit.MINUTES);

    }

    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String to, String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ritagemakrem@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
        } catch (Exception e) {
            throw new SendEmailException("Failed to send email to " + to +":" +e);
        }
    }

    public String getCode(String email) {
        String key = "verification_code:" + email;
        return operations.opsForValue().get(key);
    }

    public void deleteCode(String email) {
        String key = "verification_code:" + email;
        operations.delete(key);
    }
}
