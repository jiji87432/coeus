package com.pay.coeus.portal.controller;

import java.util.List;
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
import com.pay.coeus.api.inner.dubbo.CustomerContactHistoryFacade;
import com.pay.coeus.api.inner.dubbo.CustomerContactInfoFacade;
import com.pay.coeus.model.entity.CustomerContactHistory;
import com.pay.coeus.model.entity.CustomerContactInfo;
import com.pay.commons.utils.Page;

/**
 * 联系方式沉淀
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactHistoryController 此处填写需要参考的类
 * @version 2017年11月15日 上午10:41:15 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/contactHistory")
public class CustomerContactHistoryController {
private static final Logger logger = LoggerFactory.getLogger(CustomerContactHistoryController.class);
	
	@Autowired
	private CustomerContactHistoryFacade customerContactHistoryFacade;
	@Autowired
	private CustomerContactInfoFacade customerContactInfoFacade;
	// 挑转到列表页
	@RequestMapping("/list.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("contactHistory/list");
		return model;
	}
	// 获取列表页数据
	@RequestMapping("/findAllList.action")
	@ResponseBody
	public String findAllList(HttpServletRequest request,@RequestParam Map<String,String> param){
		Page<List<CustomerContactHistory>> list;
		try {
			if(param.get("createTimeStart") != null && param.get("createTimeStart").length() == 16){
				param.put("createTimeStart", param.get("createTimeStart")+":00");
			}
			if(param.get("createTimeEnd") != null && param.get("createTimeEnd").length() == 16){
				param.put("createTimeEnd", param.get("createTimeEnd")+":59");
			}
			logger.info("params {}", param);
			Page<List<CustomerContactHistory>> page = (Page<List<CustomerContactHistory>>)request.getAttribute("page");
			list = customerContactHistoryFacade.findAllList(page, param);
			return JSON.toJSONString(ReturnUtil.success(list));
		} catch (Exception e) {
			logger.error("contactHistory/findList", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
	// 跳转到文件上传页
	@RequestMapping("toUpload.action")
	public ModelAndView toUpload(){
		ModelAndView model = new ModelAndView();
		model.setViewName("contactHistory/upload");
		return model;
	}
	
	// 进行上传操作，上传完成进入列表页
	@RequestMapping("doUpload.action")
	public ModelAndView doUpload(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.setViewName("contactHistory/list");
		return model;
	}
	
	//跳转到详情页
	@RequestMapping("queryDetail.action")
	public ModelAndView queryDetail(Long id){
		logger.info("queryDetail_id {}", id);
		ModelAndView model = new ModelAndView();
		model.setViewName("contactHistory/detail");
		model.addObject("contactHistory", null);
		return model;
	}
	
	// 新增操作
	@RequestMapping("/add.action")
	public ModelAndView add(HttpServletRequest request,@RequestParam Map<String,String> param){
		logger.info("update_params {}", param);
		try {
			CustomerContactInfo contactInfo = new CustomerContactInfo();
			if(param.get("id") != null &&!"".equals(param.get("id"))){
				contactInfo.setId(Long.valueOf(param.get("id")));
			}
			contactInfo.setCustomerNo(param.get("customerNo"));
			contactInfo.setCustomerRole(param.get("customerRole"));
			contactInfo.setPhone(param.get("phone"));
			if(param.get("source") == null || "".equals(param.get("source"))){
				contactInfo.setSource("COEUS");
			}else{
				contactInfo.setSource(param.get("source"));
			}
			contactInfo.setRemark(param.get("remark"));
			customerContactInfoFacade.addOrModify(contactInfo);
		} catch (Exception e) {
			logger.error("contactHistory/findAllList", e);
		}
		return testCon();
	}
	
}
