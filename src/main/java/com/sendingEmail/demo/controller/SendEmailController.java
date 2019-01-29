package com.sendingEmail.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.context.annotation.PropertySource;
import com.sendingEmail.demo.model.EmailForm;
import com.sendingEmail.demo.serviceImpl.MailGunService;
import com.sendingEmail.demo.serviceImpl.SendGridService;
@Controller
@PropertySource("classpath:/application.properties")
public class SendEmailController {
    @Autowired
    public MailGunService mgEmailSender;
    @Autowired
    public SendGridService sgEmailSender;
    
	@Value("${sendmail.info.success}")
	private String emailSent;
	@Value("${sendmail.error.notsent}")
	private String emailNotSent;
	@Value("${sendmail.alert.incomplete}")
	private String emailIncomplete;
 
	
    @RequestMapping(value = {"/", "/composeEmail"}, method = RequestMethod.GET)
    public String composeEmail(Model model) {
    	EmailForm emailForm = new EmailForm();
    	model.addAttribute("emailForm", emailForm);
    	return "composeEmail";    	
    }
    
    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    public String sendEmail(
    		@Valid @ModelAttribute("emailForm") EmailForm emailForm,
    		BindingResult result, Model  model) {
    	
    	
    	if(result.hasErrors() || !emailForm.getEmailErrMsg().isEmpty()) {    		
    		model.addAttribute("errorMessage", emailIncomplete + emailForm.getEmailErrMsg());
    		return "composeEmail";
    	}
    	
    	try {
    		sgEmailSender.sendSimpleMessage(emailForm);
    	}catch (Exception err) {
    		err.printStackTrace();    		        		
    		try {
    			mgEmailSender.sendSimpleMessage(emailForm);
    		}catch (Exception ex) {        			
    			model.addAttribute("errorMessage", emailNotSent);
    			ex.printStackTrace();
    			return "composeEmail";
    		}
    	}
    	model.addAttribute("resultMessage", emailSent);
    	return "composeEmail";
    	
    	    	
    	
    }
}
