package com.pay.coeus.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pay.astrotrain.client.consumer.DefaultATPushConsumer;
import com.pay.coeus.core.mqListener.PosRequestListener;

@Configuration
@PropertySource("classpath:/application.properties")
public class MqConsumerConfig {
	
	@Value("${astrotrain.appId}")
	private String appId;
	
	@Value("${astrotrain.group.name}")
	private String groupName;
	
	@Value("${astrotrain.instance.name}")
	private String instanceName;
	
	@Value("${astrotrain.namesrv.address}")
	private String namesrvAddr;
	
	private static final String POSREQUEST_TOPIC="PosRequest";
	@Bean(name = "atConsumer", destroyMethod = "shutdown" )
	public DefaultATPushConsumer consumer(PosRequestListener posRequestListener) throws Exception {
		DefaultATPushConsumer consumer = new DefaultATPushConsumer();
		consumer.setAppId(appId);
		consumer.setGroupName(groupName);
		consumer.setInstanceName(instanceName);
		consumer.setNamesrvAddr(namesrvAddr);
		consumer.subscribe(POSREQUEST_TOPIC, posRequestListener);
		consumer.start();
		return consumer;
	}
	
}
