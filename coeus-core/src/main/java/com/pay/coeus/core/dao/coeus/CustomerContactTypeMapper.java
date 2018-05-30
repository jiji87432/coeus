package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.CustomerContactType;
import com.pay.commons.utils.Page;

public interface CustomerContactTypeMapper {
	
	void insert(CustomerContactType customerContactType);

	void update(CustomerContactType customerContactType);
	
	List<CustomerContactType> getAllList();
	
	List<CustomerContactType> findAllList(@Param("page") Page<List<CustomerContactType>> page,@Param("param") Map<String,String> param);

	CustomerContactType findById(@Param("id")Long id);

	CustomerContactType findByKeyword(@Param("keyword")String keyword);
	
}
