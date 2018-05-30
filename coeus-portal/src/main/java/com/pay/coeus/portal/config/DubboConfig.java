package com.pay.coeus.portal.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * dubbo配置
 * @author yongda.ren
 *
 */
@Configuration
@PropertySource("classpath:/dubbo.properties")
public class DubboConfig {
	@Value("${dubbo.appName}")
	private String appName;

	@Value("${dubbo.resAddress}")
	private String resAddress;

//	@Value("${dubbo.port}")
//	private int port;

	@Bean
	public ApplicationConfig application() {
		ApplicationConfig applicationConfig = new ApplicationConfig(appName);
		applicationConfig.setMonitor(monitor());
		applicationConfig.setRegistry(registry());
		return applicationConfig;
	}

	@Bean
	public RegistryConfig registry() {
		RegistryConfig registryConfig = new RegistryConfig(resAddress);
//		registryConfig.setPort(port);
		return registryConfig;
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
		return config;
	}
}
