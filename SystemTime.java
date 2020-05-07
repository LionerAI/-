package com.post.util;

import java.sql.Timestamp;
import java.util.Date;

public class SystemTime 
{
	public static Timestamp getTime()
	{
		Date date = new Date();       
		Timestamp dateStamp = new Timestamp(date.getTime());
		return dateStamp;
	}
	
	public static Timestamp getAfterTime(Integer second)
	{
		Date date = new Date();       
		Timestamp dateStamp = new Timestamp(date.getTime() + second * 1000);
		return dateStamp;
	}
}
