<%@page import="tgtools.db.DataBaseFactory"%>
<%@page import="tgtools.service.ServiceFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//ServiceFactory.register(new com.project.TestService());
String sql="update loginfo set logtime=sysdate";
DataBaseFactory.getDefault().executeUpdate(sql);

System.out.println(DataBaseFactory.get("SpringDM6DataSource").Query("select sysdate").getRow(0).getValue(0));
%>