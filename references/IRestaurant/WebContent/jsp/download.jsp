<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.utils.OwnerUtil,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);
%>
<title><%=title%></title>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<table width="<%=WebCommon.pageWidth%>" border="0" align="center">
	<tr>
		<td height="<%=WebCommon.headHeight%>"><jsp:include
			page="head.jsp"></jsp:include></td>
	</tr>
	<tr>
		<td height="<%=WebCommon.pageHeight%>" valign="middle" align="center"">
		<table border="0" width="900" align="center" class="alink">
			<tr><td><a href="<%=request.getContextPath()%>/apk/ICookBook1.0.apk">Android 手机客户端下载</a></td></tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
<div align="center"></div>
</body>
</html>