package com.pay.coeus.core.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.camel.client.api.impl.CamelClientApiImpl;
import com.pay.camel.remote.bean.CamelResponse;
import com.pay.camel.remote.bean.Goods;
import com.pay.camel.remote.bean.MessageLevel;
import com.pay.camel.remote.bean.MessageSendType;
import com.pay.camel.remote.bean.MessageType;
import com.pay.camel.remote.bean.MessgReceiver;
import com.pay.camel.remote.bean.SmsSendType;
import com.pay.common.util.PropertyUtil;

/**
 * Description: 消息通知工具类
 * 
 * @see: CamelUtil 此处填写需要参考的类
 * @version 2017年3月30日 上午11:14:32
 * @author meng.ren
 */
public class CamelUtil {

	private static Logger logger = LoggerFactory.getLogger(CamelUtil.class);

	private static String host;// ip
	private static String port;// port
	static {
		PropertyUtil propertyUtil = PropertyUtil.getInstance("application");
		host = propertyUtil.getProperty("com.pay.camel.host");
		port = propertyUtil.getProperty("com.pay.camel.port");
	}

	/**
	 * @Description 发送微信类消息
	 * @param content
	 * @param receivers
	 * @param busiType
	 *            业务大分类_小分类，全中文
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static boolean send(String content, String receivers, String busiType) {
		logger.info("Camel 通知, content = {}", content);
		Goods goods = new Goods();
		goods.setMessgType(MessageType.NOTE);
		goods.setMessgLevel(MessageLevel.INFO);
		goods.setAppCode("wxcustfront");
		goods.setBusiType(busiType);
		goods.setToken("DgBSKnayiuPLckZi2SrhuQ==");
		goods.setContent(content);
		List<MessageSendType> messgSendTypes = new ArrayList<MessageSendType>();
		messgSendTypes.add(MessageSendType.WEIXIN);
		goods.setMessgSendTypes(messgSendTypes);
		MessgReceiver messgReceiver = new MessgReceiver();
		goods.setMessgReceiver(messgReceiver);
		messgReceiver.setWeixinName(receivers);
		CamelClientApiImpl api = CamelClientApiImpl.getInstance();
		api.setHost(host);
		api.setPort(Integer.parseInt(port));
		CamelResponse result = api.send(goods);
		logger.info("Camel 通知 result = {}", result);
		if (result != null) {
			return result.getResult();
		}
		return false;
	}

	

	/**
	 * 发送通知类短信 busiType 业务大分类_小分类，全中文
	 */
	public static boolean sendSmsNotice(String content, String phones, String busiType) {
		Goods goods = new Goods();
		goods.setMessgType(MessageType.NOTE);
		goods.setMessgLevel(MessageLevel.INFO);
		goods.setAppCode("wxcustfront");
		goods.setBusiType(busiType);
		goods.setToken("DgBSKnayiuPLckZi2SrhuQ==");
		goods.setContent(content);
		List<MessageSendType> messgSendTypes = new ArrayList<MessageSendType>();
		messgSendTypes.add(MessageSendType.SMS);
		goods.setMessgSendTypes(messgSendTypes);
		MessgReceiver messgReceiver = new MessgReceiver();
		goods.setMessgReceiver(messgReceiver);
		messgReceiver.setPhone(phones);
		CamelClientApiImpl api = CamelClientApiImpl.getInstance();
		api.setHost(host);
		api.setPort(Integer.parseInt(port));
		CamelResponse result = api.send(goods);
		if (result != null) {
			logger.info("sendSmsNotice>phones:" + phones + ",content:" + content + "," + result);
			return result.getResult();
		}
		return false;
	}

	/**
	 * 发送营销类短信 content 模版消息 phones 13555555555,13666666666,13777778888
	 */
	public static boolean sendSmsSale(String content, String phones) {
		Goods goods = new Goods();
		goods.setMessgType(MessageType.NOTE);
		goods.setMessgLevel(MessageLevel.INFO);
		goods.setAppCode("wxcustfront");
		goods.setBusiType("考伊斯_通知短信");
		goods.setToken("DgBSKnayiuPLckZi2SrhuQ==");
		goods.setContent(content);
		List<MessageSendType> messgSendTypes = new ArrayList<MessageSendType>();
		messgSendTypes.add(MessageSendType.SMS);
		goods.setMessgSendTypes(messgSendTypes);
		MessgReceiver messgReceiver = new MessgReceiver();
		goods.setMessgReceiver(messgReceiver);
		messgReceiver.setSmsSendType(SmsSendType.SALE);
		messgReceiver.setPhone(phones);
		CamelClientApiImpl api = CamelClientApiImpl.getInstance();
		api.setHost(host);
		api.setPort(Integer.parseInt(port));
		CamelResponse result = api.send(goods);
		if (result != null) {
			System.out.println("sendSmsSale>phones:" + phones + ",content:" + content + "," + result);
			return result.getResult();
		}
		return false;
	}

}
