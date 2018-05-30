package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.common.utils.GpsAcquistBJYYUtil;
import com.pay.coeus.common.utils.GpsAcquistDHSTUtil;
import com.pay.coeus.common.utils.GpsAcquistIOTUtil;
import com.pay.coeus.common.utils.GpsAcquistJNYYUtil;
import com.pay.coeus.core.dao.boss.PosRequestMapper;
import com.pay.coeus.core.dao.coeus.GpsLossCustomerMapper;
import com.pay.coeus.core.dao.coeus.GpsMonitorBatchMapper;
import com.pay.coeus.core.dao.coeus.GpsMonitorCustomerMapper;
import com.pay.coeus.core.dao.coeus.GpsMonitorDataMapper;
import com.pay.coeus.core.dao.coeus.GpsMonitorSupplierMapper;
import com.pay.coeus.core.service.GpsMonitorCustomerService;
import com.pay.coeus.model.entity.GpsMonitorBatch;
import com.pay.coeus.model.entity.GpsMonitorSupplier;

@Service("gpsMonitorCustomerService")
public class GpsMonitoerCustomerServiceImpl implements GpsMonitorCustomerService {

	private Logger logger = LoggerFactory.getLogger(GpsMonitoerCustomerServiceImpl.class);
	
	@Resource
	private PosRequestMapper posRequestMapper;
	
	@Resource
	private GpsMonitorCustomerMapper gpsMonitorCustomerMapper;
	@Resource
	private GpsMonitorDataMapper gpsMonitorDataMapper;
	@Resource
	private GpsMonitorBatchMapper gpsMonitorBatchMapper;
	@Resource
	private GpsLossCustomerMapper gpsLossCustomerMapper;
	@Resource
	private GpsMonitorSupplierMapper gpsMonitorSupplierMapper;
//	@Override
//	public void monitorCustomerInit() {
//		// 获取待监控商编  1、配置支持供货商 2插入排重！3开始时间结束时间
//		// 支持的sim卡监控的对象
//		String suppliers = RedisUtil.getValue(RedisKeyConstants.SIM_MONITOR_SUPPLIERS);
//		String strNum = RedisUtil.getValue(RedisKeyConstants.SIM_MONITOR_STARTNUM);
//		String endNum = RedisUtil.getValue(RedisKeyConstants.SIM_MONITOR_ENDNUM);
//		logger.info("--suppliers--:{}", suppliers);
//		String strDate = DateUtils.getFixedDays(DateUtils.getDate(), "yyyy-MM-dd", 0-Integer.valueOf(strNum)) +" 00:00:00";
//		String endDate = DateUtils.getFixedDays(DateUtils.getDate(), "yyyy-MM-dd", 0-Integer.valueOf(endNum)) +" 00:00:00";
//		logger.info("--strDate--:{},--endDate--:{}", strDate, endDate);
//		if(StringUtils.isNotBlank(suppliers)){
//			String[] sups = suppliers.split(",");
//			for(int k=0;k<sups.length;k++){
//				try {
//					logger.info("--suppliers--:{},start", sups[k]);
//					List<Map<String, String>> customers = posRequestMapper.getActiveCustomer(sups[k], strDate, endDate);
//					if(customers != null && customers.size()>0){
//						logger.info("customers.size():"+customers.size());
//						for(int i=0;i<customers.size();i++){
//							//不存在则插入
//							if(customers.get(i).get("CUSTOMER_NO") != null && gpsMonitorCustomerMapper.getByCustomerNo(customers.get(i).get("CUSTOMER_NO")) == null){
//								gpsMonitorCustomerMapper.insert(toCustomer(customers.get(i)));
//							}
//						}
//					}else{
//						logger.info("customers.size():0");
//					}
//					logger.info("--suppliers--:{},over", sups[k]);
//				} catch (Exception e) {
//					// TODO 给出通知
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
//	private GpsMonitorCustomer toCustomer(Map<String, String> map){
//		GpsMonitorCustomer cust= new GpsMonitorCustomer();
//		cust.setCustomerNo(map.get("CUSTOMER_NO"));
//		cust.setIccid(map.get("SIM_CARD"));
//		cust.setSimSupplier(map.get("SIM_SUPPLIER"));
//		return cust;
//	}
	
