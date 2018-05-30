package com.pay.coeus.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.coeus.CustomerContactHistoryMapper;
import com.pay.coeus.core.dao.coeus.CustomerContactInfoMapper;
import com.pay.coeus.core.dao.coeus.CustomerContactTypeMapper;
import com.pay.coeus.core.service.CustomerContactInfoService;
import com.pay.coeus.model.entity.CustomerContactHistory;
import com.pay.coeus.model.entity.CustomerContactInfo;
import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;

/** 
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactInfoService 此处填写需要参考的类
 * @version 2017年11月9日 上午6:54:54 
 * @author yuze.luo
 */
@Service
public class CustomerContactInfoServiceImpl implements CustomerContactInfoService{
	private Logger logger = LoggerFactory.getLogger(CustomerContactInfoServiceImpl.class);
	@Resource
    private CustomerContactInfoMapper customerContactInfoMapper;
	@Resource
    private CustomerContactHistoryMapper customerContactHistoryMapper;
	@Resource
    private CustomerContactTypeMapper customerContactTypeMapper;
	
	/*@Override
	public void dealContactInfoQueue() {
		// 本次执行记录个数
		Long menuLength = RedisUtil.llen(RedisKeyConstants.CUSTOMER_CONTACT_INFO_QUEUE);
		logger.info("dealContactInfoQueue，size：{}", menuLength);
		int init = 1000;// 每隔1000条循环一次
		int total = Integer.parseInt(menuLength.toString());// 总计录
		int cycelTotal = total / init;// 循环次数
		if (total % init != 0) {
			cycelTotal += 1;
			if (total < init) {
				init = total;
			}
		}
		List<CustomerContactInfo> list = new ArrayList<CustomerContactInfo>();
		// 批量插入init条记录
		for (int i = 0; i < cycelTotal; i++) {
			CustomerContactInfo info = null;
			for (int j = 0; j < init; j++) {
				String json = RedisUtil.rpop(RedisKeyConstants.CUSTOMER_CONTACT_INFO_QUEUE);
				if (StringUtils.isNotBlank(json) && !"null".equals(json)) {
					try {
						info = (CustomerContactInfo) JSON.parseObject(json, CustomerContactInfo.class);
						if(info != null && StringUtils.isNotBlank(info.getCustomerNo()) && StringUtils.isNotBlank(info.getPhone())){
							list.add(info);
						}
					} catch (Exception e) {
						RedisUtil.lpush(RedisKeyConstants.CUSTOMER_CONTACT_INFO_QUEUE, json);
						continue;
					}
				}
			}
			if (list.size() > 0) {
				logger.info("保存{}条数据到customer_contact_info数据库....", list.size());
				customerContactInfoMapper.insertBatch(list);
				list.clear();// 移出当前保存的数据
			}
		}
	}*/

	@Override
	public void addContactInfo(String customerNo, String customerRole, String phone, String source, String remark) {
		try {
			if(StringUtils.isNotBlank(customerNo) && StringUtils.isNotBlank(phone)){
				// 历史记录直接新增
				CustomerContactHistory his = new CustomerContactHistory();
				his.setCustomerNo(customerNo);
				his.setCustomerRole(customerRole);
				his.setPhone(phone);
				his.setSource(source);
				his.setRemark(remark);
				// 将点击事件入队列
//				RedisUtil.lpush(RedisKeyConstants.CUSTOMER_CONTACT_INFO_QUEUE, JSON.toJSONString(info));
				customerContactHistoryMapper.insert(his);
				if(StringUtils.isBlank(source)){
					return;
				}
				CustomerContactType type1 = customerContactTypeMapper.findByKeyword(source);
				if(type1 != null && type1.getType() == 1){
					// 判断是否已有记录,没有直接新增，有的话判断一下
					CustomerContactInfo info = customerContactInfoMapper.getByCustomerNo(customerNo);
					if(info == null || info.getSource() == null || "".equals(info.getSource())){
						info = new CustomerContactInfo();
						info.setCustomerNo(customerNo);
						info.setCustomerRole(customerRole);
						info.setPhone(phone);
						info.setSource(source);
						info.setRemark(remark);
						customerContactInfoMapper.insert(info);
					}else{
						CustomerContactType type2 = customerContactTypeMapper.findByKeyword(info.getSource());
						// 根据来源判断哪个权重较高，新的高则进行修改操作。
						if(type2 == null || type2.getType() == 0 || type1.getRank()-type2.getRank()>=0){
							info.setCustomerNo(customerNo);
							info.setCustomerRole(customerRole);
							info.setPhone(phone);
							info.setSource(source);
							info.setRemark(remark);
							customerContactInfoMapper.update(info);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("addContactInfo异常：{}", e);
		}
	}

	@Override
	public String getValidPhoneByCustomerNo(String customerNo) {
		if(StringUtils.isBlank(customerNo)){
			return null;
		}
		try{
			CustomerContactInfo cci = customerContactInfoMapper.getByCustomerNo(customerNo);
			if(cci != null){
				return cci.getPhone();
			}
		}catch(Exception e){
			logger.error("coeus_根据商编查询有效联系方式异常", e);
		}
		return null;
	}
	

	@Override
	public Map<String, String> getValidInfoByCustomerNo(String customerNo) {
		if(StringUtils.isBlank(customerNo)){
			return null;
		}
		try{
			CustomerContactInfo cci = customerContactInfoMapper.getByCustomerNo(customerNo);
			if(cci != null){
				Map<String, String> map = new HashMap<String, String>();
				map.put("customerNo", cci.getCustomerNo());
				map.put("customerRole", cci.getCustomerRole());
				map.put("source", cci.getSource());
				map.put("phone", cci.getPhone());
				map.put("remark", cci.getRemark());
				return map;
			}
		}catch(Exception e){
			logger.error("coeus_根据商编查询有效联系信息异常", e);
		}
		return null;
	}

	@Override
	public List<String> getCustomerNosByPhone(String phone) {
		if(StringUtils.isBlank(phone)){
			return null;
		}
		try{
			return customerContactInfoMapper.getListByPhone(phone);
		}catch(Exception e){
			logger.error("", e);
		}
		return null;
	}
	
	@Override
	public List<CustomerContactInfo> findAllLList(Page<List<CustomerContactInfo>> page, Map<String, String> param) {
		return customerContactInfoMapper.findAllList(page, param);
	}

	@Override
	public CustomerContactInfo findById(Long id) {
		return customerContactInfoMapper.findById(id);
	}

	@Override
	public List<Map<String, String>> getCustomerNoAndRoleByPhone(String phone) {
		List<CustomerContactInfo> infos = customerContactInfoMapper.getCustomerNoAndRoleByPhone(phone);
		if(infos != null && infos.size()>0){
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			for(int k =0;k<infos.size();k++){
				map = new HashMap<String, String>();
				map.put("customerNo", infos.get(k).getCustomerNo());
				map.put("customerRole", infos.get(k).getCustomerRole());
				map.put("source", infos.get(k).getSource());
				map.put("phone", infos.get(k).getPhone());
				map.put("remark", infos.get(k).getRemark());
				list.add(map);
			}
			return list;
		}else{
			return null;
		}
	}

	@Override
	public void updateToAgent(Long id, String role) {
		customerContactInfoMapper.updateToAgent(id, role);
	}

}
