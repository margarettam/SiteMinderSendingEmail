package com.sendingEmail.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;



public class EmailForm {

	private String sendFrom;	
	@NotNull
	@NotEmpty(message = "Recipients cannot be blank")	
	private String sendTo;
	private List<String> sendToList = new ArrayList<String>();
	private String cc;
	private List<String> ccList = new ArrayList<String>();
	private String bcc;
	private List<String> bccList = new ArrayList<String>();
	@NotNull
	@NotEmpty(message = "Subject cannot be blank")
	private String subject;
	@NotNull
	@NotEmpty(message = "Message cannot be blank")
	private String message;
	
	private List<String> invalidEmailList = new ArrayList<String>();
	private Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public String getSendFrom() {
		
		return sendFrom;
	}
	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}
	public String getSendTo() {
		return sendTo;
	}
	public List<String> splitEmailToList(String emails) {
		List<String> recipients = new ArrayList<String>();
		if(emails != null && !emails.isEmpty()) {
			recipients = Stream.of(emails.split(","))
		      .map (elem -> validateEmail(elem.trim()))
		      .collect(Collectors.toList());			
		}
		return recipients;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
		this.sendToList = sendTo!=null?splitEmailToList(sendTo):new ArrayList<String>();
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
		this.ccList = cc!=null?splitEmailToList(cc):new ArrayList<String>();
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
		this.bccList = bcc!=null?splitEmailToList(bcc):new ArrayList<String>();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    public List<String> getSendToList() {
		return sendToList;
	}
	public List<String> getCcList() {
		return ccList;
	}
	public List<String> getBccList() {
		return bccList;
	}
	public String validateEmail(String email)
    {
        Matcher matcher = emailPattern.matcher(email);
        if(!matcher.find())
        {
        	invalidEmailList.add(email);
        }
        
        return email;
    }	
	public String getEmailErrMsg() {
		if(!invalidEmailList.isEmpty()) {
			return " Invalid Email: " + invalidEmailList.stream().map(e -> e).collect(Collectors.joining(", "));
		}
		return "";
	}


}
