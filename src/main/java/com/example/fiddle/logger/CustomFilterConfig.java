package com.example.fiddle.logger;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFilterConfig {
  @Bean
  public FilterRegistrationBean<CustomLoggingFilter> loggingFilter() {
    FilterRegistrationBean<CustomLoggingFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new CustomLoggingFilter());
    registrationBean.addUrlPatterns("/api/seo/products/*");
    registrationBean.addUrlPatterns("/api/seo/charities/*");

    return registrationBean;
  }
}
