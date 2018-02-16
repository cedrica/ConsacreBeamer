package com.consacresdeleternel.consacrebeamer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {
	private static final Logger LOG = Logger.getLogger(DateUtil.class);

	public static final String DATETIME_SHORT = "yyyy-MM-dd HH:mm";
	public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	public static Date convertToDate(LocalDateTime localDateTime) {
		if (localDateTime == null)
			return null;
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date convertToDate(LocalDate localDate) {
		if (localDate == null)
			return null;
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime convertToLocalDateTime(Date date) {
		if (date == null)
			return null;
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
	}

	public static LocalDate convertToLocalDate(Date date) {
		if (date == null)
			return null;
		return convertToLocalDateTime(date).toLocalDate();
	}

	public static boolean dayIsBetween(LocalDate beforeDate, LocalDate afterDate, LocalDate sourceDate,
			boolean daysIncluded, boolean acceptNull) {
		if (sourceDate != null) {
			LocalDate tempBeforeDate = beforeDate;
			LocalDate tempAfterDate = afterDate;
			if (!acceptNull) {
				if (tempBeforeDate == null || tempAfterDate == null) {
					return false;
				}
			}
			if (tempBeforeDate == null) {
				tempBeforeDate = LocalDate.MIN.plusDays(1);
			}
			if (tempAfterDate == null) {
				tempAfterDate = LocalDate.MAX.minusDays(1);
			}
			if (daysIncluded) {
				tempBeforeDate = tempBeforeDate.minusDays(1);
				tempAfterDate = tempAfterDate.plusDays(1);
			}
			if (sourceDate.isAfter(tempBeforeDate) && sourceDate.isBefore(tempAfterDate)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static Date parseToDate(String dateString) {
		return parseToDate(TIMESTAMP, dateString);
	}

	public static String convertDateToString(Date date, String dateString) {
		if (date != null) {
			return new SimpleDateFormat(dateString).format(date);
		}
		return null;
	}

	public static Date parseToDate(String datePattern, String dateString) {
		try {
			return new SimpleDateFormat(datePattern).parse(dateString);
		} catch (ParseException e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
