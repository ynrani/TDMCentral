package com.tesda.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test class
 */
public class TestClass extends Thread
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String time = new Timestamp(new Date().getTime()).toString();
		System.out.println(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssz");
		try
		{

			SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			time = sdf1.format(new Date().getTime());
			Date date = sdf1.parse(time);
			System.out.println(time);
			System.out.println(new Timestamp(date.getTime()));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}
