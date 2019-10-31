package com.springboot.cloud.common.core.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatUtils {

	final static Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDDHHMMSSMS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String MDY = "MMM d,yyyy";
	public static final String MDYHMS = "MMM d,yyyy hh:mm:ss";
	public static final String DDMMYYYY = "dd/MM/yyyy";
	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMM = "yyyy-MM";
	public static final String DAY = "yyyyMMdd";
	public static final String HHMM = "HH:mm";
	public static final String HHMMSS = "HH:mm:ss";

	public static String getStrFromYYYYMMDDHHMMSS(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return sdf.format(date);
	}

	public static String getStrFromYYYYMMDDHHMMSSMS(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSSMS);
		return sdf.format(date);
	}

	public static String getStrFromYYYYMMDD(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		return sdf.format(date);
	}

	public static String getStrFromYYYYMM(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMM);
		return sdf.format(date);
	}

	public static String getStrFromDate(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date getFormatDateByStr(String date) {
		Date sdate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
			sdate = sdf.parse(date);
		} catch (ParseException e) {
			logger.debug("Parse Error", e);
		}
		return sdate;
	}

	public static Date getFormatDateByStr(String date, String type) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		Date sdate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(type);
			sdate = sdf.parse(date);
		} catch (ParseException e) {
			logger.error("parse date error", e);
		}
		return sdate;
	}

	public static Date getFormatDateYmdhmsByStr(String date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("error message", e);
		}
		return new Date();
	}

	public static Date getFormatDateYmdhmByStr(String date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMM);

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("error message", e);
		}
		return new Date();
	}

	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return df.format(new Date());
	}

	public static Date addDay(Date date, int day) {
		if (null == date) {
			return null;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	public static Date addDay(String date, int day) {
		Date dateTime = getFormatDateByStr(date, YYYYMMDD);
		return addDay(dateTime, day);
	}

	/**
	 * 
	 * @modify renxiangyang
	 * @param date
	 *            YYYYMMDD
	 * @return
	 */
	public static Date getFormatDate(Date date) {
		Date sdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		sdate = getFormatDateByStr(sdf.format(date));
		return sdate;
	}

	public static Date getFormatDate(Date date, String type) {
		Date sdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		sdate = getFormatDateByStr(sdf.format(date));
		return sdate;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @author lijun
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetweenHalfUp(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		int between_days = (int) Math.ceil((time2 - time1) / (1000 * 3600 * 24.00));
		return between_days;
	}

	public static void main(String[] args) {
		Date date1 = DateFormatUtils.getFormatDateByStr("2017-04-18");
		Date date2 = DateFormatUtils.getFormatDateByStr("2017-04-28");
		System.out.println(daysBetweenHalfUp(date1, date2));
	}
}
