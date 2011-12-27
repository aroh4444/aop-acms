<%@page import="com.seawind.model.OrderData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.*,com.seawind.model.CookData,com.seawind.utils.TimeParseUtil,com.seawind.utils.OwnerUtil,com.seawind.common.*,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);
	
	String pageNowStr = (String) request.getAttribute(TAG.pageNow);
	int pageNow = 0;
	int total = 0;
	int pageCount = 0;
	String goodId = "";
	String pageNowP = "";
	CookData cd = null;

	ArrayList<OrderData> datas = null;
	if (null != pageNowStr) {
		pageNow = Integer.valueOf(pageNowStr);
		String totalStr = (String) request.getAttribute(TAG.total);
		goodId = (String) request.getAttribute(TAG.id);
		pageNowP = (String) request.getAttribute(TAG.pageNowP);
		if (null != totalStr) {
			total = Integer.valueOf(totalStr);
		}
		datas = (ArrayList<OrderData>) request.getAttribute(TAG.datas);
		CookDataDao cdd = new CookDataDao();
		cd = cdd.getDataById(goodId);
	} else {
		goodId = (String) request.getParameter(TAG.id);
		pageNowP = (String) request.getParameter(TAG.pageNowP);
		CookDataDao cdd = new CookDataDao();
		cd = cdd.getDataById(goodId);
		OrderDataDao odd = new OrderDataDao();
		datas = odd.getDatasByPage(goodId, 1);
		total = odd.getTotal(goodId);
		pageNow = 1;
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
		<form name="myInput" enctype="MULTIPART/FORM-DATA" method="post"
			action="<%=request.getContextPath()%>/sw?opt=login">
		<table width="960px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="6"
					background="<%=request.getContextPath()%>/images/titlebg.gif"><%=cd.getName()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订购列表</td>
			</tr>
			<tr>
				<th height="30px" style="width: 160px">客户称呼</th>
				<th style="width: 160px">订购数量</th>
				<th style="width: 160px">联系电话</th>
				<th style="width: 160px">订购渠道</th>
				<th style="width: 160px">订购时间</th>
				<th style="width: 160px">备注</th>
			</tr>
			<%
				for (OrderData data : datas) {
			%>
			<tr>
				<td height="60px" align="center"><%=data.getName()%></td>
				<td align="center"><%=data.getCount()%></td>
				<td align="center"><%=data.getPhone()%></td>
				<td align="center"><%=OwnerUtil.changeTypeToStr(data.getType())%></td>
				<td align="center"><%=TimeParseUtil.getTimeWithSec(data.getLastModified())%></td>
				<td><textarea rows="3" style="width: 160px"><%=data.getMore()%></textarea></td>
			</tr>
			<%
				}
			%>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td height="30px">
		<div align="center">
		<%
			if (total > 0) {
				if ((pageNow > 0) && (1 != pageNow)) {
					out.println("<a href=" + request.getContextPath()
							+ "/SWOrders?opt=" + OPT.dispbypage + "&pageNow="
							+ (pageNow - 1) + "&id=" + goodId + "&pageNowP="
							+ pageNowP + ">[上一页]</a>"); 
				}

				if (0 == total % MConstants.PageSizeOrder) {
					pageCount = total / MConstants.PageSizeOrder;
				} else {
					pageCount = total / MConstants.PageSizeOrder + 1;
				}
				int dispCount = 0;
				int dispNow = 0;

				if ((pageNow + MConstants.PageDispMax) > pageCount) {
					dispCount = pageCount;
				} else {
					dispCount = pageNow + MConstants.PageDispMax;
				}

				if ((dispCount - pageNow) > MConstants.PageDispMax) {
					dispNow = pageNow;
				} else {
					dispNow = dispCount - MConstants.PageDispMax;
				}

				if (dispNow <= 0) {
					dispNow = 1;
				}

				for (int i = dispNow; i <= dispCount; i++) {
					out.println("<a href=" + request.getContextPath()
							+ "/SWOrders?opt=" + OPT.dispbypage + "&pageNow="
							+ i + "&id=" + goodId + "&pageNowP=" + pageNowP
							+ ">[" + i + "]</a>");
				}

				if ((pageCount > 0) && (pageCount != pageNow)) {
					out.println("<a href=" + request.getContextPath()
							+ "/SWOrders?opt=" + OPT.dispbypage + "&pageNow="
							+ (pageNow + 1) + "&id=" + goodId + "&pageNowP="
							+ pageNowP + ">[下一页]</a>");
				}
			}
			out.println("&nbsp;&nbsp;&nbsp;当前第" + pageNow + "页，共" + pageCount
					+ "页，" + total + "条");
			out.println("&nbsp;&nbsp;&nbsp;<a href=" + request.getContextPath()
					+ "/SWCooks?opt=" + OPT.dispbypage + "&pageNow=" + pageNowP
					+ ">返回</a>");
		%>
		</div>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
<div align="center"></div>
</body>
</html>