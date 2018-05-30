package com.pay.coeus.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:dubbo-client.xml")
public class DubboClientConfig {

}
