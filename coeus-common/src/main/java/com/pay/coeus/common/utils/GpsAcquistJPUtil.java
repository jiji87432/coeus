package com.pay.coeus.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 九鹏。
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsAcquistXLXUtil 此处填写需要参考的类
 * @version 2017年11月28日 上午2:01:24 
 * @author yuze.luo
 */
public class GpsAcquistJPUtil {
	
	private static String baseUrl ="http://m2m.jpcomm.cn:3666/iot";
	
	private static String appid="3";
	
	private static String otn="coeus";
	
	private static String apiKey="b1aad41df3f84cd5ba921e0f7ede5dae";
	
	public static void bindPushUrl(String url) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "bindPushUrl");
		map.put("appid", appid);
		map.put("url", url);
		map.put("sign", signMap(map));
		String result1 = sendGet(baseUrl, mapToFormData(sortMapByKey(map)));
		System.out.println("GpsAcquistJPUtil,bindPushUrl:"+result1);
	}
	
	public static Map<String, Object> getGpsData(String iccids, String strDate, String endDate) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "queryUsageDetail");
		map.put("appid", appid);
		map.put("bd", strDate.substring(0, 4)+"-"+strDate.substring(4, 6)+"-"+strDate.substring(6));
		map.put("ed", endDate.substring(0, 4)+"-"+endDate.substring(4, 6)+"-"+endDate.substring(6));
		map.put("iccids", iccids);
		map.put("otn", otn);
		map.put("v", "1.2");
		map.put("sign", signMap(map));
		String result2 = sendGet(baseUrl, mapToFormData(sortMapByKey(map)));
		System.out.println("GpsAcquistJPUtil,getGpsData:"+result2);
		return null;
	}
	
	public static void main(String[] args){
		String strDate = "20180215";
		System.out.println(strDate.substring(0, 4)+"-"+strDate.substring(4, 6)+"-"+strDate.substring(6));
		
	}
	
    
	/**
	 * 签名
	 * @param map
	 * @return
	 */
	public static String signMap(Map<String, String> map){
		if(map != null && map.size()>0){
			map = sortMapByKey(map);
			StringBuffer str = new StringBuffer();
			str.append(apiKey);
			for (Map.Entry<String, String> entry : map.entrySet()) {
    	        str.append(entry.getValue());
    	    }
			return DigestUtils.md5Hex(str.toString());
		}
		return null;
	}
	
	/**
	 * 排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
	
	 /*
     * 将map转换成传参字符串
     */
    public static String mapToFormData(Map<String, String> map)  {
    	StringBuffer str = new StringBuffer();
        if (map != null && map.size()>0) {
        	for (Map.Entry<String, String> entry : map.entrySet()) {
    	        if(str.length() == 0){
    	        	str.append(entry.getKey() + "=" + entry.getValue());
    	        }else{
    	        	str.append("&"+entry.getKey() + "=" + entry.getValue());
    	        }
    	    }
        	System.out.println("mapToFormData--"+str.toString());
        }
        return str.toString();
    }
    /**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			//在你发起Http请求之前设置一下属性  
        	System.setProperty("http.proxyHost", "172.20.6.88");  
        	System.setProperty("http.proxyPort", "3128");
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			/*// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			System.getProperties().remove("http.proxyHost");  
            System.getProperties().remove("http.proxyPort"); 
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
    
}


