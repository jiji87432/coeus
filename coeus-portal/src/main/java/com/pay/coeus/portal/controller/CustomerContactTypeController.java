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
import com.pay.coeus.api.inner.dubbo.CustomerContactTypeFacade;
import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;

/**
 * 联系方式来源
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactTypeController 此处填写需要参考的类
 * @version 2017年11月10日 上午10:41:29 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/contactType")
public class CustomerContactTypeController {
private static final Logger logger = LoggerFactory.getLogger(CustomerContactTypeController.class);
	
	@Autowired
	private CustomerContactTypeFacade customerContactTypeFacade;
	// 跳转到列表页
	@RequestMapping("/list.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("contactType/list");
		return model;
	}
	// 获取列表页数据
	@RequestMapping("/findAllList.action")
	@ResponseBody
	public String findAllList(HttpServletRequest request,@RequestParam Map<String,String> param){
		Page<List<CustomerContactType>> list;
		logger.info("findAllList_params {}", param);
		try {
			Page<List<CustomerContactType>> page = (Page<List<CustomerContactType>>)request.getAttribute("page");
			list = customerContactTypeFacade.findAllList(page, param);
			return JSON.toJSONString(ReturnUtil.success(list));
		} catch (Exception e) {
			logger.error("contactType/findList", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
	// 跳转到详情页
	@RequestMapping("queryDetail.action")
	public ModelAndView queryDetail(Long id){
		logger.info("queryDetail_id {}", id);
		CustomerContactType contactType = null;
		if(id != null && id > 0){
			contactType = customerContactTypeFacade.findById(id);
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("contactType/detail");
		model.addObject("contactType", contactType);
		return model;
	}
	// 修改
	@RequestMapping("/update.action")
	public ModelAndView update(HttpServletRequest request,@RequestParam Map<String,String> param){
		logger.info("update_params {}", param);
		try {
			CustomerContactType contactType = new CustomerContactType();
			if(param.get("id") != null &&!"".equals(param.get("id"))){
				contactType.setId(Long.valueOf(param.get("id")));
			}
			contactType.setName(param.get("name"));
			contactType.setKeyword(param.get("keyword"));
			contactType.setRank(Integer.valueOf(param.get("rank")));
			contactType.setType(Integer.valueOf(param.get("type")));
			contactType.setRemark(param.get("remark"));
			customerContactTypeFacade.addOrModify(contactType);
		} catch (Exception e) {
			logger.error("contactType/findAllList", e);
		}
		return testCon();
	}
	
}
