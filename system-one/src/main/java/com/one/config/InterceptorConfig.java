package com.one.config;

import com.one.Interceptor.SSOInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 *  配置拦截器
 *
 * @author learrings
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Resource
	private SSOInterceptor ssoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ssoInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/index")
				.excludePathPatterns("/logout");
	}
}
