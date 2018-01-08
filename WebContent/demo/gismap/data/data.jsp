<%@ page import="tgtools.json.JSONObject" %>
<%@ page import="tgtools.json.JSONArray" %>
<%@ page import="tgtools.json.JSONException" %><%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2017-10-16
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    tgtools.web.util.RequestHelper.invoke(this,request,response);
%>
<%!
    public String getKeySearch(JSONObject p_Json) {
        try {
            String text =p_Json.getString("text");
            JSONArray result = new JSONArray();
            JSONObject json1 = new JSONObject();
            json1.put("name", text+"1");

            JSONObject json2 = new JSONObject();
            json2.put("name", text+"2");

            result.put(json1);
            result.put(json2);
            return result.toString();
        } catch (Exception ex)
        {
            return "";
        }
    }

    public String getSearch(JSONObject p_Json) {
        try {
            JSONArray result = new JSONArray();
            JSONObject json1 = new JSONObject();
            json1.put("name", "田径11");
            json1.put("lat", 32.1347);
            json1.put("lng", 118.5660);

            JSONObject json2 = new JSONObject();
            json2.put("name", "田径111");
            json2.put("lat", 32.8357);
            json2.put("lng", 118.3670);
            result.put(json1);
            result.put(json2);
            return result.toString();
        } catch (Exception ex)
        {
            return "";
        }
    }
%>
