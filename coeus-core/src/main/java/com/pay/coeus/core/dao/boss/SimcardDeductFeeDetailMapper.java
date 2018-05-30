package com.pay.coeus.core.dao.boss;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.SimcardDeductFeeDetail;

@Mapper
public interface SimcardDeductFeeDetailMapper {
	
	/**
	 * 查询需要扣费的SIM卡列表
	 * @Description  一句话描述方法用法
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String, Object>> findSimDeductFreezeList();

	/**
	 * 插入扣费记录
	 * @Description  一句话描述方法用法
	 * @param scdfd
	 * @see 需要参考的类或方法
	 */
	void saveSimCardDeductFeeDetail(SimcardDeductFeeDetail scdfd);

	/**
	 * 修改状态
	 * @Description  一句话描述方法用法
	 * @param scdfd
	 * @see 需要参考的类或方法
	 */
	void update(SimcardDeductFeeDetail scdfd);

	/**
	 * 根据状态获取列表
	 * @Description  一句话描述方法用法
	 * @param string
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<SimcardDeductFeeDetail> findSimCardDeductFeeDetailListByStatus(@Param("deductStatus") String deductStatus);

}
