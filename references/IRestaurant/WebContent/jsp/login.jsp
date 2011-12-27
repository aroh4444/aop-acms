<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.utils.OwnerUtil,com.seawind.common.*,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);
	String err = request.getParameter(TAG.HttpState);
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
		<form name="myInput" enctype="MULTIPART/FORM-DATA" method="post"
			action="<%=request.getContextPath()%>/sw?opt=login">
		<table width="650px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="5"
					background="<%=request.getContextPath()%>/images/titlebg.gif">登录</td>
			</tr>
			<tr>
				<td width="100px" class="shopInfo">密码：</td>
				<td width="300px" class="mTextInPut"><input name="passW"
					type="password" value="" maxlength="36" style="width: 98%"/></td>
				<td style="width: 60px">
				  <input type="submit" value="确定" style="width: 70px;"/>
				</td>
				<td style="text-align: center;width: 190px"><a
					href="<%=OwnerUtil.getUrlRootByReq(request)%>">返回美食列表</a>
				</td>
			</tr>
			<%if((null!=err)&&(err.equals(MHttpState.State_201))){ %>
			<tr>
				<td height="30px" colspan="4" style="text-align: center;color: red;">密码错误，您可以通过修改：<%=request.getSession().getServletContext()
				.getRealPath("")+ "\\WEB-INF\\mset.xml的passW标签对密码进行设置"%></td>
			</tr>
			<%} %>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
<div align="center"></div>
</body>
</html>