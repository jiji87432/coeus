package com.pay.coeus.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * String工具类
 * @author yongda.ren
 *
 */
public class StringUtils {
	/** 私有构造方法，禁止实例化 */
	private StringUtils() {
	}

	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 * @since 2.0
	 */
	public static boolean isNotBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static String nullToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 字符串替换。
	 * 
	 * @return java.lang.String
	 * @param pSource
	 *            java.lang.String 原字符串
	 * @param pOldString
	 *            java.lang.String 被替换字符串
	 * @param pNewString
	 *            java.lang.String 目标替换字符串
	 */
	public static String replaceString(String sourceString, String oldString,
			String newString) {
		String str = "";
		int beginIndex = 0;
		int endIndex = 0;
		while ((endIndex = sourceString.indexOf(oldString, beginIndex)) >= 0) {
			str += sourceString.substring(beginIndex, endIndex) + newString;
			beginIndex = endIndex + oldString.length();
		}
		str += sourceString.substring(beginIndex);
		return str;
	}

	/**
	 * 对字符串进行分割. 模拟J2SDK v1.4中String的方法public String[] split(String\u00A0regex)，
	 * 用以在JWSDK v1.3下能执行类似的字符串分割功能。 举例：split("boo:and:foo", ":")将返回{"boo",
	 * "and", "foo"}。 split("_0_1_4", "_")将返回{"", "0", "1", "4"}
	 * 
	 * @param str
	 *            待分割的字符串
	 * @param delim
	 *            分割符
	 * @return 分割后的字符串数组。如果str全部由delim组成，则分割后的数组长度为0。
	 */
	public static String[] split(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		String[] tokens = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			tokens[i] = st.nextToken();
		}
		return tokens;
	}

	/**
	 * 截短字符串，返回长度不大于maxLen的子串. 如果所给源字符串长度过大，则大于maxLen的后面部分用“…”替换
	 * 
	 * @param str
	 *            源字符
	 * @param maxLen
	 *            截短后的最大长度(按字节计算，一个汉字或全角标点长度2，一个英文、数字或半角标点长度1)
	 * @return 截短后的字符串
	 */
	public static String trim(String str, int maxLen) {
		return getLimitLengthString(str, maxLen, "…");
	}

	/**
	 * 截短字符串，返回长度不大于maxLen的子串. 如果所给源字符串长度过大，则大于maxLen的后面部分用symbol替换。
	 * 如果为空(null)则返回""。
	 * 
	 * @param str
	 *            源字符
	 * @param maxLen
	 *            截短后的最大长度(按字节计算，一个汉字或全角标点长度2，一个英文、数字或半角标点长度1)
	 * @param symbol
	 *            替换符号
	 * @return 截短后的字符串
	 */
	public static String getLimitLengthString(String str, int maxLen,
			String symbol) {
		if (str == null) {
			return "";
		}
		if (symbol == null) {
			symbol = "";
		}
		try {
			byte b[] = str.getBytes("GBK");
			if (b.length <= maxLen) {
				return str;
			}

			// 返回字符串的长度应小于或等于此长度
			int len = maxLen - symbol.getBytes("GBK").length;

			// 使用二分法查找算法
			int index = 0;
			// 记录第一个元素
			int lower = 0;
			// 记录最后一个元素
			int higher = str.length() - 1;
			while (lower <= higher) {
				// 记录中间元素，用两边之和除2
				index = (lower + higher) / 2;
				int tmpLen = str.substring(0, index).getBytes("GBK").length;
				if (tmpLen == len) {
					// 如果得到的与要查找的相等，则break退出
					break;
				} else if (tmpLen < len) {
					// 如果得到的要小于查找的，就用下标加1
					lower = index + 1;
				} else {
					// 如果得到的要大于查找的，就用下标减1
					higher = index - 1;
				}
			}
			if (lower > higher) {
				index = higher;
			}

			// 调用String构造方法以避免substring导致的内存泄露
			return new String(str.substring(0, index) + symbol);
		} catch (UnsupportedEncodingException uee) {
			String errorMessage = "内部错误：本计算机的Java环境不支持GBK编码！";
			throw new IllegalStateException(errorMessage, uee);
		}
	}

	/**
	 * 去掉字符串两端的全角空格和半角空格. 如果为空(null)则返回""
	 * 
	 * @param str
	 *            字符串
	 * @return 无左右空格的字符串
	 */
	public static String trim(String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			// return leftTrim(rightTrim(str));
			return str.replaceAll("^[　 ]+|[　 ]+$", "");
		}
	}

	/**
	 * 去除字符串首尾空格以及中间的所有空格，包括空白符、换行符、段落符、全角空格等 如果为空(null)则返回""
	 * 
	 * @param str
	 *            源字符
	 * @return 不包含空格的字符串
	 * @see java.lang.Character#isWhitespace(char)
	 */
	public static String trimAll(String str) {
		if (str == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			// 过滤掉各种空白符
			if (!Character.isWhitespace(ch)) {
				sb.append(ch);
			}
		}

		return sb.toString();
	}

	/**
	 * 转码函数(将字符串转化成ISO-8859-1编码)
	 * 
	 * @param sourceStr
	 *            原字符串
	 * @return 转码后的字符串
	 */
	public static String changeCodeISO(String sourceStr) {
		String newStr = "";
		try {
			newStr = new String(sourceStr.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newStr;
	}

	/**
	 * 将整数转换为中文小写字符串，各个数字依次转换， 比如整数102将被转换为"一○二"
	 * 
	 * @param number
	 *            整数
	 * @return 转换后的汉字小写字符串
	 */
	public static String getStringNumber(int number) {
		// 中文数字字符数组
		String[] chineseNumber = new String[] { "○", "一", "二", "三", "四", "五",
				"六", "七", "八", "九" };
		if (number < 0) {
			return "负" + getStringNumber(-number);
		} else if (number < 10) {
			return chineseNumber[number];
		} else {
			return getStringNumber(number / 10) + getStringNumber(number % 10);
		}
	}

	/**
	 * 将整数转换为中文的整数字符串，按汉语习惯的称呼各个数字依次转换， 比如整数20将被转换为"二十".
	 * 
	 * @todo 应改为与MoneyToChinese.getNumberToRMB()的相似算法
	 * 
	 * @param number
	 *            整数(暂不支持绝对值大于99的转换)
	 * @return 转换后的中文的整数字符串
	 */
	public static String getChineseNumber(int number) {
		// 中文数字字符数组
		String[] chineseNumber = new String[] { "零", "一", "二", "三", "四", "五",
				"六", "七", "八", "九" };
		// 中文单位数组
		String[] chineseUnit = new String[] { "", "十", "百", "千", "万", "十", "百",
				"千", "亿", "十", "百", "千" };

		// String sNumber = "";

		if (number < 0) {
			// 负几
			return "负" + getChineseNumber(-number);
		} else if (number < 10) {
			// 几
			return chineseNumber[number];
		} else if (number < 20) {
			if (number % 10 == 0) {
				// "十"
				return chineseUnit[1];
			} else {
				// 十几
				return chineseUnit[1] + chineseNumber[number % 10];
			}
		} else if (number < 100) {
			if (number % 10 == 0) {
				// 几十
				return chineseNumber[number / 10] + chineseUnit[1];
			} else {
				// 几十几
				return chineseNumber[number / 10] + chineseUnit[1]
						+ chineseNumber[number % 10];
			}
		} else {
			throw new java.lang.IllegalArgumentException("暂不支持绝对值大于99的转换");
		}
	}

	/**
	 * 统计子字符串在原字符串中的出现次数
	 * 
	 * @param source
	 *            原字符串
	 * @param substring
	 *            子字符串
	 * @return 子字符串在原字符串中的出现次数
	 */
	public static int substringCount(String source, String substring) {
		if (source == null || substring == null || source.length() == 0
				|| substring.length() == 0) {
			return 0;
		}
		int count = 0;
		int i = 0;
		while (source.indexOf(substring, i) != -1) {
			count++;
			i = source.indexOf(substring, i) + substring.length();
		}
		return count;
	}

	/**
	 * 把一个字符串中的标点符号替换成一种符号 (lxq 2005/03/04)
	 * 
	 * @param sourceString
	 *            String 源字符串
	 * @param splitChar
	 *            分割字符串
	 * @return String 用splitChar分割的字符串 sourceString或splitChar为null时, 返回null
	 */
	public static String replaceStringForSingleSign(String sourceString,
			String splitChar) {
		if (sourceString == null || splitChar == null) {
			return null;
		}

		// 需要处理的字符
		String[] sign = new String[] { " ", ",", "，", ";", ".", ":", "!", "@",
				"$", "%", "*", "(", ")", "-", "——", "_", "“", "”", "+", "=",
				"|", "\\", "/", "\"", "'", "?", "<", ">", "~", "`", "&", "、" };
		String newString = ""; // 格式化后的字符串
		String[] newStringArray = null; // 格式化过程中用到的数组
		// String htmlString = replaceString(sourceString, "&", "&amp;");
		// 特殊处理非常规字符(将其转换为全角空格)
		// byte[] unknowChar = { -93, -96 };
		// sourceString = replaceString(sourceString, new String(unknowChar),
		// "　");
		for (int i = 0; i < sign.length; i++) {
			sourceString = replaceString(sourceString, sign[i], "#");
		}
		// sourceString = replaceString(sourceString, ",", "#");
		// sourceString = replaceString(sourceString, ";", "#");
		// sourceString = replaceString(sourceString, ".", "#");
		newStringArray = sourceString.split("#");
		for (int i = 0; i < newStringArray.length; i++) {
			if (newStringArray[i].equals("")) {
				continue;
			}
			if (newString.length() != 0) {
				newString += splitChar;
			}
			newString += newStringArray[i];
		}
		return newString.trim();
	}

	/**
	 * 使用给定的replacement替换此字符串所有匹配给定的宏占位符的子字符串.
	 * 宏占位符"${aB汉123}"将首先转换为正则表达式"\\$\\{aB汉123\\}"。 注意，在替换字符串中使用反斜线 (\) 和美元符号
	 * ($) 可能导致与作为字面值替换字符串时所产生的结果不同。
	 * 
	 * @param str
	 *            待替换的字符串
	 * @param marco
	 *            用来匹配此字符串的宏占位符(格式为"${xyz}"，不区分大小写)
	 * @param replacement
	 *            用来替换每个匹配项的字符串
	 * @return 替换后的字符串
	 */
	public static String replaceMacro(String str, String macro,
			String replacement) {
		StringBuilder regex = new StringBuilder();
		for (int i = 0; i < macro.length(); i++) {
			char ch = macro.charAt(i);
			if (ch == '$' || ch == '{' || ch == '}' || ch == '\\') {
				regex.append("\\");
			}
			regex.append(ch);
		}

		Pattern pattern = Pattern.compile(regex.toString(),
				Pattern.CASE_INSENSITIVE);
		return pattern.matcher(str).replaceAll(replacement);
	}

	/**
	 * 字符串重复指定的次数
	 * 
	 * @param str
	 *            需重复的字符串
	 * @param repeat
	 *            重复次数
	 * @return 重复后的字符串
	 */
	public static String repeat(String str, int repeat) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0 || repeat <= 0) {
			return "";
		}
		StringBuilder buf = new StringBuilder(str.length() * repeat);
		for (int i = 0; i < repeat; i++) {
			buf.append(str);
		}

		return buf.toString();
	}

	/**
	 * 身份证号码自动从15位或17位升级到18位
	 * 
	 * @param id
	 *            15位或17位的身份证号码
	 * @return 18位的身份证号码
	 */
	public static String getId18(String id) {
		if (id == null || (id.length() != 15 && id.length() != 17)) {
			return id;
		}
		if (id.length() == 15) {
			id = id.substring(0, 6) + "19" + id.substring(6);
		}
		char[] pszSrc = id.toCharArray();
		int[] iW = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char[] szVerCode = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5',
				'4', '3', '2' };
		int iS = 0;
		for (int i = 0; i < 17; i++) {
			iS += (pszSrc[i] - '0') * iW[i];
		}
		return id + szVerCode[iS % 11];
	}

	/**
	 * 获得MQ接口所需的字符串
	 * 
	 * @param obj
	 *            对象(目前仅限于String、Number、Date这3种类型)
	 * @return 字符串
	 */
	public static String getMQString(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		if (obj instanceof Number) {
			return ((Number) obj).toString();
		}
		if (obj instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format((Date) obj);
		}
		throw new java.lang.IllegalArgumentException("不能识别对象类型[" + obj + "]");
	}

	/**
	 * 是否为ASCII(含Extended ASCII)码的字符. ASCII(American Standard Code for
	 * Information Interchange)，美国信息互换标准代码； Extended ASCII，延伸美国标准信息交换码。
	 * 对英文字母、阿拉伯数字、半角标点符号，返回true； 对中文汉字、全角标点符号，返回false。
	 * 
	 * @param c
	 *            待检测的字符
	 * @return 是否为ASCII(含Extended ASCII)码的字符
	 */
	public static boolean isAscii(char c) {
		String str = String.valueOf(c);
		byte[] b = null;
		try {
			b = str.getBytes("GBK");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: "
					+ uee.getMessage());
		}
		if (b.length == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 是否为gb2312(《信息交换用汉字编码字符集--基本集》)内码的字符. 对英文字母、阿拉伯数字、半角标点符号，返回false；
	 * 对中文汉字、全角标点符号，返回true。
	 * 
	 * @param c
	 *            待检测的字符
	 * @return 是否为gb2312内码的字符
	 */
	public static boolean isGb2312(char c) {
		/** gb2312编码高字节特殊符号最小值 */
		final int GB2312_HIGH_PUNCTUATION_MIN = 0xA1;
		/** gb2312编码高字节特殊符号最大值 */
		final int GB2312_HIGH_PUNCTUATION_MAX = 0xA9;
		/** gb2312编码高字节一级汉字最小值 */
		final int GB2312_HIGH_FIRSTPLANE_MIN = 0xB0;
		/** gb2312编码高字节一级汉字最大值 */
		final int GB2312_HIGH_FIRSTPLANE_MAX = 0xD7;
		/** gb2312编码高字节二级汉字最小值 */
		final int GB2312_HIGH_SECONDPLANE_MIN = 0xD8;
		/** gb2312编码高字节二级汉字最大值 */
		final int GB2312_HIGH_SECONDPLANE_MAX = 0xF7;
		/** gb2312编码低字节最小值 */
		final int GB2312_LOW_MIN = 0xA1;
		/** gb2312编码低字节最大值 */
		final int GB2312_LOW_MAX = 0xFE;

		String str = String.valueOf(c);
		byte[] b = null;
		try {
			b = str.getBytes("GBK");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: "
					+ uee.getMessage());
		}
		if (b.length == 2) {
			// int ch0 = b[0]; // 高位字节
			// int ch1 = b[1]; // 低位字节
			// if (ch0 < 0) {
			// ch0 += 256;
			// }
			// if (ch1 < 0) {
			// ch1 += 256;
			// }
			int ch0 = 0xff & b[0]; // 高位字节
			int ch1 = 0xff & b[1]; // 低位字节

			if (((ch0 >= GB2312_HIGH_PUNCTUATION_MIN && ch0 <= GB2312_HIGH_PUNCTUATION_MAX)
					|| (ch0 >= GB2312_HIGH_FIRSTPLANE_MIN && ch0 <= GB2312_HIGH_FIRSTPLANE_MAX) || (ch0 >= GB2312_HIGH_SECONDPLANE_MIN && ch0 <= GB2312_HIGH_SECONDPLANE_MAX))
					&& (ch1 >= GB2312_LOW_MIN && ch1 <= GB2312_LOW_MAX)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否为GBK(汉字内码扩展规范)内码的字符. 对英文字母、阿拉伯数字、半角标点符号，返回false； 对中文汉字、全角标点符号，返回true。
	 * 
	 * @param c
	 *            待检测的字符
	 * @return 是否为GBK内码的字符
	 */
	public static boolean isGbk(char c) {
		/** GBK编码高字节最小值 */
		final int GBK_HIGH_MIN = 0x81;
		/** GBK编码高字节最大值 */
		final int GBK_HIGH_MAX = 0xFE;
		/** GBK编码低字节最小值 */
		final int GBK_LOW_MIN = 0x40;
		/** GBK编码低字节最大值 */
		final int GBK_LOW_MAX = 0xFE;
		/** GBK编码低字节例外值 */
		final int GBK_LOW_EXCEPTION = 0x7F;

		String str = String.valueOf(c);
		byte[] b = null;
		try {
			b = str.getBytes("GBK");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: "
					+ uee.getMessage());
		}
		if (b.length == 2) {
			// int ch0 = b[0]; // 高位字节
			// int ch1 = b[1]; // 低位字节
			// if (ch0 < 0) {
			// ch0 += 256;
			// }
			// if (ch1 < 0) {
			// ch1 += 256;
			// }
			int ch0 = 0xff & b[0]; // 高位字节
			int ch1 = 0xff & b[1]; // 低位字节

			if (ch0 >= GBK_HIGH_MIN && ch0 <= GBK_HIGH_MAX
					&& ch1 >= GBK_LOW_MIN && ch1 <= GBK_LOW_MAX
					&& ch1 != GBK_LOW_EXCEPTION) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否为GBK(汉字内码扩展规范)内码的汉字. 对英文字母、阿拉伯数字、半角标点符号，返回false； 对全角标点符号，返回false；
	 * 对中文汉字，返回true。
	 * 
	 * @param c
	 *            待检测的字符
	 * @return 是否为GBK内码的汉字
	 */
	@SuppressWarnings("unused")
	public static boolean isGbkHanzi(char c) {
		/** GBK编码高字节最小值 */
		final int GBK_HIGH_MIN = 0x81;
		/** GBK编码高字节最大值 */
		final int GBK_HIGH_MAX = 0xFE;
		/** GBK编码低字节最小值 */
		final int GBK_LOW_MIN = 0x40;
		/** GBK编码低字节最大值 */
		final int GBK_LOW_MAX = 0xFE;
		/** GBK编码低字节例外值 */
		final int GBK_LOW_EXCEPTION = 0x7F;

		/** gb2312编码高字节特殊符号最小值 */
		final int GB2312_HIGH_PUNCTUATION_MIN = 0xA1;
		/** gb2312编码高字节特殊符号最大值 */
		final int GB2312_HIGH_PUNCTUATION_MAX = 0xA9;
		/** gb2312编码高字节一级汉字最小值 */
		final int GB2312_HIGH_FIRSTPLANE_MIN = 0xB0;
		/** gb2312编码高字节一级汉字最大值 */
		final int GB2312_HIGH_FIRSTPLANE_MAX = 0xD7;
		/** gb2312编码高字节二级汉字最小值 */
		final int GB2312_HIGH_SECONDPLANE_MIN = 0xD8;
		/** gb2312编码高字节二级汉字最大值 */
		final int GB2312_HIGH_SECONDPLANE_MAX = 0xF7;
		/** gb2312编码低字节最小值 */
		final int GB2312_LOW_MIN = 0xA1;
		/** gb2312编码低字节最大值 */
		final int GB2312_LOW_MAX = 0xFE;

		String str = String.valueOf(c);
		byte[] b = null;
		try {
			b = str.getBytes("GBK");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: "
					+ uee.getMessage());
		}
		if (b.length == 2) {
			// int ch0 = b[0]; // 高位字节
			// int ch1 = b[1]; // 低位字节
			// if (ch0 < 0) {
			// ch0 += 256;
			// }
			// if (ch1 < 0) {
			// ch1 += 256;
			// }
			int ch0 = 0xff & b[0]; // 高位字节
			int ch1 = 0xff & b[1]; // 低位字节

			if (ch0 >= GBK_HIGH_MIN && ch0 <= GBK_HIGH_MAX
					&& ch1 >= GBK_LOW_MIN && ch1 <= GBK_LOW_MAX
					&& ch1 != GBK_LOW_EXCEPTION) {
				if (ch0 < GB2312_HIGH_PUNCTUATION_MIN
						|| ch0 > GB2312_HIGH_PUNCTUATION_MAX) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否为生僻字，即是GBK但不是gb2312的汉字. 对英文字母、阿拉伯数字、半角标点符号、gb2312中文汉字、全角标点符号，返回false；
	 * 对超出gb2312的GBK汉字、符号，返回true。
	 * 
	 * @param c
	 *            待检测的字符
	 * @return 是否为生僻字
	 */
	public static boolean isRareWord(char c) {
		/** gb2312编码高字节特殊符号最小值 */
		final int GB2312_HIGH_PUNCTUATION_MIN = 0xA1;
		/** gb2312编码高字节特殊符号最大值 */
		final int GB2312_HIGH_PUNCTUATION_MAX = 0xA9;
		/** gb2312编码高字节一级汉字最小值 */
		final int GB2312_HIGH_FIRSTPLANE_MIN = 0xB0;
		/** gb2312编码高字节一级汉字最大值 */
		final int GB2312_HIGH_FIRSTPLANE_MAX = 0xD7;
		/** gb2312编码高字节二级汉字最小值 */
		final int GB2312_HIGH_SECONDPLANE_MIN = 0xD8;
		/** gb2312编码高字节二级汉字最大值 */
		final int GB2312_HIGH_SECONDPLANE_MAX = 0xF7;
		/** gb2312编码低字节最小值 */
		final int GB2312_LOW_MIN = 0xA1;
		/** gb2312编码低字节最大值 */
		final int GB2312_LOW_MAX = 0xFE;

		/** GBK编码高字节最小值 */
		final int GBK_HIGH_MIN = 0x81;
		/** GBK编码高字节最大值 */
		final int GBK_HIGH_MAX = 0xFE;
		/** GBK编码低字节最小值 */
		final int GBK_LOW_MIN = 0x40;
		/** GBK编码低字节最大值 */
		final int GBK_LOW_MAX = 0xFE;
		/** GBK编码低字节例外值 */
		final int GBK_LOW_EXCEPTION = 0x7F;

		String str = String.valueOf(c);
		byte[] b = null;
		try {
			b = str.getBytes("GBK");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: "
					+ uee.getMessage());
		}
		if (b.length == 2) {
			// int ch0 = b[0]; // 高位字节
			// int ch1 = b[1]; // 低位字节
			// if (ch0 < 0) {
			// ch0 += 256;
			// }
			// if (ch1 < 0) {
			// ch1 += 256;
			// }
			int ch0 = 0xff & b[0]; // 高位字节
			int ch1 = 0xff & b[1]; // 低位字节

			if (!(((ch0 >= GB2312_HIGH_PUNCTUATION_MIN && ch0 <= GB2312_HIGH_PUNCTUATION_MAX)
					|| (ch0 >= GB2312_HIGH_FIRSTPLANE_MIN && ch0 <= GB2312_HIGH_FIRSTPLANE_MAX) || (ch0 >= GB2312_HIGH_SECONDPLANE_MIN && ch0 <= GB2312_HIGH_SECONDPLANE_MAX)) && (ch1 >= GB2312_LOW_MIN && ch1 <= GB2312_LOW_MAX))
					&& (ch0 >= GBK_HIGH_MIN && ch0 <= GBK_HIGH_MAX
							&& ch1 >= GBK_LOW_MIN && ch1 <= GBK_LOW_MAX && ch1 != GBK_LOW_EXCEPTION)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取出字符串中的生僻字，即是GBK但不是gb2312的汉字.
	 * 
	 * @param s
	 *            待检测的字符串
	 * @return 生僻字的字符串(找不到时返回0长度字符串"")
	 */
	public static String getRareWords(String s) {
		StringBuilder sb = new StringBuilder();
		if (s != null) {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (StringUtils.isRareWord(c)) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * java字符串中的字符的小写转为大写
	 * 
	 * @param str
	 *            待转换的字符串
	 * @return 转换后的大写的字符串
	 */
	public static String getCapitalWords(String str) {
		int size = str.length();
		char[] chs = str.toCharArray();
		for (int i = 0; i < size; i++) {
			if (chs[i] <= 'Z' && chs[i] >= 'A') {
				chs[i] = (char) (chs[i] + 32);
			} else if (chs[i] <= 'z' && chs[i] >= 'a') {
				chs[i] = (char) (chs[i] - 32);
			}
		}
		return new String(chs);
	}

	/**
	 * 将列表内各元素用分隔符连接为字符串
	 * 
	 * @param list
	 *            列表
	 * @param delimiter
	 *            分隔符
	 * @return 字符串
	 */
	@SuppressWarnings("rawtypes")
	public static String toString(List list, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				sb.append(delimiter);
			}
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	/**
	 * 将数组内各元素用分隔符连接为字符串
	 * 
	 * @param array
	 *            数组
	 * @param delimiter
	 *            分隔符
	 * @return 字符串
	 */
	public static String toString(Object[] array, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(delimiter);
			}
			sb.append(array[i]);
		}
		return sb.toString();
	}

	/**
	 * 按字节切分字符串为不大于指定长度的列表. 切分后，不得有半个汉字现象！
	 * 
	 * @param str
	 *            字符串
	 * @param length
	 *            切分长度(按字节计算，一个汉字或全角标点长度2，一个英文、数字或半角标点长度1)
	 * @return 切分后的列表
	 */
	public static List<String> partitionString(String str, int length) {
		List<String> list = new ArrayList<String>();
		try {
			do {
				String sub = getLimitLengthString(str, length, "");
				list.add(sub);
				str = str.substring(sub.length());
			} while (str.getBytes("GBK").length > length);
			if (str.getBytes("GBK").length > 0) {
				list.add(str);
			}
		} catch (UnsupportedEncodingException uee) {
			String errorMessage = "内部错误：本计算机的Java环境不支持GBK编码！";
			throw new IllegalStateException(errorMessage, uee);
		}
		return list;
	}
	
	/**
	 * 拼接字符串
	 * @param strings
	 * @return
	 */
	public static StringBuilder concatToSB(String... strings) {
		StringBuilder builder = new StringBuilder();
		if (strings != null) {
			for (String str : strings) {
				builder.append(str);
			}
		}
		return builder;
	}
	
	/**
	 * 去掉为NULL的情况
	 * @param str
	 * @return
	 */
	public static String safeValue(String str) {
		if (str == null) return "";
		return str;
	}
	
}