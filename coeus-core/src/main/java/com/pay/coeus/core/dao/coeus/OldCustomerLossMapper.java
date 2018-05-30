package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.commons.utils.Page;


public interface OldCustomerLossMapper {
	
	int insertBatch(@Param("customerList") List<OldCustomerLoss> lists);
	
	List<OldCustomerLoss> findAllList(@Param("page") Page<List<OldCustomerLoss>> page,@Param("param") Map<String,String> param);
	
	List<String> findCustomerByDay(@Param("day") Integer day, @Param("number") Integer number, @Param("pageLimit") Integer pageLimit);

	int updateOldCustomerActivateTrans(@Param("day") Integer day, @Param("list") List<Map<String,Object>> list);
}
