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
import org.apache.ibatis.type.JdbcType;

import com.pay.coeus.model.entity.OldCustomerLoss;

@Mapper
public interface MonthTradeFrequencyMapper {

	@Insert("insert into posp_boss.month_trade_frequency "
			+ "(id, optimistic, customer_no, create_time, trans_day) "
			+ "select posp_boss.seq_month_trade_frequency_id.nextval as id , 0 as optimistic,  t.customer_no, sysdate as create_time, t.trans_day as trans_day "
			+ "from( select c.CUSTOMER_NO, count(distinct o.order_time) as trans_day from posp_boss.customer_new_old_info c "
			+ "left join posp_boss.customer_trans_day o on c.CUSTOMER_NO=o.customer_no "
			+ "where c.is_new='N' and o.order_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -2)))+1 "
			+ "and o.order_time < TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 "
			+ "group by c.CUSTOMER_NO) t")
	public int insertMonthTradeFrequency();
	
	@Select("select ti.customer_no, c.full_name, c.status as customer_status, l.customer_level, "
			+ "ei.status as customer_check_info, i.bind_time, i.activate_time , 'SUSPECT_LOSS' as result_type, c.LINKMAN as link_man  "
			+ "from ( "
			+ "select f.customer_no, count(td.order_time) as trans_count from posp_boss.month_trade_frequency f  "
			+ "left join ( select d.customer_no, d.order_time from "
			+ "posp_boss.customer_trans_day d "
			+ "where d.order_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 "
			+ ") td on f.customer_no=td.customer_no "
			+ "where f.create_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -2)))+1 and f.create_time < TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 "
			+ "and f.trans_day >= #{beginDay} and f.trans_day < #{endDay} "
			+ "group by f.customer_no "
			+ ") ti "
			+ "left join posp_boss.customer_new_old_info i on ti.customer_no = i.customer_no "
			+ "left join posp_boss.customer c on i.customer_no=c.customer_no "
			+ "left join posp_boss.pos_customer_level l on i.customer_no=l.customer_no "
			+ "left join posp_boss.enterprise_check_info ei on i.customer_no=ei.customer_no "
			+ "where ti.trans_count=0")
	@Results(value = {
			@Result(property = "customerNo", column = "customer_no", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerName", column = "full_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerStatus", column = "customer_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerLevel", column = "customer_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "customerCheckInfo", column = "customer_check_info", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "linkMan", column = "link_man", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "bindTime", column = "bind_time", javaType = Date.class),
			@Result(property = "activateTime", column = "activate_time", javaType = Date.class),
			@Result(property = "resultType", column = "result_type", javaType = String.class, jdbcType = JdbcType.VARCHAR)
	})
	public List<OldCustomerLoss> loadSuspectCustomerLoss(@Param("beginDay") Integer beginDay,@Param("endDay") Integer endDay);

