package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.coeus.CustomerContactTypeMapper;
import com.pay.coeus.core.service.CustomerContactTypeService;
import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;

/** 
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactInfoService 此处填写需要参考的类
 * @version 2017年11月9日 上午6:54:54 
 * @author yuze.luo
 */
@Service
public class CustomerContactTypeServiceImpl implements CustomerContactTypeService{
	@Resource
    private CustomerContactTypeMapper customerContactTypeMapper;
	@Override
	public List<CustomerContactType> findAllLList(Page<List<CustomerContactType>> page, Map<String, String> param) {
		return customerContactTypeMapper.findAllList(page, param);
	}
	
	@Override
	public CustomerContactType findById(Long id) {
		return customerContactTypeMapper.findById(id);
	}
	
	@Override
	public void addOrModify(CustomerContactType customerContactType) {
		// 不允许type为null
		if(customerContactType.getType() == null){
			customerContactType.setType(0);
		}
		if(customerContactType.getId() != null && customerContactType.getId()>0){
			customerContactTypeMapper.update(customerContactType);
		}else{
			customerContactTypeMapper.insert(customerContactType);
		}
	}

	@Override
	public List<CustomerContactType> getAllLList() {
		return customerContactTypeMapper.getAllList();
	}
	
}