	@Override
	public void gpsDataAcquisition() {
		// 获取待监控商编  1、配置支持供货商 2插入排重！3开始时间结束时间
		// 支持的sim卡监控的对象
		List<GpsMonitorSupplier> suppliers = gpsMonitorSupplierMapper.getValidList();
		logger.info("gpsDataAcquisition suppliers:{}", suppliers == null? 0:suppliers.size());
		String strDate = DateUtils.getFixedDays(DateUtils.getDate("yyyyMMdd"), "yyyyMMdd", -5);
		String endDate = DateUtils.getFixedDays(DateUtils.getDate("yyyyMMdd"), "yyyyMMdd", -1);
		logger.info("gpsDataAcquisition strDate:{},endDate:{}", strDate, endDate);
		GpsMonitorBatch batch = new GpsMonitorBatch();
		batch.setDateScope(strDate+"-"+endDate);
		gpsMonitorBatchMapper.insert(batch);
		if(suppliers != null && suppliers.size() > 0){
			for(int k=0;k<suppliers.size();k++){
				try {
					logger.info("gpsDataAcquisition suppliers:{},start", suppliers.get(k).getSupplier());
					List<String> customers = gpsMonitorCustomerMapper.getBySimSupplier(suppliers.get(k).getSupplier());
					if(customers != null && customers.size()>0){
						logger.info("gpsDataAcquisition customers.size():"+customers.size());
						StringBuffer iccids = new StringBuffer();
						for(int i=0;i<customers.size();i++){
							if(iccids.toString().indexOf(customers.get(i)) == -1){
								iccids.append(customers.get(i) + ",");
								if((i+1)%100 == 0){
									dealAcquestData(batch.getId(), suppliers.get(k).getSupplier(), iccids.toString().substring(0, iccids.toString().length()-1), strDate, endDate);
									iccids = new StringBuffer();
								}
							}
						}
						if(iccids.length()>0){
							dealAcquestData(batch.getId(), suppliers.get(k).getSupplier(), iccids.toString().substring(0, iccids.toString().length()-1), strDate, endDate);
						}
					}else{
						logger.info("gpsDataAcquisition customers.size():0");
					}
					logger.info("gpsDataAcquisition suppliers:{},over", suppliers.get(k).getSupplier());
				} catch (Exception e) {
					logger.error("gpsDataAcquisition 异常", e);
				}
			}
		}
	}
	
	/**
	 * 获取sim卡数据并入库。
	 * @Description  一句话描述方法用法
	 * @param simSupplier
	 * @param iccids
	 * @param strDate
	 * @param endDate
	 * @see 需要参考的类或方法
	 */
	private void dealAcquestData(Long batchId, String simSupplier, String iccids, String strDate, String endDate){
		Map<String,Object> a = null;
		if("DHST".equals(simSupplier)){
			a = GpsAcquistDHSTUtil.getGpsData(iccids, strDate, endDate, "KY");
		}else if("DHST_O".equals(simSupplier)){
			a = GpsAcquistDHSTUtil.getGpsData(iccids, strDate, endDate, "JK");
		}else if("JNYY".equals(simSupplier)){
			a = GpsAcquistJNYYUtil.getGpsData(iccids, strDate, endDate);
		}else if("HXXT".equals(simSupplier)){
			a = GpsAcquistIOTUtil.getGpsData(iccids, strDate, endDate);
		}else if("BJYY".equals(simSupplier)){
			a = GpsAcquistBJYYUtil.getGpsData(iccids, strDate, endDate);
		}
		if(a != null && a.get("flag") != null && "SUCCESS".equals(a.get("flag").toString())){
			if(a.get("list") != null){
				List<Map<String, String>> list = (List<Map<String, String>>) a.get("list");
				if(list != null && list.size()>0){
					for(int k=0;k<list.size();k++){
						list.get(k).put("batchId", batchId+"");
					}
					gpsMonitorDataMapper.insertBatch(list);
				}
			}
		}
		
	}
	
//	/**
//	 * 流失处理
//	 */
//	@Override
//	public void monitorCustomerAnalysis() {
//		int count = gpsMonitorCustomerMapper.getTotalCount();
//		logger.info("--monitorCustomerAnalysis,totalcount--:{}", count);
//		if(count>0){
//			// 分页处理流失情况
//			int onepagecount = 1000;
//			for(int nowpage = 0;nowpage<(count/onepagecount+1);nowpage++){
//				List<GpsMonitorCustomer> list = gpsMonitorCustomerMapper.getByPage(nowpage*onepagecount, onepagecount);
//				if(list == null || list.size() == 0){
//					break;
//				}
//				// 挨个处理是否流失
//				for(int i=0;i<list.size();i++){
//					//先判断是否有交互。
//					int requestCount = posRequestMapper.getCountByCustomerNo(list.get(i).getCustomerNo(), DateUtils.formatDate(list.get(i).getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
//					// 无交易在判断是否有流量。
//					if(requestCount == 0){
//						int gpsCount = gpsMonitorDataMapper.getCountByIccid(list.get(i).getIccid(), list.get(i).getCreateTime());
//						// 有流量，即为流失,添加
//						if(gpsCount>0){
//							if(gpsLossCustomerMapper.getByCustomerNo(list.get(i).getCustomerNo()) == null){
//								gpsLossCustomerMapper.insertCustomer(list.get(i).getCustomerNo());
//							}
//						}
//					}
//				}
//				
//			}
//		}
//		logger.info("--monitorCustomerAnalysis,over");
//	}

}
