package com.crypto.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@ComponentScan (basePackages={"com.crypto.project.controller", "com.crypto.project.service","com.crypto.project.model","com.crypto.project.serviceImpl","com.crypto.project.auth","com.crypto.project.helpers","com.crypto.project.configs","com.crypto.project.singleton"})
@EnableJpaRepositories(basePackages="com.crypto.project.service")
@Configuration
@EnableScheduling
public class CryptoGraphyProject extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CryptoGraphyProject.class, args);
	}
	
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(CryptoGraphyProject.class);
	  }
	 
	 @Bean
	    public CommonsRequestLoggingFilter logFilter() { 
	        CommonsRequestLoggingFilter filter
	          = new CommonsRequestLoggingFilter();
	        filter.setIncludeQueryString(true);
	        filter.setIncludePayload(true);
	        filter.setMaxPayloadLength(10000);
	        filter.setIncludeHeaders(false);
	        filter.setAfterMessagePrefix("REQUEST DATA : ");
	        return filter;
	    }
	
}
