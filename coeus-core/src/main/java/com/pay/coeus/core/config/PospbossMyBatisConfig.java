package com.pay.coeus.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.pay.coeus.core.dao.boss", sqlSessionFactoryRef="sqlSessionFactoryBoss" )
@EnableTransactionManagement(order = 0)
public class PospbossMyBatisConfig {

	@Autowired
	private DataSource dataSourceBoss;

	@Bean(name="sqlSessionFactoryBoss")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSourceBoss);
			Resource configLocation = new org.springframework.core.io.ClassPathResource("mybatis-config.xml");
			bean.setConfigLocation(configLocation);
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Bean(name="dataSourceTransactionManagerBoss")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSourceBoss);
	}
}