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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 济宁优易信息科技有限公司
 * 最多200个
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsAcquistJNYYUtil 此处填写需要参考的类
 * @version 2017年11月12日 上午6:59:06 
 * @author yuze.luo
 */
public class GpsAcquistJNYYUtil {
	
	private static String baseUrl = "http://ws.wosoiot.com:8080/simmanager/interfaces/getGprs.action";
	
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
		String result3 = sendPost(baseUrl, mapToFormData(params));
		//String result3 = "{'msg':'正确','result':[{'iccid':'898602B42216C0473980','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473981','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473982','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473983','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473984','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473985','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473986','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473987','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473988','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473989','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473990','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473991','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473992','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473993','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473994','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473995','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473996','dosage_of_gprs':'1.80'},{'iccid':'898602B42216C0473997','dosage_of_gprs':'2.82'},{'iccid':'898602B42216C0473998','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0473999','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474000','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474001','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474002','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474003','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474004','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474005','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474006','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474007','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474008','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474009','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474010','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474011','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474012','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474013','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474014','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474015','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474016','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474017','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474018','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474019','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474020','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474021','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474022','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474023','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474024','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474025','dosage_of_gprs':'0.43'},{'iccid':'898602B42216C0474026','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474027','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474028','dosage_of_gprs':'0.40'},{'iccid':'898602B42216C0474029','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474030','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474031','dosage_of_gprs':'0.02'},{'iccid':'898602B42216C0474032','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474033','dosage_of_gprs':'0.02'},{'iccid':'898602B42216C0474034','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474035','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474036','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474037','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474038','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474039','dosage_of_gprs':'0.43'},{'iccid':'898602B42216C0474040','dosage_of_gprs':'1.98'},{'iccid':'898602B42216C0474041','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474042','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474043','dosage_of_gprs':'0.10'},{'iccid':'898602B42216C0474044','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474045','dosage_of_gprs':'0.12'},{'iccid':'898602B42216C0474046','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474047','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474048','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474049','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474050','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474051','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474052','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474053','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474054','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474055','dosage_of_gprs':'0.05'},{'iccid':'898602B42216C0474056','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474057','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474058','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474059','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474060','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474061','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474062','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474063','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474064','dosage_of_gprs':'2.02'},{'iccid':'898602B42216C0474065','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474066','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474067','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474068','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474069','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474070','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474071','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474072','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474073','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474074','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474075','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474076','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474077','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474078','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474079','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474080','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474081','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474082','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474083','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474084','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474085','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474086','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474087','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474088','dosage_of_gprs':'0.18'},{'iccid':'898602B42216C0474089','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474090','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474091','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474092','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474093','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474094','dosage_of_gprs':'2.10'},{'iccid':'898602B42216C0474095','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474096','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474097','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474098','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474099','dosage_of_gprs':'2.81'},{'iccid':'898602B42216C0474100','dosage_of_gprs':'0.02'},{'iccid':'898602B42216C0474101','dosage_of_gprs':'6.32'},{'iccid':'898602B42216C0474102','dosage_of_gprs':'0.12'},{'iccid':'898602B42216C0474103','dosage_of_gprs':'0.10'},{'iccid':'898602B42216C0474104','dosage_of_gprs':'0.34'},{'iccid':'898602B42216C0474105','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474106','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474107','dosage_of_gprs':'0.07'},{'iccid':'898602B42216C0474108','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474109','dosage_of_gprs':'0.54'},{'iccid':'898602B42216C0474110','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474111','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474112','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474113','dosage_of_gprs':'0.02'},{'iccid':'898602B42216C0474114','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474115','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474116','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474117','dosage_of_gprs':'2.92'},{'iccid':'898602B42216C0474118','dosage_of_gprs':'0.04'},{'iccid':'898602B42216C0474119','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474120','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474121','dosage_of_gprs':'0.04'},{'iccid':'898602B42216C0474122','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474123','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474124','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474125','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474126','dosage_of_gprs':'0.11'},{'iccid':'898602B42216C0474127','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474128','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474129','dosage_of_gprs':'0.05'},{'iccid':'898602B42216C0474130','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474131','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474132','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474133','dosage_of_gprs':'0.02'},{'iccid':'898602B42216C0474134','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474135','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474136','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474137','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474138','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474139','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474140','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474141','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474142','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474143','dosage_of_gprs':'0.08'},{'iccid':'898602B42216C0474144','dosage_of_gprs':'0.59'},{'iccid':'898602B42216C0474145','dosage_of_gprs':'0.52'},{'iccid':'898602B42216C0474146','dosage_of_gprs':'0.98'},{'iccid':'898602B42216C0474147','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474148','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474149','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474150','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474151','dosage_of_gprs':'0.04'},{'iccid':'898602B42216C0474152','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474153','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474154','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474155','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474156','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474157','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474158','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474159','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474160','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474161','dosage_of_gprs':'0.28'},{'iccid':'898602B42216C0474162','dosage_of_gprs':'0.56'},{'iccid':'898602B42216C0474163','dosage_of_gprs':'0.22'},{'iccid':'898602B42216C0474164','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474165','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474166','dosage_of_gprs':'0.01'},{'iccid':'898602B42216C0474167','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474168','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474169','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474170','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474171','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474172','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474173','dosage_of_gprs':'1.49'},{'iccid':'898602B42216C0474174','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474175','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474176','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474177','dosage_of_gprs':'0.74'},{'iccid':'898602B42216C0474178','dosage_of_gprs':'0.00'},{'iccid':'898602B42216C0474179','dosage_of_gprs':'0.00'}],'status':'1'}";
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
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
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
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
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


