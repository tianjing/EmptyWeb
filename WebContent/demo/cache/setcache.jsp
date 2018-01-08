<%@page import="tgtools.cache.CacheFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
java.util.HashMap<String,String> d=new java.util.HashMap<String,String>();
d.put("a", "1");
d.put("b", "2");

net.sf.ehcache.Element ele =new net.sf.ehcache.Element("tianjing",d);
CacheFactory.get(CacheFactory.TimerCache).put(ele);





%>