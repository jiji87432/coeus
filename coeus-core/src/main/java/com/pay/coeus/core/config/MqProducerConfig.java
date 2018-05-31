package com.pay.coeus.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pay.astrotrain.client.producer.DefaultATProducer;

@Configuration
@PropertySource("classpath:/application.properties")
public class MqProducerConfig {
	
	@Value("${astrotrain.appId}")
	private String appId;
	
	@Value("${astrotrain.group.name}")
	private String groupName;
	
	@Value("${astrotrain.instance.name}")
	private String instanceName;
	
	@Value("${astrotrain.namesrv.address}")
	private String namesrvAddr;
	
	@Bean(initMethod="start", destroyMethod="shutdown")
	public DefaultATProducer consumer() throws Exception {
		DefaultATProducer producer = new DefaultATProducer();
		producer.setAppId(appId);
		producer.setGroupName(groupName);
		producer.setInstanceName(instanceName);
		producer.setNamesrvAddr(namesrvAddr);
		return producer;
	}
	
}
