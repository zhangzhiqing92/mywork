package com.work.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
