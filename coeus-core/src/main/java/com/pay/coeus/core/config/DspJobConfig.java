package com.pay.coeus.core.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pay.dsp.core.executor.DspJobExecutor;

/**
 * 分布式调度平台配置
 * @Description: 这里用一句话描述这个类的作用
 * @see: DspJobConfig 此处填写需要参考的类
 * @version 2018年1月12日 上午8:34:47 
 * @author yuze.luo
 */
@Configuration
@ComponentScan(basePackages = "com.pay.coeus.core.quartz.handler")
@PropertySource("classpath:/system.properties")
public class DspJobConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DspJobConfig.class);
	
	@Value("${dsp.zookeeper}")
	private String zkServerAddress;
	
	@Value("${com.pay.app}")
	private String appName;
	
	@Bean(name="dspJobExecutor",initMethod = "start", destroyMethod = "destroy")
	public DspJobExecutor dspJobExecutor() throws IOException {
		long l = System.currentTimeMillis();
		logger.info("appName={}, zkServerAddress={}, currentTimeMillis={}", appName, zkServerAddress, l);
		DspJobExecutor c = new DspJobExecutor();
		c.setZkServerAddress(zkServerAddress);
		c.setExecutorAppName(appName);
		return c;
	}

}