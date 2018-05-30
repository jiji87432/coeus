package com.pay.coeus.core.quartz.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pay.coeus.core.service.TrCallRecordService;
import com.pay.coeus.core.util.HttpClientUtil;
import com.pay.common.util.DateUtil;
import com.pay.common.util.DigestUtil;
import com.pay.common.util.PropertyUtil;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

@JobHander(value = "trCallRecordHandler")
@Component
public class TrCallRecordHandler extends BaseJobHandler{

	private Logger logger = LoggerFactory.getLogger(TrCallRecordHandler.class);
	@Resource
	private TrCallRecordService trCallRecordService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		PropertyUtil propertyUtil = PropertyUtil.getInstance("system");
		String url = propertyUtil.getProperty("tr.httpclient.url");
		String method=propertyUtil.getProperty("tr.call.record.method");
		String detailMethod=propertyUtil.getProperty("tr.call.record.detail.method");
		String pw=propertyUtil.getProperty("tr.admin.pwd");
		Map<String,Object> map=new HashMap<>();
		try {
			map.put("enterpriseId", "3005192");
			map.put("userName", "admin");
			String  seed = ((Math.random()*(9999-1000+1))+1000)+"";
			String pwd=DigestUtil.md5(pw+seed);
			map.put("seed", seed);
			map.put("pwd", pwd);
			map.put("mark", "2");
			map.put("start", "0");
			map.put("limit", "1000");
			String date=DateUtil.formatDate(DateUtil.addDayToDate(new Date(), -1),"yyyy-MM-dd");
			map.put("startTime", date+" 00:00:00");
			map.put("endTime", date+" 23:59:59");
			String result=	HttpClientUtil.getHttpPost(method, map, url);
//			String result=	GpsAcquistIOTUtil.sendPost(url+method, GpsAcquistIOTUtil.mapToFormData(map));
			List<Map<String,Object>> list=getReturnJsonForMap(result);
			if(list.size()==0){
				list=getReturnJsonForMap(result);
			}
			for(int i=0;i<list.size();i++){  
				Thread.sleep(1000);
				trCallRecordService.insertCallRecord(list.get(i));
	            String id=list.get(i).get("uniqueId").toString();
	            map.put("id", id);
	            String detailResult=HttpClientUtil.getHttpPost(detailMethod, map, url);
//	            String detailResult=GpsAcquistIOTUtil.sendPost(url+detailMethod, GpsAcquistIOTUtil.mapToFormData(map));
	            List<Map<String,Object>> detaillist=getReturnJsonForMapForDetail(detailResult);
	            for(Map<String,Object> m:detaillist){
	            	trCallRecordService.insertCallRecordDetail(m);
	            }
	            Thread.sleep(1000);
	        }  
		} catch (Exception e) {
			logger.error("TrCallRecordJob error:", e);
		}
		return ReturnT.SUCCESS;
	}
	
	public List<Map<String,Object>> getReturnJsonForMap(String jsn){
		
		List<Map<String,Object>> list=new ArrayList();
		JSONObject json=JSONObject.fromObject(jsn);
		if(json.getString("result").equals("success")){
			String msg=json.getString("msg");
			JSONObject jsonMsg=JSONObject.fromObject(msg);
			JSONArray jsonArray=JSONArray.fromObject(jsonMsg.get("data"));
			for(int i=0;i<jsonArray.size();i++){  
				Map<String,Object> re=JSONObject.fromObject(jsonArray.get(i));
	            list.add(re);
	        }  
		}else{
			logger.info("TrCallRecordJob data:{}", jsn);
		};
		return list;
	}
	public List<Map<String,Object>> getReturnJsonForMapForDetail(String jsn){
		
		List<Map<String,Object>> list=new ArrayList();
		JSONObject json=JSONObject.fromObject(jsn);
		if(json.getString("result").equals("success")){
			String msg=json.getString("msg");
			JSONArray jsonArray=JSONArray.fromObject(msg);
			for(int i=0;i<jsonArray.size();i++){  
				Map<String,Object> re=JSONObject.fromObject(jsonArray.get(i));
				list.add(re);
			}  
		}else{
			logger.info("TrCallRecordJob data:{}", jsn);
		};
		return list;
	}
	public static void main(String[] args) {
		//f54e283f944fa3c8a084b5bf7a55deda
//		System.out.println(DigestUtil.md5("f54e283f944fa3c8a084b5bf7a55deda1000"));
		String s=",id"
				+",optimistic"
				+",uniqueId"
				+",hotline"
				+",numberTrunk"
				+",customerNumber"
				+",customerProvince"
				+",customerCity"
				+",queueName"
				+",cno"
				+",clientNumber"
				+",status"
				+",mark"
				+",startTime"
				+",answerTime"
				+",bridgeTime"
				+",bridgeDuration"
				+",cost"
				+",totalDuration"
				+",recordFile"
				+",inCaseLib"
				+",score"
				+",callType"
				+",comment"
				+",ivrName"
				+",endReason"
				+",userField"
				+",sipCause"
				+",recordFileName"
				+",totalCost";
		String [] ss=s.split(",");
		for(String str:ss){
			
			System.out.println("<if test=\"param."+str+" != null \">");
			System.out.println(str+",");
			System.out.println("</if>");
		}
		System.out.println(" optimistic ");
		System.out.println(" ) VALUES ( ");
		for(String str:ss){
			
			System.out.println("<if test=\"param."+str+" != null \">");
			System.out.println("#{param."+str+"},");
			System.out.println("</if>");
		}
		System.out.println(" 0 ");
	}
}
