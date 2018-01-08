<%@ page import="tgtools.plugin.IPlugin" %><%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2016-10-09
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {
        IPlugin mail=tgtools.plugin.PluginFactory.getPlugin("mailplugin");
        mail.execute("smtp.ym.163.com","tianjing@binfo-tech.com","tg58783933","tianjinggggg","tianjing@binfo-tech.com","tianji","测是1","<html><head></head><body>你好</body></html>");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
%>

