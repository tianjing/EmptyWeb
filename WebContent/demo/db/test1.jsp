<%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2016-12-24
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
public String getData()
{

    Object obj=tgtools.db.DataBaseFactory.get("PI3000RPCSource");
    return null==obj?"":obj.toString();
}

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=getData()%>
</body>
</html>