	@Select("select f1.customer_no,  c.full_name,c.status as customer_status, l.customer_level, "
			+ "ei.status as customer_check_info, i.bind_time, i.activate_time , 'LOSS' as result_type , c.LINKMAN as link_man "
			+ "from posp_boss.month_trade_frequency f1  "
			+ "left join ( select f2.customer_no, f2.trans_day from posp_boss.month_trade_frequency f2 "
			+ "where "
			+ "f2.create_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 and "
			+ "f2.create_time < TRUNC(LAST_DAY(SYSDATE))+1 ) t on f1.customer_no = t.customer_no "
			+ "left join posp_boss.customer_new_old_info i on f1.customer_no=i.customer_no "
			+ "left join posp_boss.customer c on i.customer_no=c.customer_no "
			+ "left join posp_boss.pos_customer_level l on i.customer_no=l.customer_no "
			+ "left join posp_boss.enterprise_check_info ei on i.customer_no=ei.customer_no "
			+ "where f1.create_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -2)))+1 and f1.create_time < TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 "
			+ "and t.trans_day is null")
	@Results(value = {
			@Result(property = "customerNo", column = "customer_no", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerName", column = "full_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerStatus", column = "customer_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "customerLevel", column = "customer_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "customerCheckInfo", column = "customer_check_info", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "linkMan", column = "link_man", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "bindTime", column = "bind_time", javaType = Date.class),
			@Result(property = "activateTime", column = "activate_time", javaType = Date.class),
			@Result(property = "resultType", column = "result_type", javaType = String.class, jdbcType = JdbcType.VARCHAR)
	})
	public List<OldCustomerLoss> loadCustomerLoss();

	@Select("select customer_no, full_name, customer_status, customer_status, customer_level, customer_check_info, bind_time, activate_time, result_type, link_man, pos_count "
			+ " from ( "
			+ "select f1.customer_no,  c.full_name,c.status as customer_status, l.customer_level, "
			+ "ei.status as customer_check_info, i.bind_time, i.activate_time , 'LOSS' as result_type , c.LINKMAN as link_man , rownum as nu, count(p.pos_sn) as pos_count "
			+ "from posp_boss.month_trade_frequency f1  "
			+ "left join ( select f2.customer_no, f2.trans_day from posp_boss.month_trade_frequency f2 "
			+ "where "
			+ "f2.create_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 and "
			+ "f2.create_time < TRUNC(LAST_DAY(SYSDATE))+1 ) t on f1.customer_no = t.customer_no "
			+ "left join posp_boss.customer_new_old_info i on f1.customer_no=i.customer_no "
			+ "left join posp_boss.customer c on i.customer_no=c.customer_no "
			+ "left join posp_boss.pos_customer_level l on i.customer_no=l.customer_no "
			+ "left join posp_boss.enterprise_check_info ei on i.customer_no=ei.customer_no "
			+ "left join posp_boss.pos p on f1.customer_no=p.customer_no "
			+ "where f1.create_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -2)))+1 and f1.create_time < TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 "
			+ "and t.trans_day is null "
			+ "group by f1.customer_no,  c.full_name,c.status , l.customer_level, "
			+ "ei.status , i.bind_time, i.activate_time , c.LINKMAN  , rownum) "
			+ "where nu between #{beginNum} and #{endNum}")
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
	public List<OldCustomerLoss> loadCustomerLoss2(@Param("beginNum") int beginNum, @Param("endNum") int endNum);
	
	@Select("select customer_no, full_name, customer_status, customer_status, customer_level, customer_check_info, bind_time, activate_time, result_type, link_man, pos_count "
			+ "from ( "
			+ "select ti.customer_no, c.full_name, c.status as customer_status, l.customer_level, "
			+ "ei.status as customer_check_info, i.bind_time, i.activate_time , 'SUSPECT_LOSS' as result_type, c.LINKMAN as link_man, ti.pos_count, rownum as nu   "
			+ "from ( "
			+ "select f.customer_no, count(td.order_time) as trans_count, count(p.pos_sn) as pos_count from posp_boss.month_trade_frequency f  "
			+ "left join ( select d.customer_no, d.order_time from "
			+ "posp_boss.customer_trans_day d "
			+ "where d.order_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 "
			+ ") td on f.customer_no=td.customer_no "
			+ "left join posp_boss.pos p on f.customer_no=p.customer_no "
			+ "where f.create_time >= TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, -1)))+1 and f.create_time < TRUNC(LAST_DAY(SYSDATE))+1 "
			+ "and f.trans_day >= #{beginDay} and f.trans_day < #{endDay} "
			+ "group by f.customer_no "
			+ ") ti "
			+ "left join posp_boss.customer_new_old_info i on ti.customer_no = i.customer_no "
			+ "left join posp_boss.customer c on i.customer_no=c.customer_no "
			+ "left join posp_boss.pos_customer_level l on i.customer_no=l.customer_no "
			+ "left join posp_boss.enterprise_check_info ei on i.customer_no=ei.customer_no "
			+ "where ti.trans_count=0) "
			+ "where nu between #{beginNum} and #{endNum}")
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
	public List<OldCustomerLoss> loadSuspectCustomerLoss2(@Param("beginDay") Integer beginDay,@Param("endDay") Integer endDay,
															@Param("beginNum") int beginNum, @Param("endNum") int endNum);

	@Select("select "
			+ "to_char(trunc(ADD_MONTHS(SYSDATE, #{month}-2), 'mm'),'yyyyMMdd') || '-'||to_char(trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm'),'yyyyMMdd') as \"retainedTime\" , "
			+ "count(total_customer) as \"totalCount\", sum (case when trans_customer is not null then 1 else 0 end) as \"retained\", sum(transAmount) as \"transAmount\" from ("
			+ "select f1.customer_no as total_customer, t.customer_no as trans_customer, sum(d.trans_amount) as transAmount "
			+ "from posp_boss.month_trade_frequency f1  "
			+ "left join "
			+ "( select f2.customer_no, f2.trans_day from posp_boss.month_trade_frequency f2 "
			+ "where f2.create_time >= trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm') and "
			+ "f2.create_time < trunc(ADD_MONTHS(SYSDATE, #{month}), 'mm') ) t on f1.customer_no = t.customer_no "
			+ "left join posp_boss.customer_trans_day d on f1.customer_no=d.customer_no and d.order_time >= trunc(ADD_MONTHS(SYSDATE, #{month}-2), 'mm') and d.order_time < trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm') "
			+ "where f1.create_time >= trunc(ADD_MONTHS(SYSDATE, #{month}-2), 'mm') and f1.create_time < trunc(ADD_MONTHS(SYSDATE, #{month}-1), 'mm') "
			+ "group by f1.customer_no, t.customer_no)")
	public List<Map<String,Object>> loadRetainedCustomer(@Param("month") Integer month);
}
