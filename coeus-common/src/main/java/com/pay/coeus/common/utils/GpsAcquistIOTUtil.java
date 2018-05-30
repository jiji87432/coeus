package com.pay.coeus.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

/**
 * 华信信通。
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsAcquistIOTUtil 此处填写需要参考的类
 * @version 2017年11月7日 上午2:01:24 
 * @author yuze.luo
 */
public class GpsAcquistIOTUtil {
	
	private static String baseUrl = "http://wulian.huaxincloud.com/Api/Iot/get_data_usage_2";
	
	/**
	 * 1. 接口认证 http 请求头部添加 X-4GIOT-Key，Key 值
		$header = [
 		'X-4GIOT-Key: IW9tP7sW6HDKQCJ343xdpDDyAEHjkcos7N8D9b6kFQ2hJk6L0xwTK6A5hBTap9ATi9D2s6TRXbyzAlNeyWiNGuI0c3kxhUUuf8YzH3hTCDWyx1tWMECtPMdNqCPpLlA1'
		];
		2. 代理商秘钥 key，Key 值为：cdHcZx2rvD0btw
	 */
	private static String header_key = "IW9tP7sW6HDKQCJ343xdpDDyAEHjkcos7N8D9b6kFQ2hJk6L0xwTK6A5hBTap9ATi9D2s6TRXbyzAlNeyWiNGuI0c3kxhUUuf8YzH3hTCDWyx1tWMECtPMdNqCPpLlA1";
	
	private static String key = "cdHcZx2rvD0btw";
	
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
		params.put("iccids",iccids.replaceAll(",", "_"));
		params.put("key", key);
		params.put("startDay", strDate);
		params.put("endDay", endDate);
		String result3 = sendPost(baseUrl, mapToFormData(params));
		JSONObject json = (JSONObject) JSONObject.parse(result3);
		if("1".equals(json.get("status").toString())){
			returnMap.put("flag", "SUCCESS");
			returnMap.put("respCode", json.get("status"));
			returnMap.put("respDesc", "SUCCESS");
			System.out.println(json.get("status")+"_iot_success");
			JSONObject obj = (JSONObject) JSONObject.parse(json.get("info").toString());
			List<Map<String, Object>> flows = new ArrayList<Map<String, Object>>();
			Map<String, Object> flow= null;
			String[] iccidlist= iccids.split(",");
			for(int i=0;i<iccidlist.length;i++){
				if(obj.get(iccidlist[i]) != null && obj.getDouble(iccidlist[i])>0){
					flow = new HashMap<String, Object>();
					flow.put("iccid", iccidlist[i]);
					flow.put("useFlow", obj.get(iccidlist[i]));
					flows.add(flow);
				}
			}
			returnMap.put("list", flows);
		}else{
			returnMap.put("flag", "FAIL");
			returnMap.put("respCode", json.get("status"));
			returnMap.put("respDesc", "FAIL");
			System.out.println(json.get("status")+"_iot_fail");
		}
		return returnMap;
	}
	
//	public static void main(String[] args){
//		System.out.println(getGpsData("89860617030079217942,898602B4071770232311", "20171001", "20171030"));
//	}
	
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
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	//在你发起Http请求之前设置一下属性  
        	System.setProperty("http.proxyHost", "172.20.6.88");  
        	System.setProperty("http.proxyPort", "3128");
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content‐Type", "application/x‐www‐form‐urlencoded");
            
            conn.setRequestProperty("key", header_key);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
        	System.getProperties().remove("http.proxyHost");  
            System.getProperties().remove("http.proxyPort");  
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }   
}


