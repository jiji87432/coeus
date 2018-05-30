package com.pay.coeus.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pay.coeus.api.inner.bean.ReturnUtil;
import com.pay.coeus.api.inner.dubbo.CustomerActiveOrderFacade;

/**
 * 新入网商户数据统计
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactHistoryController 此处填写需要参考的类
 * @version 2017年11月20日 上午10:41:15 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/newOpen")
public class CustomerNewOpenController {
private static final Logger logger = LoggerFactory.getLogger(CustomerNewOpenController.class);
	
	@Autowired
	private CustomerActiveOrderFacade customerActiveOrderFacade;

	// 挑转到查询页
	@RequestMapping("/toDetail.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("newOpen/detail");
		return model;
	}
	
	/** 
	* 获得该月第一天 
	* @param year 
	* @param month 
	* @return 
	*/ 
	public static String getFirstDayOfMonth(int year,int month){  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置月份  
        cal.set(Calendar.MONTH, month-1);  
        //获取某月最小天数  
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数  
        cal.set(Calendar.DAY_OF_MONTH, firstDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String firstDayOfMonth = sdf.format(cal.getTime());  
        return firstDayOfMonth;  
    }  
	
	/** 
	* 获得该月最后一天 
	* @param year 
	* @param month 
	* @return 
	*/  
	public static String getLastDayOfMonth(int year,int month){  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置月份  
        cal.set(Calendar.MONTH, month-1);  
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String lastDayOfMonth = sdf.format(cal.getTime());  
        return lastDayOfMonth;  
    }  
	
	// 获取查询结果数据
	@RequestMapping("/getDetail.action")
	@ResponseBody
	public String getDetail(HttpServletRequest request,@RequestParam Map<String,String> param){
		try {
			logger.info("params {}", param);
			String strTime = "";
			String endTime = "";
			try {
				String dateParam = param.get("dateParam");
				int month = Integer.valueOf(dateParam.substring(dateParam.length()-2,dateParam.length()));
				int year = Integer.valueOf(dateParam.substring(0,dateParam.length()-2));
				strTime = getFirstDayOfMonth(year, month) +" 00:00:00";
				endTime = getLastDayOfMonth(year,month)+" 23:59:59";
			} catch (Exception e) {
				e.printStackTrace();
				return JSON.toJSONString(ReturnUtil.fail("查询参数有误", null));
			}
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, String> returnMap = customerActiveOrderFacade.getActiveCustomerData(strTime, endTime);
			logger.info("getDetail,returnMap:" + returnMap.toString());
			if(returnMap != null && "SUCCESS".equals(returnMap.get("flag"))){
				// 活跃商户数
				result.put("activeNum", returnMap.get("ACTIVENUM"));
				// 活跃商户交易笔数
				result.put("activetransNum", returnMap.get("TRANSNUM"));
				// 活跃商户交易金额
				result.put("activeAmount", returnMap.get("TRANSAMOUNT"));
				// 新入网商户数
				result.put("newNum", returnMap.get("NEWNUM"));
				// 新入网活跃商户数
				result.put("newActiveNum", returnMap.get("NEWACTIVENUM"));
				// 新入网活跃商户交易笔数
				result.put("newActivetransNum", returnMap.get("NEWTRANSNUM"));
				// 新入网活跃商户交易金额
				result.put("newActiveAmount", returnMap.get("NEWTRANSAMOUNT"));
//				// 新入网当月激活商户数
//				result.put("newJihuoNum", returnMap.get("NEWJIHUONUM"));
//				// 新增激活商户数：当月有交易金额>=50 POS交易
//				result.put("newJihuoPosNum", returnMap.get("NEWJIHUOPOSNUM"));
				return JSON.toJSONString(ReturnUtil.success(result));
			}else{
				return JSON.toJSONString(ReturnUtil.error(null));
			}
		} catch (Exception e) {
			logger.error("newOpen/getDetail", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
	
}
