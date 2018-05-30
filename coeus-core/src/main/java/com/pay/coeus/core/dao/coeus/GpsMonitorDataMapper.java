package com.pay.coeus.core.dao.coeus;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.GpsMonitorData;

public interface GpsMonitorDataMapper {
	
	void insert(GpsMonitorData gpsMonitorData);
	
	void insertBatch(@Param("dataList") List<Map<String, String>> dataList);
	
	int getCountByIccid(@Param("iccid")String iccid, @Param("strTime")Date strTime);
	
}
