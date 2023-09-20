package com.imdb.title.config;

import com.imdb.title.filter.RequestCounter;
import com.imdb.title.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<RequestFilter> requestCounterFilter(RequestCounter counter) {
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestFilter(counter));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

