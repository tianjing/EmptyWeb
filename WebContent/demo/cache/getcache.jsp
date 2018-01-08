<%@page import="net.sf.ehcache.CacheManager"%>
<%@page import="net.sf.ehcache.distribution.CacheManagerPeerProviderFactory"%>
<%@page import="tgtools.cache.CacheFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
net.sf.ehcache.Element ele= CacheFactory.get(CacheFactory.TimerCache).get("tianjing");
java.util.HashMap<String,String> map=(java.util.HashMap<String,String>) ele.getObjectValue();

for(java.util.Map.Entry<String, String> item :map.entrySet())
{
		System.out.print("key:"+item.getKey()+"  value:"+item.getValue());
}

%>