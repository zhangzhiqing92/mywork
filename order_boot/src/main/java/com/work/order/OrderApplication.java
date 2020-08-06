package com.work.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class OrderApplication {
//	@Bean
//	public FilterRegistrationBean httpServletRequestReplacedRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new HttpServletRequestReplacedFilter());
//		registration.addUrlPatterns("/*");
//		registration.addInitParameter("paramName", "paramValue");
//		registration.setName("httpServletRequestReplacedFilter");
//		registration.setOrder(1);
//		return registration;
//	}
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
