package com.pay.coeus.core.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pay.dsmclient.v2.c3p0.C3p0PooledDataSource;

/**
 * 数据源配置
 * @author yongda.ren
 */
@Configuration
@PropertySource("classpath:/db.properties")
public class DBConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
	@Value("${com.pay.coeus.core.ds.name}")
	private String dsName;
	
	@Value("${com.pay.coeus.core.pospboss.name}")
	private String dsNameBoss;

	@Bean(destroyMethod = "close", name="dataSource")
	public DataSource dataSource() {
		long l = System.currentTimeMillis();
		logger.info("dsName = {} , {}", dsName, l);
		C3p0PooledDataSource ds = new C3p0PooledDataSource();
		ds.setDataSourceName(dsName);
		return ds;
	}
	
	@Bean(destroyMethod = "close", name="dataSourceBoss")
	public DataSource dataSourceBoss() {
		long l = System.currentTimeMillis();
		logger.info("dsNameBoss = {} , {}", dsNameBoss, l);
		C3p0PooledDataSource ds = new C3p0PooledDataSource();
		ds.setDataSourceName(dsNameBoss);
		return ds;
	}
}
