package com.pay.coeus.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pay.account.api.bean.AccountRequestV;
import com.pay.account.api.bean.request.Cost;
import com.pay.account.api.bean.request.Fare;
import com.pay.account.api.bean.response.AccountFareRecordQueryResponse;
import com.pay.account.api.bean.response.AccountFareResponse;
import com.pay.account.api.dubbo.AccountInterfaceVI;
import com.pay.account.api.dubbo.AccountQueryInterface;
import com.pay.account.api.enums.HandlerResult;
import com.pay.account.api.enums.ProcessStatus;
import com.pay.account.api.enums.UserRole;
import com.pay.app.remote.service.RemoteNewCustomerLoginService;
import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.common.utils.RedisUtil;
import com.pay.coeus.core.dao.boss.SimcardDeductFeeDetailMapper;
import com.pay.coeus.core.service.SimFeeDeductionService;
import com.pay.coeus.core.util.CamelUtil;
import com.pay.coeus.core.util.JsonArrayStringToJsonObject;
import com.pay.coeus.model.entity.SimcardDeductFeeDetail;

@Service
public class SimFeeDeductionServiceImpl implements SimFeeDeductionService {
	private Logger logger = LoggerFactory.getLogger(SimFeeDeductionServiceImpl.class);
	
	private static final String SIM_PRE_FROZEN = "MCH_SIM";
	private static final String V_PAY = "V_PAY_100001";
	
	private static double SIMCARD_DEDUCT_FEE = 30d;
	
	@Autowired
	private AccountInterfaceVI accountInterfaceVI;
	@Autowired
	private AccountQueryInterface accountQueryInterface;
	@Autowired
	private RemoteNewCustomerLoginService remoteNewCustomerLoginService;
	@Autowired
	private SimcardDeductFeeDetailMapper simcardDeductFeeDetailMapper;

	/**
	 * 查询出需要扣费的商户，增加到扣费记录
	 */
	@SuppressWarnings("unused")
	@Override
	public void createFreezeSimDeduct() {
		/**
		 * 查询在扣款范围内的商户与POS数据
		 */
		logger.info("createFreezeSimDeduct,step1,query simCardDeduct:" + DateUtils.formatDate(new Date()));
		List<Map<String, Object>> freezeList = simcardDeductFeeDetailMapper.findSimDeductFreezeList();
        logger.info("createFreezeSimDeduct,step2,query simCardDeduct:" + DateUtils.formatDate(new Date())+ "此次所查数据条数:"+freezeList.size());
        if (null != freezeList && freezeList.size() > 0) {
        	Map<String, Object> map = null;
        	String limitStr = RedisUtil.getValue("FREEZE_SIM_DEDUCT_LIMIT_NUM");
        	int limitNum = freezeList.size();
        	if(StringUtils.isNotBlank(limitStr)){
        		limitNum = Integer.valueOf(limitStr);
        		logger.info("createFreezeSimDeduct,限值："+limitNum);
        	}
			for (int k=0;k<freezeList.size();k++) {
				if(k>=limitNum){
					logger.info("createFreezeSimDeduct,超过限值："+limitNum+"！当前值："+k);
					break;
				}
				try {
					map = freezeList.get(k);
					String customerNo = String.valueOf(map.get("CUSTOMER_NO"));
					logger.info("createFreezeSimDeduct,customerNo"+customerNo);
					String transOrder = UUID.randomUUID().toString().substring(18);
					// 调用扣费接口
					// 智能POS机扣费金额：40元，传统POS扣费 30元
					double deductFee  = SIMCARD_DEDUCT_FEE;
					if(map.get("USE_TYPE") != null && "INTELLIGENCE".equals(String.valueOf(map.get("USE_TYPE")))){
						deductFee = 40d;
					}
					logger.info("createFreezeSimDeduct,map:"+map.toString());
					AccountFareResponse response = simDeductFare(customerNo, deductFee, transOrder);
					SimcardDeductFeeDetail scdfd = new SimcardDeductFeeDetail();
					scdfd.setFreezeNo(response.getFreezeNo());
					scdfd.setTransOrder(transOrder);
					scdfd.setCustomerNo(customerNo);
					scdfd.setRequestId(String.valueOf(map.get("ID")));
					scdfd.setPosSn(String.valueOf(map.get("POS_SN")));
					scdfd.setImsi(String.valueOf(map.get("IMSI")));
					scdfd.setPosCati(String.valueOf(map.get("POS_CATI")));
					scdfd.setCreateTime(new Date());
				    scdfd.setTotalFee(deductFee);
				    scdfd.setSimStatus("TRUE");
					if (null != response && null != response.getResult() && !HandlerResult.FAILED.name().equals(response.getResult().name())) {
						scdfd.setDeductStatus("PREFREEZE");
						logger.info("createFreezeSimDeduct,success!"+"customerNo:"+customerNo+" transOrder:"+transOrder+"返回的FreezeNo:"+response.getFreezeNo());
					} else {
						scdfd.setDeductStatus("WAITDEDUCT");
						if (null != response){
							logger.info("createFreezeSimDeductPreFreeze,failed! customerNo:" + customerNo + " resultMsg:" + response.getResultMsg());
						} else {
							logger.info("createFreezeSimDeduct,failed! response is null! customerNo:" + customerNo);
						}
					}
					simcardDeductFeeDetailMapper.saveSimCardDeductFeeDetail(scdfd);
				} catch (Exception e) {
					logger.error("createFreezeSimDeduct,error :" + String.valueOf(map.get("CUSTOMER_NO")), e);
					continue;
				}
			}
			logger.info("createFreezeSimDeduct,finish!" + freezeList.size());
		} else {
			logger.info("createFreezeSimDeduct,No customer pos data can be frozen!");
		}
	}
	
