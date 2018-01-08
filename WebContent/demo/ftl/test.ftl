<html>
<head>
  <title>FreeMarker Example Web Application 1</title>
</head>
<body>
 ${message}
</body>
<#list table as dt>
<tr><td>${dt.name}</td> <td> ${dt.sex}</td>
</#list>
</html>