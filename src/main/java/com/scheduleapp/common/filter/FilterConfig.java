package com.scheduleapp.common.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 설정 클래스 //
public class FilterConfig {

    @Bean  // 서블릿 필터 등록 빈
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // Filter 등록 -> LoginFilter 적용
        filterRegistrationBean.addUrlPatterns("/*");  // 전체 URL에 Filter 적용

        return filterRegistrationBean;
    }
}
