package com.pay.coeus.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 
 * @Description: 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @see: DateUtils 此处填写需要参考的类
 * @version 2017年5月11日 下午1:46:20
 * @author meng.ren
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到制指定月后的年
	 * 
	 * @param num
	 * @return
	 */
	public static String getYear(int num) {
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + num);
		Date date = curr.getTime();
		return year.format(date);
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到指定月后的月
	 */
	public static String getMonth(int num) {
		SimpleDateFormat month = new SimpleDateFormat("MM");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + num);
		Date date = curr.getTime();
		return month.format(date);

	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到指定月后的天
	 */
	public static String getDay(int num) {
		SimpleDateFormat day = new SimpleDateFormat("dd");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + num);
		Date date = curr.getTime();
		return day.format(date);

	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 *
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 把日期字符串按照对应格式转换成日期类型
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date parseStrToDate(String str, String format) {
		if (str == null || format == null) {
			return null;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date =sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 判断字符串是否是日期
	 *
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(timeString);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 格式化时间
	 *
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	/**
	 * 获取系统时间Timestamp
	 *
	 * @return
	 */
	public static Timestamp getSysTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 获取系统时间Date
	 *
	 * @return
	 */
	public static Date getSysDate() {
		return new Date();
	}

	/**
	 * 生成时间随机数
	 *
	 * @return
	 */
	public static String getDateRandom() {
		String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return s;
	}

	/**
	 *
	 * 生成与某一天相差特定天数的日期
	 *
	 * @param date
	 *            如：2015-05-01
	 * @param pattern
	 *            如：YYYY-MM-DD
	 * @param passnumber
	 *            0 为当天
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getFixedDays(String date, String pattern, Integer days) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date d = new Date();
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
//			System.out.println("日期格式不正确");
			e.printStackTrace();
			return null;
		}
		Calendar day = Calendar.getInstance();
		day.setTime(d);
		day.add(Calendar.DATE, days);
		String nextDay = simpleDateFormat.format(day.getTime());
		return nextDay;
	}

	/**
	 *
	 * 生成与某一天相差特定月份的日期
	 *
	 * @param date
	 *            如：2015-05-01
	 * @param pattern
	 *            如：YYYY-MM-DD
	 * @param passnumber
	 *            0 为当天
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getFixedMonths(String date, String pattern, Integer months) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date d = new Date();
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
//			System.out.println("日期格式不正确");
			e.printStackTrace();
			return null;
		}
		Calendar day = Calendar.getInstance();
		day.setTime(d);
		day.add(Calendar.MONTH, months);
		String nextDay = simpleDateFormat.format(day.getTime());
		return nextDay;
	}

	public static Date addDay(Date currentDate, String dhm) {
		// 偏移量
		int offset = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		// 天
		if (dhm.endsWith("D")) {
			offset = Integer.parseInt(dhm.substring(0, dhm.indexOf("D")));
			c.add(Calendar.DAY_OF_MONTH, offset);
		} else if (dhm.endsWith("H")) { // 时
			offset = Integer.parseInt(dhm.substring(0, dhm.indexOf("H")));
			c.add(Calendar.HOUR, offset);
		} else if (dhm.endsWith("M")) { // 分
			offset = Integer.parseInt(dhm.substring(0, dhm.indexOf("M")));
			c.add(Calendar.MINUTE, offset);
		}
		return c.getTime();
	}

	/**
	 * 返回流逝时间的中文描述(比如：x天h小时m分s秒SSS毫秒)
	 * 
	 * @param elapsedTime
	 *            从开始到结束流逝时间的毫秒数
	 * @return 时间差的中文描述(比如：d天h小时m分s秒SSS毫秒)
	 */
	public static String getChineseTimeConsuming(long elapsedTime) {
		int day = (int) (elapsedTime / 1000 / 60 / 60 / 24);
		int hour = (int) ((elapsedTime % (1000 * 60 * 60 * 24)) / 1000 / 60 / 60);
		int minute = (int) ((elapsedTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60)) / 1000 / 60);
		int second = (int) ((elapsedTime / 1000) % 60);
		int millisecond = (int) (elapsedTime % 1000);

		StringBuilder sb = new StringBuilder();
		if (elapsedTime > 0) {
			if (day > 0) {
				sb.append(day).append("d ");
			}
			if (hour > 0) {
				sb.append(hour).append("h ");
			}
			if (minute > 0) {
				sb.append(minute).append("mi ");
			}
			if (second > 0) {
				sb.append(second).append("se ");
			}
			if (millisecond > 0) {
				sb.append(millisecond).append("ms");
			}
		} else {
			sb.append("0ms");
		}

		return sb.toString();
	}

	/**
	 * 比较两个日期相差的天数，包含开头，不含结尾
	 * 
	 * @Description 一句话描述方法用法
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static long compareDays(Date beginTime, Date endTime) {
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		long days = (end.getTime().getTime() - begin.getTime().getTime()) / (1000 * 60 * 60 * 24);
		return days;
	}
	
	/**
	 * 获取当月 日
	 * @return
	 */
	public static int getDayOfMonth(){
		return LocalDate.now().getDayOfMonth();
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
//	public static void main(String[] args) throws ParseException {
//		// System.out.println(formatDate(parseDate("2010/3/6")));
//		// System.out.println(getDate("yyyy年MM月dd日 E"));
//		// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		// System.out.println(time/(24*60*60*1000));
//		System.out.println(DateUtils.getFixedDays("2015-07-07", "yyyy-MM-dd", 0));
//		System.out.println(DateUtils.getFixedMonths("2015-07-07", "yyyy-MM-dd", 1));
//
//	}
}
