package com.test.util;

import tgtools.data.DataTable;
import tgtools.exceptions.APPErrorException;

public class DBHelper {

	public static DataTable select(String p_Sql) throws APPErrorException
	{
		return tgtools.db.DataBaseFactory.getDefault().Query(p_Sql);
	}
	
	public static int excute(String p_Sql) throws APPErrorException
	{
		return tgtools.db.DataBaseFactory.getDefault().executeUpdate(p_Sql);
	}
	
}
