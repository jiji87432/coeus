package com.pay.coeus.core.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * spring配置
 * @author yongda.ren
 *
 */
@Configuration
@EnableAspectJAutoProxy
@EnableApolloConfig({"application", "quartz"})
@Import({DubboConfig.class,DubboClientConfig.class, JedisConfig.class,DubboServiceConfig.class,DBConfig.class, MyBatisConfig.class, 
	PospbossMyBatisConfig.class,MqConsumerConfig.class,DspJobConfig.class,MqProducerConfig.class})
@ComponentScan(
		basePackages ="com.pay.coeus.core.service,"
				+ "com.pay.coeus.core.dubbo,"
				+ "com.pay.coeus.core.quartz,"
				+ "com.pay.coeus.core.quartz.jobs,"
				+ "com.pay.coeus.core.mqListener,"
				+ "com.pay.coeus.core.mqProducer,"
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
