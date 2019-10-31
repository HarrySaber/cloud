/**
 * STARPOST CONFIDENTIAL
 * _____________________
 * 
 * [2014] - [2018] StarPost Supply Chain Management Co. (Shenzhen) Ltd. 
 * All Rights Reserved.
 * 
 * NOTICE: All information contained herein is, and remains the property of
 * StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from StarPost Supply Chain Management Co. (Shenzhen)
 * Ltd.
 *
 */
package com.springboot.cloud.common.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author D.king
 * @Date 2019年7月18日
 * @ClassName: DoubleFormatUtils.java
 */
public class DoubleFormatUtils {
	private final int newScala;
	private final int roundingMode;

	public DoubleFormatUtils(int newScala, int roundingMode) {
		this.newScala = newScala;
		this.roundingMode = roundingMode;
	}

	/**
	 * 默认为四舍五入
	 * 
	 * @author luojiaheng
	 * @param d        需要格式化的Double
	 * @param newScala 保留的位数
	 * @return 格式化后的Double
	 */
	public static Double format(Double d, int newScala) {
		if (d == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(d);
		return b.setScale(newScala, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 默认四舍五入，保留两位
	 * 
	 * @author luojiaheng
	 * @param d
	 * @return
	 */
	public static Double defaultFormat(Double d) {
		return format(d, 2);
	}

	public Double format(Double d) {
		if (d == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(d);
		return b.setScale(newScala, roundingMode).doubleValue();
	}

	public static String formatDoubleToString(Double d) {
		if (d == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

	public static boolean isNumber(String num) {
		if (num == null || "".equals(num.trim())) {
			return false;
		}
		String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(num);
		return isNum.matches();
	}

}
