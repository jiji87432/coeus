package com.pay.coeus.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * DubboService 服务提供
 * @author yongda.ren
 *
 */
@Configuration
@ImportResource("classpath:dubbo-service.xml")
public class DubboServiceConfig {

}
