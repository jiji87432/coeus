package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.CustomerContactInfo;
import com.pay.commons.utils.Page;

public interface CustomerContactInfoMapper {
	
	CustomerContactInfo getByCustomerNo(@Param("customerNo")String customerNo);
	
	List<String> getListByPhone(@Param("phone")String phone);
	
	void insert(CustomerContactInfo customerContactInfo);

	void insertBatch(@Param("infoList")List<CustomerContactInfo> list);
	
	void update(CustomerContactInfo customerContactInfo);
	
	List<CustomerContactInfo> findAllList(@Param("page") Page<List<CustomerContactInfo>> page,@Param("param") Map<String,String> param);

	CustomerContactInfo findById(@Param("id")Long id);
	
	List<CustomerContactInfo> getCustomerNoAndRoleByPhone(@Param("phone")String phone);

	void updateToAgent(@Param("id")Long id, @Param("customerRole")String role);
	
}
