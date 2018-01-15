package tgtools.plugin.demo.util;

public class LogHelper {

	public static void info(String message)
	{
		tgtools.util.LogHelper.infoForce("", message, "ClientBasePlugin");
	}
	
	public static void error(String message, Throwable ex)
	{
		tgtools.util.LogHelper.error("", message, "ClientBasePlugin", ex);
	}
}
