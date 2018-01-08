<%@ page import="tgtools.json.JSONArray" %>
<%@ page import="tgtools.web.rests.entity.ResposeEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2016-11-22
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../../Util.jsp"%>
<%
    ResposeEntity entity =new ResposeEntity();
    try {
        JSONArray array = new JSONArray();

        JSONArray value1 = new JSONArray();
        value1.put("00:15");
        value1.put(10.7);
        value1.put("V0015");

        JSONArray value2 = new JSONArray();
        value2.put("00:30");
        value2.put(20.7);
        value2.put("V0030");


        JSONArray value3 = new JSONArray();
        value3.put("00:45");
        value3.put(30.7);
        value3.put("V0045");


        JSONArray value4 = new JSONArray();
        value4.put("01:00");
        value4.put(42.7);
        value4.put("V0100");


        JSONArray value5 = new JSONArray();
        value5.put("01:15");
        value5.put(52.7);
        value5.put("V0115");

        array.put(value1);
        array.put(value2);
        array.put(value3);
        array.put(value4);
        array.put(value5);

        entity.Success=true;
        entity.Data=array.toString();
    }catch (Exception ex)
    {
        entity.Success=false;
    }

    out.clear();
    out.write(paserToJson(entity));
%>