	@Override
	public void modifyWaitDeductToPreereeze() {
		List<SimcardDeductFeeDetail> waitList = simcardDeductFeeDetailMapper.findSimCardDeductFeeDetailListByStatus("WAITDEDUCT");
		if (null != waitList && waitList.size() > 0) {
			logger.info("modifyWaitDeductToPreereeze, WAITDEDUCT data size:"+waitList.size());
			for (SimcardDeductFeeDetail scdfd : waitList) {
				try {
					String transOrder = UUID.randomUUID().toString().substring(18);
					// 调用冻结接口
					AccountFareResponse response = simDeductFare(scdfd.getCustomerNo(), scdfd.getTotalFee(), transOrder);
					if (null != response && null != response.getResult() && !HandlerResult.FAILED.name().equals(response.getResult().name())) {
						scdfd.setFirstDeductFeeTime(new Date());
						scdfd.setFreezeNo(response.getFreezeNo());
						scdfd.setTransOrder(transOrder);
						scdfd.setDeductStatus("PREFREEZE");
						simcardDeductFeeDetailMapper.update(scdfd);
						logger.info("modifyWaitDeductToPreereeze,PreFreeze success!");
					} else if (null != response) {
						logger.warn("modifyWaitDeductToPreereeze,PreFreeze failed! customerNo:" + scdfd.getCustomerNo() + " resultMsg:" + response.getResultMsg());
					} else {
						logger.warn("modifyWaitDeductToPreereeze,PreFreeze failed! response is null! customerNo:" + scdfd.getCustomerNo());
					}
				} catch (Exception e) {
					logger.error("modifyWaitDeductToPreereezePreFreeze,error :" + scdfd.getCustomerNo(), e);
					continue;
				}
			}
			logger.info("modifyWaitDeductToPreereeze,Modify WAITDEDUCT to PREFREEZE finish!" + waitList.size());
		} else {
			logger.info("modifyWaitDeductToPreereeze,No WAITDEDUCT data can be modify to PREFREEZE!");
		}
	}
	
