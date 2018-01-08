<%@ page import="tgtools.json.JSONObject" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2017-05-08
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    JSONObject json =new JSONObject();
    json.put("ID_","fdafda");
    json.put("REV_","fdafda");

    String insert =tgtools.util.JsonSqlFactory.parseInsertSql(json,"BQ_SYS.ACT_ID_USER");
    ArrayList<String> keys=new ArrayList<String>();
    keys.add("ID_");
    String update =tgtools.util.JsonSqlFactory.parseUpdateSql(json,keys,"BQ_SYS.ACT_ID_USER");
    out.println("insert :"+insert);
    out.println("<br/>");
    out.println("update :"+update);
%>