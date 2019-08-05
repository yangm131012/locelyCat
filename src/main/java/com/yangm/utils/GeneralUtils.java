package com.yangm.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.yangm.constants.GeneralConstant;
import com.yangm.constants.TimeConstants;


public final class GeneralUtils {

	/**
	 * 数字
	 */
	private static final int NUM_2 = 2;

	/**
	 * 数字16
	 */
	private static final int NUM_16 = 16;

	/**
	 * 不正确的数据长度
	 */
	private static final String ERROR_LENGTH = "不正确的数据长度:";

	/**
	 * 判断字符串是否是空或者空白字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrZeroLenght(String str) {
		return StringUtils.isBlank(str);
	}

	/**
	 * 判断字符串是否为空，为空抛出异常
	 * 
	 * @param str
	 * @param message
	 * @return
	 */
	public static boolean isNullOrZeroLenghtFunction(String str, String message) {
		if (isNullOrZeroLenght(str)) {
			throw new IllegalAccessError(message + "为空或者为字符串");
		}
		return false;
	}

	/**
	 * 判断集合对象是否为null或者0大小 , 为空或0大小返回true ,否则返回false
	 */
	public static boolean isNullOrZeroSize(Collection<? extends Object> c) {
		return isNull(c) || c.isEmpty();
	}

	/**
	 * 判断对象是否为null , 为null返回true,否则返回false
	 */
	public static boolean isNull(Object obj) {
		return (null == obj) ? true : false;
	}

	/**
	 * 封装对象的isNull方法
	 */
	public static boolean isNullFunction(Object obj, String message) {
		if (isNull(obj)) {
			throw new IllegalAccessError(message + "为空");
		} else {
			return false;
		}
	}

	/**
	 * 填充日期
	 * 
	 * @param date
	 * @return
	 */
	public static String fillDate(String date) {
		if (date == null) {
			return null;
		}

		// 替换-
		String var1 = date.replace("-", "");
		// 剩余位数填充0
		int len = var1.length();
		if (len <= 14) {
			for (int i = len; i < 14; i++) {
				var1 += "0";
			}
		}
		return var1;
	}

	/**
	 * 时间串转换时间格式
	 * 
	 * @param str
	 * @param formatF
	 * @param formatT
	 * @return
	 */
	public static String String2String(String str, String formatF, String formatT) {
		return date2String(string2Date(str, formatF), formatT);
	}

	/**
	 * 将java.util.Date类型转化位String类型
	 * 
	 * @param date   要转换的时间
	 * @param format 时间格式
	 * @return 如果转换成功，返回指定格式字符串，如果转换失败，返回null
	 */
	public static String date2String(Date date, String format) {
		if (GeneralUtils.isNull(date) || GeneralUtils.isNull(format)) {
			return null;
		}
		return DateFormatUtils.format(date, format);
	}

	/**
	 * 将字符串时间转换成java.util.Date类型
	 *
	 * @param str    要转换的字符串
	 * @param format 时间格式
	 * @return 如果转换失败，返回null
	 */
	public static Date string2Date(String str, String format) {
		if (GeneralUtils.isNull(str) || GeneralUtils.isNull(format)) {
			return null;
		}

		// 定义日期/时间格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			// 转换日期/时间格式
			date = sdf.parse(str);
			// 判断转换前后时间是否一致
			if (!str.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * 字符串非空判断
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean isNotNullOrZeroLenght(String str) {
		return !isNullOrZeroLenght(str);
	}

	/**
	 * 判断主键是否为空，为空抛出异常
	 * 
	 * @param id
	 */
	public static void idIsNull(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("主键不能为空");
		}
	}

	/**
	 * 判断数字类型是否为null或者0，如果是返回true，否则返回false
	 *
	 * @param number 被判断的数字
	 * @return boolean
	 */
	public static boolean isNullOrZero(Number number) {
		if (GeneralUtils.isNotNull(number)) {
			return (number.doubleValue() != 0) ? false : true;
		}
		return true;
	}

	/**
	 * 判断数字类型是否不为null或者0，如果是返回true，否则返回false
	 *
	 * @param number 被判断的数字
	 * @return boolean
	 */
	public static boolean isNotNullOrZero(Number number) {
		return !isNullOrZero(number);
	}

	/**
	 * 返回当前时间戳
	 * 
	 * @param pattern 默认为：yyyyMMddHHmmss
	 * @return string 时间字符串
	 */
	public static String getCurrentTimeStamp(String pattern) {
		if (isNullOrZeroLenght(pattern)) {
			pattern = TimeConstants.DATETIME_14;
		}
		Date date = new Date(System.currentTimeMillis());
		return date2String(date, pattern);
	}

	/**
	 * 判断对象是否为null , 为null返回false,否则返回true
	 *
	 * @param obj 被判断的对象
	 * @return boolean
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 判断集合对象是否为null或者0大小 , 为空或0大小返回false, 否则返回true
	 *
	 * @param c collection 集合接口
	 * @return boolean 布尔值
	 * 
	 * @author yanjq
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isNotNullOrZeroSize(Collection<? extends Object> c) {
		return !isNullOrZeroSize(c);
	}

	/**
	 * 
	 * 判断上报的位置信息，是否定位成功
	 * 
	 * @param hexState TL_LOCATION_HISTORY表中STATUS_HEX_STR字段
	 * @return true 表示定位成功;flase 表示定位不成功
	 */
	public static boolean isGpsValid(String hexState) {
		if (GeneralUtils.isNullOrZeroLenght(hexState)) {
			return false;
		}

		byte[] bytes = GeneralUtils.hexString2ByteArray(hexState);

		int state = ((bytes[bytes.length - 1] & 0x02) >> 1);

		if (state == 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 将16进制数字符串类型转换成字节数组
	 * 
	 * @param value 16进制数字符串表现形式
	 * @return byte[]
	 * @see [类、类#方法、类#成员]
	 */
	public static byte[] hexString2ByteArray(String value) {
		if (value.length() % NUM_2 != 0) {
			throw new IllegalStateException(value + ERROR_LENGTH + value.length());
		}
		byte[] returnValue = new byte[value.length() / NUM_2];
		try {
			byte[] bytes = value.getBytes(GeneralConstant.CHARACTER_CODING);
			for (int i = 0, j = 0; i < bytes.length; i += NUM_2, j++) {
				returnValue[j] = (byte) Integer.parseInt(new String(new byte[] { bytes[i], bytes[i + 1] }), NUM_16);
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return returnValue;
	}

	/**
	 * 增加小数点控制 控制数字的小数点
	 * 
	 * @param value        - 需要修改的小数
	 * @param scale        - 控制的精度
	 * @param roundingMode - 取整模式
	 * @return byteArray 计算后返回的数值
	 * @author yanjq
	 */
	public static double round(double value, int scale, int roundingMode) {
		// 大数类
		BigDecimal bd = new BigDecimal(value);

		// 设置精度
		bd = bd.setScale(scale, roundingMode);

		// 进行运算
		double dValue = bd.doubleValue();

		return dValue;
	}
}
