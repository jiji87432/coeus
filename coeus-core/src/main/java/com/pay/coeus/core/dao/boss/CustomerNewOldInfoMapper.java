package com.pay.coeus.core.dao.boss;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.pay.coeus.model.entity.NewCustomerActivate;

@Mapper
public interface CustomerNewOldInfoMapper {
	
	@Insert("insert into customer_new_old_info(id, optimistic, customer_no, create_time, is_new, last_modify_time) "
			+ "select SEQ_CUSTOMER_NEW_OLD_INFO_ID.nextval, 0, c.CUSTOMER_NO, sysdate, 'Y', sysdate "
			+ "from posp_boss.customer c where c.OPEN_TIME > TRUNC(SYSDATE) -1 "
			+ "and c.OPEN_TIME < TRUNC(SYSDATE) and c.AGENT_ID <> 37447301 "
			+ "and c.MCC not in ('9990','9991','9992','9993','9997','9998','9999')"
			+ "and c.CUSTOMER_NO not in (select i.customer_no from customer_new_old_info i) ")
	public int insertNewOpenCustomerNo();
	
	@Update("MERGE INTO posp_boss.customer_new_old_info T1 "
			+ "USING (select C.CUSTOMER_NO as customer_no, MIN(b.bindtime) as bind_time "
			+ "from posp_boss.customer_new_old_info c "
			+ "left join  posp_boss.posbind_bill b  on c.customer_no=b.customer_no "
			+ "where c.bind_time is null and c.is_new='Y' and b.operate_type='BIND' and b.status='SUCCESS' "
			//+ "and b.bindtime >= TRUNC(SYSDATE)-1 and b.bindtime < TRUNC(SYSDATE) "
			+ "group by c.CUSTOMER_NO) T2 "
			+ "ON ( T1.customer_no=T2.customer_no) "
			+ "WHEN MATCHED THEN "
			+ "UPDATE SET T1.Optimistic= T1.Optimistic+1, T1.bind_time = T2.bind_time, last_modify_time=sysdate")
	public int updateBindTime();
	
	@Update("MERGE INTO posp_boss.customer_new_old_info T1 "
			+ "USING (select C.CUSTOMER_NO, min(o.create_time) as activate_time "
			+ "from posp_boss.customer_new_old_info c "
			+ "left join posp_boss.pos_order o on c.CUSTOMER_NO=o.customer_no  "
			+ "where o.status in ('SUCCESS','SETTLED') and o.amount >=50 and o.trans_type <> 'SM_PURCHASE' "
			//+ "and o.create_time >= TRUNC(SYSDATE)-1 and o.create_time < TRUNC(SYSDATE) "
			+ "and c.activate_time is null and c.is_new='Y' group by c.CUSTOMER_NO) T2 "
			+ "ON ( T1.customer_no=T2.customer_no) "
			+ "WHEN MATCHED THEN "
			+ "UPDATE SET T1.Optimistic= T1.Optimistic+1, T1.Activate_Time = T2.activate_time, last_modify_time=sysdate")
	public int updateActivateTime();
	 
