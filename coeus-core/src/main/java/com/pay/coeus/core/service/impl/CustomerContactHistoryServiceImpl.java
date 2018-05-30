package com.pay.coeus.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.core.dao.coeus.CustomerContactHistoryMapper;
import com.pay.coeus.core.service.CustomerContactHistoryService;
import com.pay.coeus.model.entity.CustomerContactHistory;
import com.pay.commons.utils.Page;

/** 
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactHistoryService 此处填写需要参考的类
 * @version 2017年11月9日 上午6:54:54 
 * @author yuze.luo
 */
@Service
public class CustomerContactHistoryServiceImpl implements CustomerContactHistoryService{
	
	@Resource
    private CustomerContactHistoryMapper customerContactHistoryMapper;
	
	@Override
	public void addContactHistory(String customerNo, String customerRole, String phone, String source, String remark) {
		CustomerContactHistory his = new CustomerContactHistory();
		his.setCustomerNo(customerNo);
		his.setCustomerRole(customerRole);
		his.setPhone(phone);
		his.setSource(source);
		his.setRemark(remark);
		customerContactHistoryMapper.insert(his);
	}

	@Override
	public List<CustomerContactHistory> findAllLList(Page<List<CustomerContactHistory>> page, Map<String, String> param) {
		return customerContactHistoryMapper.findAllList(page, param);
	}

	@Override
	public List<Map<String, String>> getPhonesByCustomerNo(String customerNo) {
		List<CustomerContactHistory> historys = customerContactHistoryMapper.getPhonesByCustomerNo(customerNo);
		if(historys != null && historys.size()>0){
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			for(int k =0;k<historys.size();k++){
				map = new HashMap<String, String>();
				map.put("customerNo", historys.get(k).getCustomerNo());
				map.put("customerRole", historys.get(k).getCustomerRole());
				map.put("source", historys.get(k).getSource());
				map.put("phone", historys.get(k).getPhone());
				map.put("remark", historys.get(k).getRemark());
				map.put("createTime", DateUtils.formatDateTime(historys.get(k).getCreateTime()));
				list.add(map);
			}
			return list;
		}else{
			return null;
		}
	}

}
