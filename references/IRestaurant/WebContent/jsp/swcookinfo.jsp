<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.CookDataDao,com.seawind.model.CookData,com.seawind.utils.OwnerUtil,com.seawind.utils.HttpToolUtil,com.seawind.common.*,com.seawind.webcommon.WebCommon"
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
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);

	String id = request.getParameter(TAG.id);
	CookDataDao cdd = new CookDataDao();
	CookData cd = cdd.getDataById(id);
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
				<td height="30px" colspan="3"
					background="<%=request.getContextPath()%>/images/titlebg.gif">美食详情</td>
			</tr>
			<tr>
				<td width="180px" rowspan="4"><img src="<%=cd.getImgUrl()%>"
					width="180px" height="180px" /></td>
				<td width="350px" height="30px" class="shopInfo">美食名称：<%=cd.getName()%></td>
				<td class="cookPrice" style="text-align: center;width: 120px">￥<%=cd.getPrice()%></td>
			</tr>
			<tr>
				<td height="30px" colspan="2" class="shopInfo">订购电话：<%=cd.getPhone()%></td>
			</tr>
			<tr>
				<td height="30px" colspan="2" class="shopInfo">所属城市：<%=cd.getCity()%></td>
			</tr>
			<tr>
				<td width="350px" class="shopInfo">联&nbsp;系&nbsp;人&nbsp;：<%=cd.getLinkMan()%></td>
				<td class="shopInfo" style="text-align: center;width: 120px"><a
					href="javascript:history.go(-1)">返回</a>
				</td>
			</tr>
			<tr class="shopInfoIntro">
				<td height="120px" colspan="3" valign="top">简介：<%=cd.getIntro()%></td>
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