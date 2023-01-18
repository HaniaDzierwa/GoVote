package com.aleklew.ballot.modules.general.services;

import com.aleklew.ballot.modules.general.interfaces.ISharedMailingService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class SharedMailingService implements ISharedMailingService {
    private final JavaMailSender javaMailSender;
    public SharedMailingService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void sendActivateAccountMail(String targetEmailAddress, String activationCode) {
      try {
          MimeMessage mimeMessage = javaMailSender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

          helper.setTo(targetEmailAddress);
          helper.setSubject("Aktywacja Konta");

          StringBuilder messageContentBuilder = new StringBuilder();
          messageContentBuilder.append("<h3>Witaj użytkowniku!</h3>");
          messageContentBuilder.append(String.format("<a href=\"%s%s\">Aktywuj konto</a>", "http://localhost:8080/api/v1/public/activate?activationCode=", activationCode));

          helper.setText(messageContentBuilder.toString(), true); // Use this or above line.

          javaMailSender.send(mimeMessage);
      } catch (Exception e) {}
    }
}
