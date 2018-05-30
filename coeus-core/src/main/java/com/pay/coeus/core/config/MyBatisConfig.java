package com.pay.coeus.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置
 * @author yongda.ren
 *
 */
@Configuration
@MapperScan(basePackages ="com.pay.coeus.core.dao.coeus",sqlSessionFactoryRef="sqlSessionFactory")
@EnableTransactionManagement(order = 0)
public class MyBatisConfig {
	@Autowired
	private DataSource dataSource;
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			Resource configLocation = new ClassPathResource("mybatis-config.xml");
			bean.setConfigLocation(configLocation);
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
