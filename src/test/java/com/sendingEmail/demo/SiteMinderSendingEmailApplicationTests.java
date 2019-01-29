package com.sendingEmail.demo;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sendingEmail.demo.controller.SendEmailController;
import com.sendingEmail.demo.model.EmailForm;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteMinderSendingEmailApplicationTests {

	@Autowired
	private SendEmailController controller;
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testSplitEmailToList() {
		EmailForm eform = new EmailForm();
		eform.setSendTo("test, test2, test3,test4");
		List<String> splitedList = eform.getSendToList();		
		Set<String> expectedNames=new HashSet<>(Arrays.asList("test", "test2", "test3", "test4"));
		assertThat(splitedList.stream().map(str -> str)
		                 .collect(Collectors.toSet()).equals(expectedNames));

		
	}

}

