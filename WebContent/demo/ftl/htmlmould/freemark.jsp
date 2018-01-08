<%@page import="tgtools.plugin.*,tgtools.data.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//组装数据
Map<String,Object>  root = new HashMap<String,Object>();
root.put("str", null==request.getParameter("type")?"table1":request.getParameter("type"));
//获取插件
IPlugin plu= PluginFactory.getPlugin("freemark");
System.out.println(this.getServletConfig().getServletContext().getRealPath("demo/tg/ftl/htmlmould/index.ftl"));
try {
	//模板的处理 并返回 result
	String result= (String)plu.execute(root,this.getServletConfig().getServletContext().getRealPath("demo/tg/ftl/htmlmould/index.ftl"));
	out.clear();
	out.write(result);
	out.flush();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>