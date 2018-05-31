package com.pay.coeus.core.config;

import com.alibaba.dubbo.config.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * dubbo配置
 * @author yongda.ren
 *
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class DubboConfig {
	@Value("${dubbo.appName}")
	private String appName;

	@Value("${dubbo.resAddress}")
	private String resAddress;

	/*
	 * @Value("${dubbo.pay.resAddress}") private String payResAddress;
	 */

	/*
	 * @Value("${dubbo.resUsername}") private String resUsername;
	 * 
	 * @Value("${dubbo.resPassowrd}") private String resPassowrd;
	 */

	@Value("${dubbo.protocol}")
	private String protocol;

	@Value("${dubbo.port}")
	private int port;

	// @Value("${dubbo.monAddress}")
	// private String monAddress;
	//
	// @Value("${dubbo.connections}")
	// private int connections;

	@Bean
	public ApplicationConfig application() {
		ApplicationConfig applicationConfig = new ApplicationConfig(appName);
		applicationConfig.setMonitor(monitor());
		applicationConfig.setRegistries(registries());
		return applicationConfig;
	}

	private List<RegistryConfig> registries() {
		List<RegistryConfig> registries = new ArrayList<RegistryConfig>();
		// 自身系统dubbo服务注册中心
		registries.add(registry());
		// 双中心，注册卡友dubbo中心
		// registries.add(payRegistry());
		return registries;
	}

	@Bean
	public RegistryConfig registry() {
		RegistryConfig registryConfig = new RegistryConfig(resAddress);
		// registryConfig.setUsername(resUsername);
		// registryConfig.setPassword(resPassowrd);
		// registryConfig.setProtocol(protocol);
		registryConfig.setPort(port);
		registryConfig.setDefault(true);
		return registryConfig;
	}

	/*
	 * @Bean(name="payRegistry") public RegistryConfig payRegistry() {
	 * RegistryConfig payRegistryConfig = new RegistryConfig(payResAddress);
	 * //payRegistryConfig.setUsername(resUsername);
	 * //payRegistryConfig.setPassword(resPassowrd);
	 * payRegistryConfig.setProtocol(protocol);
	 * payRegistryConfig.setDefault(false); return payRegistryConfig; }
	 */

	@Bean
	public ProtocolConfig protocol() {
		ProtocolConfig protocolConfig = new ProtocolConfig(protocol, port);
		return protocolConfig;
	}

	@Bean
	public MonitorConfig monitor() {
		MonitorConfig monitorConfig = new MonitorConfig();
		monitorConfig.setProtocol("registry");
		return monitorConfig;
	}

	@Bean
	public ProviderConfig provider() {
		ProviderConfig config = new ProviderConfig();
		// config.setConnections(connections);
		return config;
	}
}
