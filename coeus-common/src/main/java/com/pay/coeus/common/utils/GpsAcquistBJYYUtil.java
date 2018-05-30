package com.pay.coeus.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 北京优友
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsAcquistBJYYUtil 此处填写需要参考的类
 * @version 2018年2月23日 上午7:00:17 
 * @author yuze.luo
 */
public class GpsAcquistBJYYUtil {
	
	private static String baseUrl = "http://ws.sim001.com:8080/simmanager/interfaces/getGprs.action";
	
	/**
	 * 获取流量信息（最支持200条，时间段不限）
	 * @Description  一句话描述方法用法
	 * @param iccidList 逗号分开的iccid
	 * @param strDate 开始时间（20171001）
	 * @param endDate 结束时间（20171031）
	 * @return flag(String) ：SUCCESS/FAIL
	 * 			respCode(String)：1成功其他失败
	 * 			respDesc(String)：描述
	 * 			list(String)	：iccid	   (String):
	 * 					  		  useFlow (String):使用流量
	 * @see 需要参考的类或方法
	 */
	public static Map<String, Object> getGpsData(String iccids, String strDate, String endDate){
		if(iccids == null || iccids.length()== 0){
			return null;
		}
		// 返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 传参对象
		Map<String, String> params = new TreeMap<String, String>();
		params.put("iccids",iccids);
		params.put("date", strDate+"-"+endDate);
		String result3 = sendGet(baseUrl, mapToFormData(params));
		//{"msg":"正确","result":[{"iccid":"89860401101771051427","dosage_of_gprs":"0.42"},{"iccid":"89860401101771059797","dosage_of_gprs":"0.46"}],"status":"1"}
		JSONObject json = (JSONObject) JSONObject.parse(result3);
		if("1".equals(json.get("status").toString())){
			returnMap.put("flag", "SUCCESS");
			returnMap.put("respCode", json.get("status"));
			returnMap.put("respDesc", json.get("msg"));
			System.out.println(json.get("status")+","+json.get("msg"));
			JSONArray list = (JSONArray) JSONArray.parse(json.get("result").toString());
			JSONObject obj = null;
			List<Map<String, Object>> flows = new ArrayList<Map<String, Object>>();
			Map<String, Object> flow= null;
			for(int i=0;i<list.size();i++){
				obj = (JSONObject) JSONObject.parse(list.get(i).toString());
				if(obj.get("dosage_of_gprs") != null && Double.valueOf(obj.get("dosage_of_gprs").toString()) > 0){
					flow = new HashMap<String, Object>();
					flow.put("iccid", obj.get("iccid"));
					flow.put("useFlow", obj.get("dosage_of_gprs"));
					flows.add(flow);
				}
			}
			returnMap.put("list", flows);
		}else{
			returnMap.put("flag", "FAIL");
			returnMap.put("respCode", json.get("status"));
			returnMap.put("respDesc", json.get("msg"));
			System.out.println(json.get("status")+","+json.get("msg"));
		}
		return returnMap;
	}
	
	/*
     * 将map转换成传参字符串
     */
    private static String mapToFormData(Map<String, String> map)  {
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
    
    private static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			System.out.println("urlNameString：" + urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
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
}


