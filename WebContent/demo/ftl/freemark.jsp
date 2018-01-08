<%@page import="tgtools.plugin.*,tgtools.data.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//组装数据
Map<String,Object>  root = new HashMap<String,Object>();
root.put( "message", "Hello World!" );
DataTable dt=new DataTable();
dt.appendColumn("name");
dt.appendColumn("sex");
DataRow row= dt.appendRow();
row.setValue("name", "tg1");
row.setValue("sex", 2.2);

DataRow row1= dt.appendRow();
row1.setValue("name", "tg2");
row1.setValue("sex", 2.2);

root.put("table", dt);
//获取插件
IPlugin plu= PluginFactory.getPlugin("freemark");
System.out.println(this.getServletConfig().getServletContext().getRealPath("test.ftl"));
try {
	//模板的处理 并返回 result
	String result= (String)plu.execute(root,this.getServletConfig().getServletContext().getRealPath("ftl/test.ftl"),"UTF-8");
	out.clear();
	out.write(result);
	out.flush();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>