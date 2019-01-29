package com.sendingEmail.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SiteMinderSendingEmailApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SiteMinderSendingEmailApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(SiteMinderSendingEmailApplication.class, args);
	}

}

