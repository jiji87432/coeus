package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.commons.utils.Page;


public interface NewCustomerActivateMapper {
	
	int insertBatch(@Param("customerList") List<NewCustomerActivate> lists);
	
	List<NewCustomerActivate> findAllList(@Param("page") Page<List<NewCustomerActivate>> page,@Param("param") Map<String,String> param);

	List<String> findCustomerByDay(@Param("day") Integer day, @Param("number") Integer number, @Param("pageLimit") Integer pageLimit);

	int updateNewCustomerActivateTrans(@Param("day") Integer day, @Param("list") List<Map<String,Object>> list);
}
