<%@page import="com.seawind.model.ShopData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.ShopDataDao,com.seawind.utils.OwnerUtil,com.seawind.utils.HttpToolUtil,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	//未登录，返回登录画面
	if (!HttpToolUtil.isLogin(request, response)) {
		return;
	}
	String title = WebCommon.titleDefault;
	OwnerUtil.setMInfo(request);
	ShopDataDao sdd = new ShopDataDao();
	ShopData sd = sdd.getData();
	if (null != sd) {
		title = sd.getName();
	}else{
		sd = new ShopData();
	}
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
				<td height="30px" colspan="2"
					background="<%=request.getContextPath()%>/images/titlebg.gif">店主信息</td>
			</tr>
			<tr>
				<td width="280px" rowspan="6" align="center" valign="middle"><img
					src="<%=sd.getImgUrl()%>" width="280px" height="280px"
					style="text-align: center" align="middle" /></td>
				<td height="40px" class="shopInfo">餐厅名称：<%=sd.getName()%></td>
			</tr>
			<tr>
				<td height="40px" class="shopInfo">订餐电话：<%=sd.getPhone()%></td>
			</tr>
			<tr>
				<td height="40px" class="shopInfo">所属城市：<%=sd.getCity()%></td>
			</tr>
			<tr>
				<td height="40px" class="shopInfo">详细地址：<%=sd.getAddr()%></td>
			</tr>
			<tr>
				<td height="40px" class="shopInfo">&nbsp;联&nbsp;系&nbsp;人&nbsp;：<%=sd.getLinkMan()%></td>
			</tr>
			<tr>
				<td height="40px" align="center"><a href="<%=request.getContextPath()%>/jsp/swshopinfoeidt.jsp">编辑</a></td>
			</tr>
			<tr class="shopInfoIntro">
				<td height="120px" colspan="2" valign="top">简介：<%=sd.getIntro()%></td>
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