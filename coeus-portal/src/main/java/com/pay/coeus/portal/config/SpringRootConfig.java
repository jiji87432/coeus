package com.pay.coeus.portal.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import com.pay.commons.cache.context.CacheConfig;

/**
 * spring配置
 * @author yongda.ren
 *
 */
@Configuration
@EnableAspectJAutoProxy
@Import({DubboConfig.class, DubboClientConfig.class, JedisConfig.class, CacheConfig.class })
@ComponentScan(
		basePackages = "com.pay.coeus.portal.controller,"
				+ "com.pay.coeus.portal.dubbo,"
				+ "com.pay.coeus.portal.job,"
				+ "com.pay.commons.cache.util,"
				+ "com.pay.coeus.common.utils", 
				excludeFilters = { @Filter(Controller.class),
		@Filter(Configuration.class)})
public class SpringRootConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public Validator validator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}
}
