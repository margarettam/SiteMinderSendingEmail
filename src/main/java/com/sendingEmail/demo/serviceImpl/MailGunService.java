package com.sendingEmail.demo.serviceImpl;


import org.apache.http.HttpStatus;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sendingEmail.demo.model.EmailForm;

@Service
@ConfigurationProperties(prefix = "mailgun.api")
public class MailGunService {	
	private String password;
	private String url;
	private String sendFrom;
	

    public void sendSimpleMessage(EmailForm eForm) throws UnirestException {    	

        HttpResponse<JsonNode> request = Unirest.post(this.url)
            .basicAuth("api", this.password)            
            .field("from", this.sendFrom)
            .field("to", eForm.getSendTo())
            .field("subject", eForm.getSubject())
            .field("text", eForm.getMessage())
            .asJson();
    	

        
        System.out.println(request.getStatus());
        System.out.println(request.getBody());
        
        if(request.getStatus() != HttpStatus.SC_OK && request.getStatus() != HttpStatus.SC_ACCEPTED) {
        	throw new UnirestException(String.format("MailGun Mail Not Sent, Status Code(%d)", request.getStatus()));
        }
     
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}
    
    
    

}