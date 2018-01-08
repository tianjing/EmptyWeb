<%@page import="tgtools.data.DataTable"%>
<%@page import="tgtools.db.DataBaseFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

DataTable dt= DataBaseFactory.get("SpringDB2DataSource").Query("select * from JB_DIC_STATION");

System.out.println(dt.getRows().size());


%>