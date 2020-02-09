package com.tdm.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tdm.constant.MessageConstant;
import com.tdm.exception.ServiceException;


public class DateUtil
{

	public static String converDateToString(Date date) throws ServiceException {
		try {
			if (null != date) {
				SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM/yyyy");
				String stringobj = dataFormater.format(date);
				return stringobj;
			}

			return null;
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	public static Date converStringToDate(String strdate) {

		if (strdate.contains(".")) {
			strdate = strdate.substring(0, strdate.indexOf("."));
		}
		Date returndate = null;
		if (null != strdate) {
			try {
				SimpleDateFormat dataFormater = new SimpleDateFormat("MM/dd/yyyy");
				returndate = dataFormater.parse(strdate);
				dataFormater.format(returndate);
				return returndate;
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		return returndate;
	}

	public static Timestamp converStringTimeStampToSqlTimeStamp(String strdate) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a zzz");
			Date date = formatter.parse(strdate);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
	}

	public static String converDateToStringToCal(Date date) throws ServiceException {
		try {
			if (null != date) {
				SimpleDateFormat dataFormater = new SimpleDateFormat("yyyy-MM-dd");
				String stringobj = dataFormater.format(date);
				return stringobj;
			}

			return null;
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}

	}
}