	@Select("select i.customer_no, c.full_name, c.status as customer_status, l.customer_level, "
			+ "ei.status as customer_check_info, i.bind_time, i.activate_time, "
			+ "'NO_ACTIVATE' as result_type, c.LINKMAN as link_man, count(p.pos_sn) as pos_count "
			+ "from posp_boss.customer_new_old_info i "
			+ "left join posp_boss.customer c on i.customer_no=c.customer_no "
			+ "left join posp_boss.pos_customer_level l on i.customer_no=l.customer_no "
			+ "left join posp_boss.enterprise_check_info ei on i.customer_no=ei.customer_no "
			+ "left join posp_boss.pos p on i.customer_no=p.customer_no "
			+ "where i.bind_time >= TRUNC(SYSDATE) - (#{day}+1) and i.bind_time < TRUNC(SYSDATE) - #{day}  "
			+ "and i.activate_time is null and i.is_new='Y' "
			+ "group by i.customer_no, c.full_name, c.status, l.customer_level,"
			+ "ei.status, i.bind_time, i.activate_time, "
			+ "c.LINKMAN ")
	@Results(value = {
			@Result(property = "customerNo", column = "customer_no", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerName", column = "full_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerStatus", column = "customer_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerLevel", column = "customer_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "customerCheckInfo", column = "customer_check_info", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "linkMan", column = "link_man", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "bindTime", column = "bind_time", javaType = Date.class),
			@Result(property = "activateTime", column = "activate_time", javaType = Date.class),
			@Result(property = "resultType", column = "result_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "posCount", column = "pos_count", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
	})
	public List<NewCustomerActivate> loadNewCustomerBindNoActivate(@Param("day") Integer day);
	
	@Update("MERGE INTO posp_boss.customer_new_old_info T1 "
			+ "USING (select distinct i.customer_no from posp_boss.customer_new_old_info i "
			+ "left join posp_boss.customer_trans_day d on i.customer_no = d.customer_no "
			+ "where i.is_new='Y' and d.order_time >= TRUNC(SYSDATE) - #{day} "
			+ "and i.activate_time >= TRUNC(SYSDATE) - (#{day}+1) and i.activate_time < TRUNC(SYSDATE) - #{day}) T2 "
			+ "ON (T1.customer_no = T2.customer_no) "
			+ "WHEN MATCHED THEN "
			+ "UPDATE SET T1.Optimistic = T1.Optimistic + 1, T1.Is_New = 'N', T1.Last_Modify_Time=sysdate")
	public int updateActivateRemain(@Param("day") Integer day);
	
	@Select("select i.customer_no, c.full_name, c.status as customer_status, l.customer_level, "
			+ "ei.status as customer_check_info, i.bind_time, i.activate_time , "
			+ "'NO_REMAIN' as result_type, c.LINKMAN as link_man, count(p.pos_sn) as pos_count "
			+ "from posp_boss.customer_new_old_info i "
			+ "left join posp_boss.customer c on i.customer_no=c.customer_no "
			+ "left join posp_boss.pos_customer_level l on i.customer_no=l.customer_no "
			+ "left join posp_boss.enterprise_check_info ei on i.customer_no=ei.customer_no "
			+ "left join posp_boss.pos p on i.customer_no=p.customer_no "
			+ "where i.is_new='Y' "
			+ "and i.activate_time >= TRUNC(SYSDATE) - (#{day}+1) and i.activate_time < TRUNC(SYSDATE) - #{day} "
			+ "group by i.customer_no, c.full_name, c.status, l.customer_level, "
			+ "ei.status , i.bind_time, i.activate_time , "
			+ "c.LINKMAN ")
	@Results(value = {
			@Result(property = "customerNo", column = "customer_no", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerName", column = "full_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerStatus", column = "customer_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerLevel", column = "customer_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "customerCheckInfo", column = "customer_check_info", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "linkMan", column = "link_man", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "bindTime", column = "bind_time", javaType = Date.class),
			@Result(property = "activateTime", column = "activate_time", javaType = Date.class),
			@Result(property = "resultType", column = "result_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "posCount", column = "pos_count", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
	})
	public List<NewCustomerActivate> loadNewCustomerBindNoRemain(@Param("day") Integer day);

	@Update("update posp_boss.customer_new_old_info i set i.optimistic=i.optimistic+1, i.is_new='N', i.last_modify_time=sysdate "
			+ "where  i.is_new='Y' "
			+ "and i.activate_time >= TRUNC(SYSDATE) - (#{day}+1) and i.activate_time < TRUNC(SYSDATE) - #{day}")
	public int updateActivateNoRemain(@Param("day") Integer day);
	
	@Select("select to_char(trunc(ADD_MONTHS(SYSDATE, #{month}-2), 'mm'),'yyyyMMdd') || '-'||to_char(trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm'),'yyyyMMdd') as \"activiteTime\", "
			+ "count(i.customer_no) as \"activiteCount\", sum(case when d.trans_amount is null then 0 else d.trans_amount end) as \"activiteTransAmount\" from posp_boss.customer_new_old_info i "
			+ "left join posp_boss.customer c on i.customer_no = c.CUSTOMER_NO "
			+ "left join posp_boss.customer_trans_day d on i.customer_no=d.customer_no and d.order_time >= trunc(ADD_MONTHS(SYSDATE, #{month}-2), 'mm') and d.order_time < trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm') "
			+ "where c.OPEN_TIME > to_date('2017-09-01','yyyy-MM-dd') "
			+ "and i.activate_time >= trunc(ADD_MONTHS(SYSDATE, #{month}-2), 'mm') and i.activate_time < trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm')")
	public List<Map<String,Object>> loadCustomerActivitiTime(@Param("month") Integer month);
	
	@Select("select t.customer_no as \"customerNo\", t.activate_time as \"activateTime\" from ( "
            + "select o.customer_no,o.activate_time,rownum as rn from "
            + "posp_boss.customer_new_old_info o where o.activate_time is not null "
            + "and o.last_modify_time > TRUNC(sysdate) and o.is_new='Y') t "
            + "where t.rn between #{beginNum} and #{endNum}")
	public List<Map<String,Object>> loadCustomerDayActivitiTime(@Param("beginNum") int beginNum, @Param("endNum") int endNum);
	
}
