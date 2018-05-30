package com.pay.coeus.core.mqListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pay.astrotrain.client.ATMessage;
import com.pay.astrotrain.client.MessageListener;
import com.pay.astrotrain.client.message.StringMessage;
import com.pay.coeus.common.utils.RedisUtil;
import com.pay.coeus.core.service.PosService;
import com.pay.coeus.core.util.ISO8583Utile;

@Component
public class PosRequestListener implements MessageListener{
	private static final Logger logger = LoggerFactory.getLogger(PosRequestListener.class);
	@Resource
	private PosService posService;
	
	private static final String cachePre="coeus-core-posId";
	private static final Integer cacheTime=60*60*24;
	@Override
	public void onMessage(ATMessage message) {
		try{
			if(message instanceof StringMessage){
//				StringMessage sm=(StringMessage) message;
//				String str=sm.getMsg();
//				if(str == null || str.equals("")){
//					return;
//				}
//				JSONObject json=JSONObject.fromObject(str);
//				if(json == null ){
//					return;
//				}
//				Map<String,Object> params=new HashMap<String,Object>();
//				List<Map<String,Object>> listParams=new ArrayList<Map<String,Object>>();
//				if(json.get("sendOnTerminalState")!= null){
//					
//					long posId=json.getLong("id");
//					String cache=RedisUtil.getValue(cachePre+posId);
//					if(cache !=null && !cache.equals("")){
//						logger.info("PosRequestListener post_id is repeat:"+cache);
//						return ;
//					}else{
//						RedisUtil.setKeyValue(cachePre+posId, posId+"",cacheTime );
//					}
//					List<byte[]> transStatusCodeList=new ArrayList<byte[]>();
//					String[] posRequest=new String[]{"customerNo","customerName","mcc","responseCode","posBatch","posCati","posSn","posEntryModeCode","shopNo","softVersion","posType","organization"};
//					for(int i=0;i<posRequest.length;i++){
//						if(json.get(posRequest[i]) != null){
//							params.put(posRequest[i], json.get(posRequest[i]));
//						}
//					}
//					params.put("posId", posId);
//					Date createTime=new Date(json.getLong("createTime"));
//					params.put("createTime", createTime);
//					String sendOnTerminalState=(String) json.get("sendOnTerminalState");
//					byte[] sendOnTerminalStateB =ISO8583Utile.hexStringToByte(sendOnTerminalState);
//					byte[]posStatusCode=new byte[5];
//					System.arraycopy(sendOnTerminalStateB, 0, posStatusCode, 0, 5);
//					String[] posStatus=new String[]{"electric","semaphore","failConnCount","allConnCount","failCardCount"};
//					for(int m=0;m<posStatusCode.length;m++){
//						String posStatusKey=posStatus[m];
//						byte[] bm=new byte[1];
//						System.arraycopy(posStatusCode, m, bm, 0, 1);
//						if(m<2){
//							params.put(posStatusKey, Integer.valueOf(ISO8583Utile.bcd2String(bm, false)));
//						}else{
//							params.put(posStatusKey, Integer.valueOf(ISO8583Utile.bytesToHexString(bm), 16));
//						}
//					}
//					String[] transStatus=new String[]{"transTime","transLong","returnCode","simCard","baseStation","transType","transIp"};
//					Integer[] transStatusCount=new Integer[]{5,1,2,3,5,1,1};
//					byte[] transStatusCode=new byte[sendOnTerminalStateB.length-5];
//					System.arraycopy(sendOnTerminalStateB, 5, transStatusCode, 0, sendOnTerminalStateB.length-5);
//					for(int n=0;n<transStatusCode.length;n=n+18){
//						byte[] tn=new byte[18];
//						System.arraycopy(transStatusCode, n, tn, 0, 18);
//						transStatusCodeList.add(tn);
//					}
//					for(byte[] transStatusCodeByte:transStatusCodeList){
//						Map<String,Object> params1=new HashMap<String,Object>();
//						for(int o=0,p=0;o<transStatusCodeByte.length;o=o+transStatusCount[p],p++){
//							byte[] tn=new byte[transStatusCount[p]];
//							System.arraycopy(transStatusCodeByte, o, tn, 0, transStatusCount[p]);
//							if(p==1 || p==6 ){
//								params1.put(transStatus[p], Integer.valueOf(ISO8583Utile.bytesToHexString(tn),16));
//							}else if(p==2){
//								params1.put(transStatus[p],ISO8583Utile.bytesToHexString(tn));
//							}else{
//								params1.put(transStatus[p],ISO8583Utile.bcd2String(tn, false));
//							}
//						}
//						params1.put("posId", posId);
//						listParams.add(params1);
//					}
//					posService.insertPosRecord(params, listParams);
//				}
			}
		}catch(Exception e){
			logger.error("MessageListener error"+message,e);
		}
	}
	public static void onMessage1(String str) {
		try{
				Map<String,Object> params=new HashMap<String,Object>();
				List<Map<String,Object>> listParams=new ArrayList<Map<String,Object>>();
				if(true){
					
					List<byte[]> transStatusCodeList=new ArrayList<byte[]>();
					String sendOnTerminalState=str;
					byte[] sendOnTerminalStateB =ISO8583Utile.hexStringToByte(sendOnTerminalState);
					byte[]posStatusCode=new byte[5];
					System.arraycopy(sendOnTerminalStateB, 0, posStatusCode, 0, 5);
					String[] posStatus=new String[]{"electric","semaphore","failConnCount","allConnCount","failCardCount"};
					for(int m=0;m<posStatusCode.length;m++){
						String posStatusKey=posStatus[m];
						byte[] bm=new byte[1];
						System.arraycopy(posStatusCode, m, bm, 0, 1);
//						System.out.println(ISO8583Utile.bytesToHexString(bm));
						if(m<2){
							System.out.println(ISO8583Utile.bcd2String(bm, false));
							params.put(posStatusKey, Integer.valueOf(ISO8583Utile.bcd2String(bm, false)));
						}else{
							params.put(posStatusKey, Integer.valueOf(ISO8583Utile.bytesToHexString(bm), 16));
						}
					}
					String[] transStatus=new String[]{"transTime","transLong","returnCode","simCard","baseStation","transType","transIp"};
					Integer[] transStatusCount=new Integer[]{5,1,2,3,5,1,1};
					byte[] transStatusCode=new byte[sendOnTerminalStateB.length-5];
					System.arraycopy(sendOnTerminalStateB, 5, transStatusCode, 0, sendOnTerminalStateB.length-5);
					for(int n=0;n<transStatusCode.length;n=n+18){
						byte[] tn=new byte[18];
						System.arraycopy(transStatusCode, n, tn, 0, 18);
						transStatusCodeList.add(tn);
					}
					for(byte[] transStatusCodeByte:transStatusCodeList){
						Map<String,Object> params1=new HashMap<String,Object>();
						for(int o=0,p=0;o<transStatusCodeByte.length;o=o+transStatusCount[p],p++){
							byte[] tn=new byte[transStatusCount[p]];
							System.arraycopy(transStatusCodeByte, o, tn, 0, transStatusCount[p]);
							System.out.println(transStatus[p]+":"+ISO8583Utile.bytesToHexString(tn));
							if(p==1 || p==6 ){
								params1.put(transStatus[p], Integer.valueOf(ISO8583Utile.bytesToHexString(tn),16));
							}else if(p==2){
								params1.put(transStatus[p],ISO8583Utile.bytesToHexString(tn));
							}else{
								params1.put(transStatus[p],ISO8583Utile.bcd2String(tn, false));
							}
						}
						listParams.add(params1);
					}
					for(Map<String,Object> m:listParams){
						Iterator<Entry<String,Object>> it=m.entrySet().iterator();
						while(it.hasNext()){
							Entry e=it.next();
							System.out.print(e.getKey()+":"+e.getValue()+",");
						}
						System.out.println();
					}
					Iterator<Entry<String,Object>> it=params.entrySet().iterator();
					while(it.hasNext()){
						Entry e=it.next();
						System.out.print(e.getKey()+":"+e.getValue()+",");
					}
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//public static void main(String[] args) {
//	String  s="5F310039011210153609003030FFFFFF0000000000020A1210193736003030FFFFFF0000000000020A1211085108003030FFFFFF0000000000010A1214150108003030FFFFFF0000000000010A1210142200003030FFFFFF0000000000020A";
//	onMessage1(s);
//}
}
