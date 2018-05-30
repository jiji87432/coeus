package com.pay.coeus.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GpsAcquistDHSTUtil {
	
	private static String baseUrl = "http://api.iot.dahantop.cn/api/batchHistoryScopeQuery";
	
	private static String appid_ky = "kayouzf";
	private static String appkey_ky = "66a9a2f380394b8cb4ebfc21c4903fb4";
	
	private static String appid_jk = "shjika";
	private static String appkey_jk = "3b73af83c60e43628c51bdd86334545b";
	
	/**
	 * 获取流量信息（最支持100条，时间段31天以内）
	 * @Description  一句话描述方法用法
	 * @param iccidList 逗号分开的iccid
	 * @param strDate 开始时间（20171001）
	 * @param endDate 结束时间（20171031）
	 * @param source LF、KY
	 * @return flag(String) ：SUCCESS/FAIL
	 * 			respCode(String)：00成功其他失败
	 * 			respDesc(String)：描述
	 * 			list(String)	：iccid	   (String):
	 * 					  		  useFlow (String):使用流量
	 * @see 需要参考的类或方法
	 */
	public static Map<String, Object> getGpsData(String iccids, String strDate, String endDate, String source){
		if(iccids == null || iccids.length()== 0){
			return null;
		}
		// 返回对象
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 传参对象
		Map<String, String> params = new TreeMap<String, String>();
		params.put("iccids",iccids);
		if("KY".equals(source)){
			params.put("appid", appid_ky);
		}else{
			params.put("appid", appid_jk);
		}
		params.put("dateScope", strDate+"-"+endDate);
		long timestamp = System.currentTimeMillis()/1000;
		params.put("timestamp", timestamp+"");
		params.put("sign", getSign(params, source));
		String result3 = sendPost(baseUrl, mapToFormData(params));
		//String result3 = "{'respCode':'00','respDesc':'请求成功','result':[{'cardStatus':'1','iccid':'898602B4151670034124','imsi':'460040458314114','usedFlow':'100334.430'},{'cardStatus':'1','iccid':'898602B7151770261312','imsi':'460040765101312','usedFlow':'100301.290'},{'cardStatus':'3','iccid':'898602B8191651009666','imsi':''},{'cardStatus':'1','iccid':'898602B7161700467435','imsi':'460040736907435','usedFlow':'0.290'},{'cardStatus':'1','iccid':'898602B7161700468066','imsi':'460040736908066','usedFlow':'0.040'},{'cardStatus':'3','iccid':'898607B6191792675829'},{'cardStatus':'1','iccid':'898602B7151750000888','imsi':'460040762300888','usedFlow':'198603.700'},{'cardStatus':'3','iccid':'898602B8191651013607','imsi':''},{'cardStatus':'1','iccid':'898602B4151670046271','imsi':'460040458416261','usedFlow':'100324.370'},{'cardStatus':'3','iccid':'898607B6191792770511'},{'cardStatus':'1','iccid':'898602B6161770092283','imsi':'460040665009283','usedFlow':'0.320'},{'cardStatus':'1','iccid':'898602B4151670040978','imsi':'460040458410968','usedFlow':'100376.940'},{'cardStatus':'3','iccid':'898607B6191792676698'},{'cardStatus':'1','iccid':'898602B6161770097696','imsi':'460040665104696','usedFlow':'0.790'},{'cardStatus':'1','iccid':'898602B6161770097221','imsi':'460040665104221','usedFlow':'0.270'},{'cardStatus':'1','iccid':'898602B4151670044981','imsi':'460040458414971','usedFlow':'100337.720'},{'cardStatus':'1','iccid':'898602B4151670034133','imsi':'460040458314123','usedFlow':'100333.610'},{'cardStatus':'1','iccid':'898602B4151670042893','imsi':'460040458412883','usedFlow':'100366.520'},{'cardStatus':'1','iccid':'898602B4151670034080','imsi':'460040458314070','usedFlow':'100333.810'},{'cardStatus':'1','iccid':'898602B6161770100480','imsi':'460040665107480','usedFlow':'3.120'},{'cardStatus':'3','iccid':'898607B6191792691235'},{'cardStatus':'1','iccid':'898602B6161770100283','imsi':'460040665107283','usedFlow':'0.590'},{'cardStatus':'1','iccid':'898602B6161770097926','imsi':'460040665104926','usedFlow':'0.820'},{'cardStatus':'1','iccid':'898602B6161770092200','imsi':'460040665009200','usedFlow':'0.310'},{'cardStatus':'1','iccid':'898602B6161770100494','imsi':'460040665107494','usedFlow':'1.110'},{'cardStatus':'3','iccid':'898607B6191792770674'},{'cardStatus':'3','iccid':'898607B6191792765460'},{'cardStatus':'1','iccid':'898602B4151670051768','imsi':'460040458511758','usedFlow':'100253.930'},{'cardStatus':'3','iccid':'898607B6191792691531'},{'cardStatus':'3','iccid':'898602B8191651012915','imsi':''},{'cardStatus':'1','iccid':'898602B4151670056996','imsi':'460040458516986','usedFlow':'198605.950'},{'cardStatus':'1','iccid':'898602B6161770097190','imsi':'460040665104190','usedFlow':'0.100'},{'cardStatus':'1','iccid':'898602B6161770099817','imsi':'460040665106817','usedFlow':'0.570'},{'cardStatus':'1','iccid':'898602B4151670059145','imsi':'460040458519135','usedFlow':'198610.830'},{'cardStatus':'1','iccid':'898602B4151670059547','imsi':'460040458519537','usedFlow':'198609.830'},{'cardStatus':'1','iccid':'898602B7151770261607','imsi':'460040765101607','usedFlow':'100308.120'},{'cardStatus':'1','iccid':'898602B4151670039184','imsi':'460040458319174','usedFlow':'100366.870'},{'cardStatus':'3','iccid':'898607B6191792763576'},{'cardStatus':'1','iccid':'898602B7151750000818','imsi':'460040762300818','usedFlow':'198604.640'},{'cardStatus':'1','iccid':'898602B6161770100167','imsi':'460040665107167','usedFlow':'0.640'},{'cardStatus':'1','iccid':'898602B7161700460344','imsi':'460040736900344','usedFlow':'0.190'},{'cardStatus':'3','iccid':'898607B6191792770529'},{'cardStatus':'1','iccid':'898602B4151670039953','imsi':'460040458319943','usedFlow':'100372.860'},{'cardStatus':'3','iccid':'898607B1191751102540','imsi':''},{'cardStatus':'1','iccid':'898602B6161770100737','imsi':'460040665107737','usedFlow':'0.600'},{'cardStatus':'3','iccid':'898607B1191751103252','imsi':''},{'cardStatus':'1','iccid':'898602B7151750001511','imsi':'460040762301511','usedFlow':'198602.000'},{'cardStatus':'1','iccid':'898602B7161700463971','imsi':'460040736903971','usedFlow':'0.260'},{'cardStatus':'1','iccid':'898602B6161770098224','imsi':'460040665105224','usedFlow':'0.550'},{'cardStatus':'1','iccid':'898602B4151670053663','imsi':'460040458513653','usedFlow':'93894.740'},{'cardStatus':'3','iccid':'898602B8191651012327','imsi':''},{'cardStatus':'3','iccid':'898607B1191751102790','imsi':''},{'cardStatus':'1','iccid':'898602B6161770093759','imsi':'460040665100759','usedFlow':'0.160'},{'cardStatus':'1','iccid':'898602B7161700467541','imsi':'460040736907541','usedFlow':'0.440'},{'cardStatus':'1','iccid':'898602B4151670045802','imsi':'460040458415792','usedFlow':'100328.260'},{'cardStatus':'1','iccid':'898602B4151670038737','imsi':'460040458318727','usedFlow':'100366.860'},{'cardStatus':'3','iccid':'898607B6191792768365'},{'cardStatus':'3','iccid':'898607B6191792676363'},{'cardStatus':'1','iccid':'898602B6161770097031','imsi':'460040665104031','usedFlow':'0.100'},{'cardStatus':'1','iccid':'898602B7161700468703','imsi':'460040736908703','usedFlow':'0.420'},{'cardStatus':'1','iccid':'898602B4151670044176','imsi':'460040458414166','usedFlow':'100349.090'},{'cardStatus':'1','iccid':'898602B4151670044177','imsi':'460040458414167','usedFlow':'100348.820'},{'cardStatus':'1','iccid':'898602B4151670047046','imsi':'460040458417036','usedFlow':'100415.400'},{'cardStatus':'1','iccid':'898602B4151670041518','imsi':'460040458411508','usedFlow':'100376.250'},{'cardStatus':'3','iccid':'898607B6191792765026'},{'cardStatus':'1','iccid':'898602B7151750002908','imsi':'460040762302908','usedFlow':'198603.240'},{'cardStatus':'3','iccid':'898607B6191792764164'},{'cardStatus':'1','iccid':'898602B7161700463871','imsi':'460040736903871','usedFlow':'0.320'},{'cardStatus':'1','iccid':'898602B7161700466813','imsi':'460040736906813','usedFlow':'0.320'},{'cardStatus':'3','iccid':'898607B6191792765014'},{'cardStatus':'1','iccid':'898602B6161770100399','imsi':'460040665107399','usedFlow':'0.450'},{'cardStatus':'1','iccid':'898602B6161770099345','imsi':'460040665106345','usedFlow':'0.160'},{'cardStatus':'1','iccid':'898602B4151670059313','imsi':'460040458519303','usedFlow':'198610.200'},{'cardStatus':'1','iccid':'898602B7161700464048','imsi':'460040736904048','usedFlow':'0.370'},{'cardStatus':'1','iccid':'898602B7161700466519','imsi':'460040736906519','usedFlow':'0.240'},{'cardStatus':'1','iccid':'898602B7161700465629','imsi':'460040736905629','usedFlow':'0.290'},{'cardStatus':'1','iccid':'898602B6161770095104','imsi':'460040665102104','usedFlow':'0.100'},{'cardStatus':'3','iccid':'898607B6191792769429'},{'cardStatus':'3','iccid':'898607B6191792768520'},{'cardStatus':'1','iccid':'898602B4151670042010','imsi':'460040458412000','usedFlow':'100376.260'},{'cardStatus':'1','iccid':'898602B6161770100886','imsi':'460040665107886','usedFlow':'0.330'},{'cardStatus':'1','iccid':'898602B7161700463767','imsi':'460040736903767','usedFlow':'0.260'},{'cardStatus':'3','iccid':'898607B6191792676879'},{'cardStatus':'3','iccid':'898607B6191792763345'},{'cardStatus':'3','iccid':'898607B6191792763248'},{'cardStatus':'3','iccid':'898607B6191792765080'},{'cardStatus':'3','iccid':'898607B6191792770432'},{'cardStatus':'1','iccid':'898602B4151670056444','imsi':'460040458516434','usedFlow':'198606.250'},{'cardStatus':'1','iccid':'898602B4151670038858','imsi':'460040458318848','usedFlow':'100367.430'},{'cardStatus':'1','iccid':'898602B7151750001068','imsi':'460040762301068','usedFlow':'198604.950'},{'cardStatus':'3','iccid':'898607B6191792764289'},{'cardStatus':'1','iccid':'898602B7161700469434','imsi':'460040736909434','usedFlow':'0.400'},{'cardStatus':'1','iccid':'898602B6161770092439','imsi':'460040665009439','usedFlow':'0.650'},{'cardStatus':'1','iccid':'898602B4151670038841','imsi':'460040458318831','usedFlow':'100368.160'},{'cardStatus':'3','iccid':'898607B6191792682534'},{'cardStatus':'3','iccid':'898607B6191792764864'},{'cardStatus':'1','iccid':'898602B4151670047727','imsi':'460040458417717','usedFlow':'100303.760'},{'cardStatus':'1','iccid':'898602B6161770099329','imsi':'460040665106329','usedFlow':'0.260'},{'cardStatus':'1','iccid':'898602B6161770100600','imsi':'460040665107600','usedFlow':'0.380'},{'cardStatus':'1','iccid':'898602B7151770261474','imsi':'460040765101474','usedFlow':'100306.350'}]}";
		JSONObject json = (JSONObject) JSONObject.parse(result3);
		if("00".equals(json.get("respCode").toString())){
			returnMap.put("flag", "SUCCESS");
			returnMap.put("respCode", json.get("respCode"));
			returnMap.put("respDesc", json.get("respDesc"));
			System.out.println(json.get("respCode")+","+json.get("respDesc"));
			JSONArray list = (JSONArray) JSONArray.parse(json.get("result").toString());
			JSONObject obj = null;
			List<Map<String, Object>> flows = new ArrayList<Map<String, Object>>();
			Map<String, Object> flow= null;
			for(int i=0;i<list.size();i++){
				obj = (JSONObject) JSONObject.parse(list.get(i).toString());
				// 卡状态异常的不做处理。// 卡状态 0 未知 1 在用 2 停机 3 待激活 4 销户
				if("1".equals(obj.get("cardStatus"))){
					if(obj.get("usedFlow") != null && !"".equals(obj.get("usedFlow").toString()) && Double.valueOf(obj.get("usedFlow").toString()) > 0){
						flow = new HashMap<String, Object>();
						flow.put("iccid", obj.get("iccid"));
						flow.put("useFlow", obj.get("usedFlow"));
						flows.add(flow);
					}
				}
			}
			returnMap.put("list", flows);
		}else{
			returnMap.put("flag", "FAIL");
			returnMap.put("respCode", json.get("respCode"));
			returnMap.put("respDesc", json.get("respDesc"));
			// 查询失败，给出警告
			System.out.println(json.get("respCode")+","+json.get("respDesc"));
		}
		
		
		return returnMap;
	}
	
	/**
	 * 根据参数获取签名
	 * 签名算法来自api
	 * @param params
	 * @return
	 */
	public static String getSign(Map<String, String> params, String source){
		if(params == null || params.size() == 0){
			return null;
		}
		Map<String, String> resultMap = sortMapByKey(params);  //按Key进行排序
		StringBuffer str = new StringBuffer();
		if("KY".equals(source)){
			str.append(appkey_ky);
		}else{
			str.append(appkey_jk);
		}
	    for (Map.Entry<String, String> entry : resultMap.entrySet()) {
	        str.append(entry.getKey());
	        str.append(entry.getValue());
	    }
	    if("KY".equals(source)){
			str.append(appkey_ky);
		}else{
			str.append(appkey_jk);
		}
	    String sign = DigestUtils.md5Hex(str.toString());
		return sign;
	}
	

	/**
     * 使用 Map按key进行排序
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

class MapKeyComparator implements Comparator<String>{
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}