	/**
	 * 查看扣费结果
	 */
	@Override
	public void updateSimDeductByQueryFareStatus() {
		logger.info("updateSimDeductByQueryFareStatus,Begin to execute pay deduct...");
		// 查询
		try {
			// 查询未完全扣款陈功状态的数据
			List<SimcardDeductFeeDetail> payList = simcardDeductFeeDetailMapper.findSimCardDeductFeeDetailListByStatus("PREFREEZE");
			if (null == payList || payList.size() <= 0) {
				logger.info("updateSimDeductByQueryFareStatus,There is no need to pay data!");
				return;
			}
			// 查询扣款状态
			for (SimcardDeductFeeDetail scdfd : payList) {
				try {
					AccountFareRecordQueryResponse response = simDeductQueryFare(scdfd.getTransOrder());
					// 组合判断是否请款成功
					if (null != response && null != response.getResult() && HandlerResult.SUCCESS.equals(response.getResult())
							&& null != response.getFares() && response.getFares().size() > 0
							&& ProcessStatus.END.name().equals(response.getFares().get(0).getProcessStatus().name())) {
						// 扣款成功，发送扣款成功短信
						String phoneNo  = null;
						String result = remoteNewCustomerLoginService.getUserNameByCustomerNo("coeus", scdfd.getCustomerNo());
						if(result != null){
							JSONObject json = JsonArrayStringToJsonObject.getJsonObject(result);
							if("0001".equals(json.getString("code"))){
								phoneNo = json.getString("userName");
							};
						}
						if (StringUtils.isNotBlank(phoneNo)) {
							boolean messageflag = false;
							try {
								// 掌易通商户的不发送短信agentNo：8614294125，agentId：37447301
								// 2018-1-30确认掌易通没有POS，所以不用处理
                                logger.info("updateSimDeductByQueryFareStatus,sendmessage Phone:"+ phoneNo+" CustomerNo:"+scdfd.getCustomerNo());
                                messageflag = sendDeductFareMessage(phoneNo, scdfd.getCreateTime(), scdfd.getTotalFee());
							} catch (Exception e) {
								logger.error(e.toString(),e);
								logger.error("updateSimDeductByQueryFareStatus,sendMessage error,customerNo:" + scdfd.getCustomerNo() + ",phoneNo:" + phoneNo+" messageflag:"+messageflag);
							}
							if (messageflag) {
								logger.info("updateSimDeductByQueryFareStatus,sendMessage success,customerNo:" + scdfd.getCustomerNo() + ",phoneNo:" + phoneNo+" messageflag:"+messageflag);
								scdfd.setMessageStatus("Y");
								scdfd.setMessageTime(new Date());
							} else {
								logger.warn("updateSimDeductByQueryFareStatus,sendMessage fail,customerNo:" + scdfd.getCustomerNo() + ",phoneNo:" + phoneNo +" messageflag:"+messageflag);
								scdfd.setMessageStatus("N");
								scdfd.setMessageTime(new Date());
							}
						}else{
							logger.info("updateSimDeductByQueryFareStatus,phoneNo is null,customerNo:"+scdfd.getCustomerNo());
						}
						scdfd.setDeductStatus("SUCCESS");
						scdfd.setDeductFee(scdfd.getTotalFee());
						if (null != response.getFares().get(0).getLastModifyTime()) {
							scdfd.setDeductFeeSuccTime(response.getFares().get(0).getLastModifyTime());
						} else {
							logger.info("updateSimDeductByQueryFareStatus,deduct timie customer:" + scdfd.getCustomerNo() + ",use curr time!");
							scdfd.setDeductFeeSuccTime(response.getFinishTime());
						}
						simcardDeductFeeDetailMapper.update(scdfd);
						logger.info("updateSimDeductByQueryFareStatus，success," +"customerNo:"+scdfd.getCustomerNo()+" messageStatus:"+scdfd.getMessageStatus());
					} else if (null != response) {
						logger.warn("updateSimDeductByQueryFareStatus,failed! customerNo:" + scdfd.getCustomerNo() + ",resultMsg:" + response.getResultMsg());
					} else {
						logger.warn("updateSimDeductByQueryFareStatus,failed! response is null! customerNo:" + scdfd.getCustomerNo());
					}
				} catch (Exception e) {
					logger.error("updateSimDeductByQueryFareStatus,error :" + scdfd.getCustomerNo(), e);
					continue;
				}
			}
			logger.info("updateSimDeductByQueryFareStatus finish!" + payList.size());
		} catch (Exception e) {
			logger.error("updateSimDeductByQueryFareStatus error!", e);
		}
		logger.info("updateSimDeductByQueryFareStatus,End to execute pay deduct...");
	}

