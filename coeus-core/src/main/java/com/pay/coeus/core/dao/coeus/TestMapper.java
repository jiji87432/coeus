package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.TestCoeus;
import com.pay.commons.utils.Page;

public interface TestMapper {
	void insert(@Param("name") String name, @Param("value") String value);
	void update(String name);
	void delete(Long id);
	public List<Map<String, String>> findAllList(@Param("page") Page page);
	TestCoeus findById(Long id);
}
