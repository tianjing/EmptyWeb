<%@ page import="tgtools.plugin.IPlugin" %><%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2017-06-29
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   IPlugin plugin= tgtools.plugin.PluginFactory.getPlugin("pinyinplugin");
    String res1 ="";
    String res2 ="";
    String res3 ="";
    if(null!=plugin) {
       res1 = (String) plugin.execute("田径");
       res2 = (String) plugin.execute("田径", ";");
       res3 = (String) plugin.execute("田径", ";", true);
   }
%>
<%="res1:"+res1%>

<%="res2:"+res2%>
<%="res3:"+res3%>
