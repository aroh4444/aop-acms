<%@page import="com.seawind.model.OrderData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.*,com.seawind.model.CookData,com.seawind.utils.OwnerUtil,com.seawind.common.*,com.seawind.utils.OwnerUtil,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);
	
	String id = request.getParameter(TAG.id);
	OrderData od = new OrderDataDao().getDataById(id);
	CookData cd = new CookDataDao().getDataById(od.getGoodId());
%>
<title><%=title%></title>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="contents">
<table width="<%=WebCommon.pageWidth%>" border="0" align="center">
	<tr>
		<td height="<%=WebCommon.headHeight%>"><jsp:include page="head.jsp"></jsp:include></td>
	</tr>
	<tr>
		<td height="<%=WebCommon.pageHeight%>">
		<table width="650px" border="1" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="3"
					background="<%=request.getContextPath()%>/images/titlebg.gif">订购成功</td>
			</tr>
			<tr>
				<td width="270px" rowspan="6"><img
					src="<%=cd.getImgUrl()%>"
					width="270px" height="270px" /></td>
				<td width="360px" height="35px" class="shopInfo">美食名称：<%=cd.getName()%></td>
				<td class="cookPrice" style="text-align: center;width: 120px">￥<%=cd.getPrice()%></td>
			</tr>
			<tr>
				<td height="35px" colspan="2" class="shopInfo">订购电话：<%=cd.getPhone()%></td>
			</tr>
			<tr>
				<td height="35px" colspan="2" class="shopInfo">&nbsp;联&nbsp;系&nbsp;人&nbsp;：<%=cd.getLinkMan()%></td>
			</tr>
			<tr>
				<td height="35px" colspan="2" class="shopInfo">订购数量：<%=od.getCount()%></td>
			</tr>
			<tr>
				<td height="35px" colspan="2" class="shopInfo">&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;&nbsp;址&nbsp;&nbsp;：<%=OwnerUtil.getUrlRootByReq(request)%></td>
			</tr>
			<tr>
				<td height="35px" class="shopInfo">订购时间：<%=OwnerUtil.getTimeByTimeStr(od.getLastModified())%></td>
				<td style="text-align: center;width: 120px"><a
					href="<%=request.getContextPath()%>/IndexSv?opt=<%=OPT.dispbypage%>&pageNow=1">返回列表</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="30px">
		<div align="center">您可以使用浏览器 "另存为" 保存本页</div>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
<div align="center"></div>
</body>
</html>