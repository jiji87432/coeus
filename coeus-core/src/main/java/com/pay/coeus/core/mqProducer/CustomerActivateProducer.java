package com.pay.coeus.core.mqProducer;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.astrotrain.client.ATMessage;
import com.pay.astrotrain.client.ATProducer;
import com.pay.astrotrain.client.producer.DefaultATProducer;
import com.pay.astrotrain.client.exceptions.ATException;
import com.pay.astrotrain.client.message.StringMessage;

@Component
public class CustomerActivateProducer implements InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DefaultATProducer defaultATProducer;
	
	private ATProducer producer;
	
	private static final String CUSTOMER_ACTIVATE_TIME="CUSTOMER_ACTIVATE_TIME";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		producer = this.defaultATProducer.createProducer(CUSTOMER_ACTIVATE_TIME);
	}
	
	public void sendMsg(Map<String,Object> params) {
		//String msg = JSON.toJSONStringWithDateFormat(params,"yyyy-MM-dd HH:mm:ss");
		String msg = JSONObject.toJSONString(params);
		logger.info("CustomerActivate sendMsg {}",msg);
		// 新建一个StringMessage
		StringMessage msgObj = new StringMessage(msg);
		// 为消息设置一个业务标识符,最好是唯一的,方便在调试程序时进行跟踪,可选属性.
		msgObj.setProperty(ATMessage.MSG_KEYS, UUID.randomUUID().toString());
		try {
			producer.send(msgObj);
		} catch (ATException e) {
			logger.error("CustomerActivate sendmsg error ", e);
		}
	}

}
