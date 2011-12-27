<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.utils.OwnerUtil,com.seawind.common.TAG,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);
	
	String errMsg = (String)request.getAttribute(TAG.errMsg);
%>
<title><%=title%></title>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="contents">
<table width="<%=WebCommon.pageWidth%>" border="0" align="center">
	<tr>
		<td height="<%=WebCommon.headHeight%>"><jsp:include
			page="headsw.jsp"></jsp:include></td>
	</tr>
	<tr>
		<td height="<%=WebCommon.pageHeight%>">
		<table width="650px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px"
					background="<%=request.getContextPath()%>/images/titlebg.gif">用户提示</td>
			</tr>
			<tr>
				<td height="30px" style="text-align: center; color: red;"><%=errMsg%></td>
			</tr>
			<tr>
				<td align="center">
				  <a href="<%=request.getContextPath()%>/jsp/swshopinfoeidt.jsp">填写餐厅信息</a>
				</td>
			</tr>
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