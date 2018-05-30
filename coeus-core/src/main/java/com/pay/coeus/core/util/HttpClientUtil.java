package com.pay.coeus.core.util;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pay.common.util.PropertyUtil;

public class HttpClientUtil {
	@Autowired
    private static final Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	private static final String HTTP_URL;
	private static final String HTTPS_PROTOCOL = "https";
	private static final String PROXY_IP;
	private static final String PROXY_PORT;
	
	static{
		PropertyUtil propertyUtil = PropertyUtil.getInstance("system");
		HTTP_URL = propertyUtil.getProperty("tr.httpclient.url");
		PROXY_IP = propertyUtil.getProperty("httpclient.proxyIp");
		PROXY_PORT = propertyUtil.getProperty("httpclient.proxyPort");
	}
	
	public static CloseableHttpClient createSSLClientDefault() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			//信任所有
			public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	
	@SuppressWarnings("unchecked")
	public static String getHttpPost(String methodName,Map<String,Object> map,String... clientUrl) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		String msg = null;
		HttpResponse response = null;
		String uri = null;
        HttpClient client = null;
        
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    	Iterator<String> iterator = map.keySet().iterator();
    	while(iterator.hasNext()){
    		String key = iterator.next();
    		String value = (String)map.get(key);
    		parameters.add(new BasicNameValuePair(key, value));
    	}
        if(clientUrl.length == 0){
			uri = HTTP_URL;
		}else{
			uri = clientUrl[0];
		}
		logger.info(uri);
	    if(HTTPS_PROTOCOL.equals(new URL(uri).getProtocol())){
			client = createSSLClientDefault();
		}else{
			//把代理设置到请求配置
			HttpHost proxy = new HttpHost(PROXY_IP,Integer.parseInt(PROXY_PORT),"http");
	        RequestConfig defaultRequestConfig = RequestConfig.custom()
	                .setProxy(proxy)
	                .build();
			client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		}
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
        HttpPost request = new HttpPost(uri+methodName);
    	request.setEntity(entity);
        
    	response = client.execute(request);
        
        logger.info("Response Code: " + response.getStatusLine().getStatusCode()); 
        
        if(response.getStatusLine().getStatusCode() == 200) {
        	HttpEntity he = response.getEntity();
        	msg = EntityUtils.toString(he,"UTF-8");
        	
//        	logger.info(msg);
        }
        
        return msg;
	}
	
	public static String getHttpGet(String methodName,Map<String,Object> paramMap,String... clientUrl) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		String msg = null;
		HttpResponse response = null;
		String uri = null;
		HttpClient client = null;
		
		Iterator<String> iterator = paramMap.keySet().iterator();
		StringBuffer param = new StringBuffer();
		int count = 0;
		while(iterator.hasNext()){
			String key = iterator.next();
			Object value = paramMap.get(key);
			if(count == 0){
				param.append("?").append(key).append("=").append(value);
			}else{
				param.append("&").append(key).append("=").append(value);
			}
			count++;
		}
		
		if(clientUrl.length == 0){
			uri = HTTP_URL+methodName+param.toString();
		}else{
			uri = clientUrl[0]+methodName+param.toString();
		}
		
		logger.info(uri);
		
		if (HTTPS_PROTOCOL.equals(new URL(uri).getProtocol())){
			client = createSSLClientDefault();
		}else{
			//把代理设置到请求配置
			HttpHost proxy = new HttpHost(PROXY_IP,Integer.parseInt(PROXY_PORT),"http");
	        RequestConfig defaultRequestConfig = RequestConfig.custom()
	                .setProxy(proxy)
	                .build();
			client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		}
		
		HttpGet request = new HttpGet(uri);  
    	response = client.execute(request);
    	
    	logger.info(response.getStatusLine().getStatusCode());
    	
    	
    	if(response.getStatusLine().getStatusCode() == 200) {
        	HttpEntity he = response.getEntity();
        	msg = EntityUtils.toString(he,"UTF-8");
        	
        	logger.info(msg);
        }
		
		return msg;
	}
	
	public static void main(String[] args) throws Exception{
	} 
}
