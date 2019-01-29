package com.sendingEmail.demo.serviceImpl;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendingEmail.demo.model.EmailForm;

@Service
@ConfigurationProperties(prefix = "sendgrid.api")
public class SendGridService {
		
	private String password;
	private String sendFrom;
	public Mail createEmail(EmailForm emailForm) throws IOException {
		Personalization ps = new Personalization();
		List<String> toList = emailForm.getSendToList();
		List<String> ccList = emailForm.getCcList();
		List<String> bccList = emailForm.getBccList();
		for(String to : toList) {
			ps.addTo(new Email(to));
		}
		for(String cc : ccList) {
			ps.addCc(new Email(cc));
		}
		for(String bcc : bccList) {
			ps.addBcc(new Email(bcc));
		}
		
		Mail mail = new Mail();
		
		mail.addPersonalization(ps);
		mail.setFrom(new Email(sendFrom));
		mail.setSubject(emailForm.getSubject());
		mail.addContent(new Content("text/plain", emailForm.getMessage()));
		return mail;		        
	}
	

	public void sendSimpleMessage(EmailForm emailForm) throws Exception {    	
        
		Mail mail = createEmail(emailForm);
        SendGrid sg = new SendGrid(this.password);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        
        
        if(response.getStatusCode() != HttpStatus.SC_OK && response.getStatusCode() != HttpStatus.SC_ACCEPTED) {
        	throw new Exception(String.format("SendGrid Mail Not Sent, Status Code(%d)", response.getStatusCode()));
        }
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getSendFrom() {
		return sendFrom;
	}


	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}
    
    
}