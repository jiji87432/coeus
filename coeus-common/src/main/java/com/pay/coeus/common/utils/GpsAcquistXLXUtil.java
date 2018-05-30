package com.pay.coeus.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 芯联芯/领佳。
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsAcquistXLXUtil 此处填写需要参考的类
 * @version 2017年11月28日 上午2:01:24 
 * @author yuze.luo
 */
public class GpsAcquistXLXUtil {
	
	private static String baseUrl = "http://mgr.m2mconnect.cn:7700/api/batchQueryFlow.do";
	
	private static String appid = "10000001";
	
	private static String password = "12345678";
	
	public static Map<String, Object> getGpsData(String iccids, String strDate, String endDate) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("iccids", iccids);// 最多200个
		map.put("startDate", strDate);
		map.put("endDate", endDate);
		String transid = appid+"@"+DateUtils.getDate("yyyyMMddHHmmss")+"@"+DateUtils.getDate("yyyyMMdd");
		map.put("transid", transid);
		String token = appid+password+transid;
		map.put("token", Encrypt(token, "SHA-256"));
		String result3 = sendGet(baseUrl, mapToFormData(map));
		JSONObject json = (JSONObject) JSONObject.parse(result3);
		// 返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if("0".equals(json.get("code").toString())){
			returnMap.put("flag", "SUCCESS");
			returnMap.put("respCode", json.get("code"));
			returnMap.put("respDesc", "SUCCESS");
			System.out.println(json.get("code")+"_xinlianxin_success");
			List<Map<String, Object>> flows = new ArrayList<Map<String, Object>>();
			Map<String, Object> flow= null;
			String[] iccidlist= iccids.split(",");
			JSONArray list = (JSONArray) JSONArray.parse(json.get("data").toString());
			JSONObject obj = null;
			if(list != null && list.size()>0){
				for(int k=0;k<iccidlist.length;k++){
					for(int i=0;i<list.size();i++){
						obj = (JSONObject) JSONObject.parse(list.get(i).toString());
						if("00".equals(obj.get("status")) && iccidlist[k].equals(obj.get("iccid")) && !"0".equals(obj.get("gprs").toString())){
							flow = new HashMap<String, Object>();
							flow.put("iccid", iccidlist[k]);
							flow.put("useFlow",obj.get("gprs"));
							flows.add(flow);
							break;
						}
					}
				}
			}
			returnMap.put("list", flows);
		}else{
			returnMap.put("flag", "FAIL");
			returnMap.put("respCode", json.get("code"));
			returnMap.put("respDesc", "FAIL");
			System.out.println(json.get("status")+"_iot_fail");
		}
		return returnMap;
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


