package com.test.util;

public class LogHelper {

	public static void info(String message)
	{
		tgtools.util.LogHelper.info("", message, "MailSendService");
	}
	
	public static void error(String message,Throwable ex)
	{
		tgtools.util.LogHelper.error("", message, "MailSendService", ex);
	}
}