	/**
	 * 去扣费
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @param deductFee
	 * @param transOrder
	 * @return
	 * @see 需要参考的类或方法
	 */
	private AccountFareResponse simDeductFare(String customerNo, Double deductFee, String transOrder) {
		logger.info("simDeductFare,customerNo:" + customerNo + ",transOrder:" + transOrder);
		AccountRequestV arv = new AccountRequestV();
		arv.setSystemCode("POS_MIS");
		arv.setSystemFlowId(transOrder);
		arv.setRequestTime(new Date());
		arv.setOperator("SYSTEM");
		arv.setRemark("SIM扣费预冻结");
		Fare fare = new Fare();
		fare.setUserNo(customerNo);
		fare.setUserRole(UserRole.CUSTOMER);
		fare.setBussinessCode(SIM_PRE_FROZEN);
		fare.setTransDate(new Date());
		fare.setTransOrder(transOrder);
		fare.setAmount(deductFee);
		Cost cost = new Cost();
		cost.setFundChannelCode(V_PAY);
		cost.setClearingDate(new Date());
		fare.setCost(cost);
		arv.setTradeVoucher(fare);
		AccountFareResponse afr = accountInterfaceVI.fare(arv);
		logger.info("simDeductFare,return data：{" + afr + "}");
		return afr;
	}

	/**
	 * SIM卡扣费查询
	 *
	 */
	private AccountFareRecordQueryResponse simDeductQueryFare(String freezeNo) {
		logger.info("simDeductQueryFare1");
		if (StringUtils.isBlank(freezeNo)) {
			logger.warn("simDeductQueryFare freezeNo is null !");
			return null;
		}
		logger.info("freezeNo:" + freezeNo );
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transOrder", freezeNo);
		map.put("userRole", UserRole.CUSTOMER);
		AccountFareRecordQueryResponse accountFareRecordQueryResponse = accountQueryInterface.findAccountFareRecordBy(map);
		logger.info("simDeductQueryFare,return data：{" + accountFareRecordQueryResponse + "}");
		return accountFareRecordQueryResponse;
	}

	/**
	 * 发送扣费短信
	 *
	 * @param customerPhone
	 * @return
	 */
	private boolean sendDeductFareMessage(String customerPhone, Date createTime, double deductFee) {
		logger.info("sendDeductFareMessage,customerPhone:"+customerPhone+ "createTime:"+createTime);
		String amount = deductFee+"";
		String date1 = DateUtils.formatDate(new Date(), "yyyy年MM月");
//		String date2 = DateUtil.formatDate(DateUtil.addDayToDate(createTime, 365), "yyyy年MM月");
		String date2 = DateUtils.getFixedMonths(date1, "yyyy年MM月", 11);
		String message = "温馨提示：您"+ date1 +"-"+ date2 +"SIM卡流量费用，现已从您的刷卡款项中扣除，共计"
				+ amount +"元。为降低您的使用成本，SIM卡流量费用已由60元/年，调整为"
				+ amount +"元/年，请知悉。感谢您的使用，如有疑问，请致电4000202400";
		logger.info("sendDeductFareMessage,deduct message :" + message+ " customerPhone:"+customerPhone);
		return CamelUtil.sendSmsNotice(message, customerPhone, "SimCardFee");
	}
	
}
