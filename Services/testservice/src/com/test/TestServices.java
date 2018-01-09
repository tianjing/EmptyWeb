package com.test;

import tgtools.data.DataTable;
import tgtools.json.JSONArray;
import tgtools.service.BaseService;
import tgtools.tasks.TaskContext;
import tgtools.tasks.TaskRunner;
import tgtools.util.DateUtil;

import java.util.Date;


public class TestServices extends BaseService {
	/**
	*服务名称，部署时用（唯一） 必须
	*/
	@Override
	public String getName()
	{
		return "testservice";
	}
	
	/**
	*执行间隔 （毫秒） 必须
	*/
	@Override
	protected int getInterval() {
		// TODO Auto-generated method stub
		return 10000;
	}
	/**
	*结束时间  DateUtil.getMaxDate() 为永不结束 必须
	*/
	@Override
	protected Date getEndTime() {
		// TODO Auto-generated method stub
		return DateUtil.getMaxDate();
	}
	/**
	* 服务的主要执行代码 必须
	*/
	@Override
	public void run(TaskContext p_Param) {
		while (true) {
		try {

				tgtools.util.LogHelper.info("", "测试任务开始", "TestServices");

				//DataTable dt = tgtools.db.DataBaseFactory.getDefault().Query("select top 10000 * from loginfo");
			//	String ss =new JSONArray( dt.toJson()).toString();

			    tgtools.db.DataBaseFactory.getDefault().executeUpdate("update LOGINFO set USERNAME ='1' where ID_='2F28F668-6DF4-4692-AB47-0D4D1F151893'");
				tgtools.util.LogHelper.info("", "测试任务结束", "TestServices");

				//Thread.sleep(1000);

			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		try {
			if (null == tgtools.db.DataBaseFactory.getDefault()) {
				tgtools.db.DataBaseFactory.add("DBCP", "jdbc:dm://192.168.1.240:5236", "BQ_SYS123", "BQ_SYS");
			}

			TaskRunner<TestServices> dd=new TaskRunner<TestServices>();
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.add(new TestServices());
			dd.runThreanTillEnd();
			System.out.println("全部结束");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
