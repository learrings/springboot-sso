package com.one.config;

import com.one.Interceptor.SSOInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 *  WEB配置
 *
 * @author learrings
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Resource
	private SSOInterceptor ssoInterceptor;

	/**
	 *  配置拦截器
	 **/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ssoInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/index")
				.excludePathPatterns("/logout");
	}

	/**
	 *  配置static
	 **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
