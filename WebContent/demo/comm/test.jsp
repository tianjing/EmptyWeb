<%@page import="tgtools.exceptions.APPErrorException"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String data=getData();
	String data1=getData();

//JSONObject json =new JSONObject(data);
//data=json.getString("name");
%>
<%!public String getData() throws APPErrorException {

	//DataBaseFactory.get("SpringOMSDataSource").Query("select 1");
	//return  tgtools.web.util.WebRestClient.doPost("http://localhost:8080/EmptyProject/myrest/CommRest/exec", "YZCZP", "czl,142313062");
	//	return  tgtools.web.util.WebRestClient.doPost("http://172.17.3.250:8081/WebSun/myrest/CommRest/exec", "PluginTiansen", "");
//return  tgtools.web.util.WebRestClient.doPost("http://localhost:8080/EmptyProject/myrest/CommRest/exec", "mailplugin", "Bbxxbinsert,南京,,220kV29YT201611034,xxxx,2017-02-17+12:00:00.0000000,0CA123,xxx,2");
return  tgtools.web.util.WebRestClient.doPost("http://localhost:8080/EmptyProject/myrest/CommRest/exec", "PWKHDataPlugin", "type=info&content=allCorpCode&corpName=1111");

}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=data %>


<%=data1 %>
</body>
</html>